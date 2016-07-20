/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author user1
 */
public class Add extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try{
            HttpSession hs = request.getSession();
            ItemData item = (ItemData)hs.getAttribute("itemdata");            
            
            //カートに追加から遷移しなかった場合アイテムページに遷移
            String ac = request.getParameter("ac");
            if(ac == null || hs.getAttribute("ac") == null ||  !ac.equals(hs.getAttribute("ac").toString())){
                response.sendRedirect("Item?itemcode=" + item.itemcode);
            }else{
                //このページの更新を防ぐ
                hs.removeAttribute("ac");
                //セッションから商品詳細を取り出し、カートクラスにマッピング
                int qty = Integer.parseInt(request.getParameter("qty"));
                CartData cd = new CartData(item,qty);
                ArrayList<CartData> cart = new ArrayList<CartData>();

                //ログインしていないならばゲストカートへカートを登録
                if(hs.getAttribute("login") == null){
                    //セッションにほかのカート情報があれば取得しなおす
                    if(hs.getAttribute("guestcart") != null){
                        cart = (ArrayList<CartData>)hs.getAttribute("guestcart");
                        for(CartData acd : cart){
                            //カートに同じ商品が入っていた場合、個数だけを変更する
                            if((acd.getItemcode().equals(cd.getItemcode()))){
                                qty += acd.getQuantity();
                                cart.remove(acd);
                                cd.setQuantity(qty);
                                break;
                            }                    
                        }                
                    }                
                    //ゲストカートにアイテム追加
                    cart.add(cd);
                    hs.setAttribute("guestcart", cart);                
                }else{
                    int userID = Integer.parseInt(hs.getAttribute("userID").toString());
                    if(hs.getAttribute("cart") != null){
                        cart = (ArrayList<CartData>)hs.getAttribute("cart");
                        for(CartData acd : cart){
                            //カートに同じ商品が入っていた場合、個数だけを変更する
                            if((acd.getItemcode().equals(cd.getItemcode()))){
                                qty += acd.getQuantity();
                                cart.remove(acd);
                                cd.setQuantity(qty);
                                CartDataDAO.getInstance().delete(userID, new CartDataDTO(cd));
                                break;
                            }                    
                        }                
                    }
                    //新しい商品のとき、カートに追加しDBに新しく挿入する
                    cart.add(cd);
                    CartDataDAO.getInstance().insert(userID, new CartDataDTO(cd));
                    hs.setAttribute("cart", cart);
                }
                //セッションに入れていたアイテムデータを削除
                hs.removeAttribute("itemdata");
                
                Log.printLog("addへ遷移","item:" + cd.getItemcode());            
                request.setAttribute("itemdata", item);
                request.setAttribute("qty",request.getParameter("qty"));
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
        }catch(Exception e){
            Error.errorProcess(request, response, e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
