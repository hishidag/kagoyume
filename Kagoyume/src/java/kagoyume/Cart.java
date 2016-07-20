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
public class Cart extends HttpServlet {

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
            
            //商品情報を配列に入れる
            ArrayList<CartData> cart = new ArrayList<CartData>();
            int total = 0;
            //ログインチェック
            if(hs.getAttribute("login") == null){
                if(hs.getAttribute("guestcart") != null){
                    cart = (ArrayList<CartData>)hs.getAttribute("guestcart");
                }
                //cartのページからdeleteが送られてきたらその商品をカートから消去
                if(request.getParameter("delete") != null){
                    String itemcode = request.getParameter("deleteitem");
                    for(CartData cd :cart){
                        if(cd.getItemcode().equals(itemcode)){
                            cart.remove(cart.indexOf(cd));
                            break;
                        }
                    }                
                } 
            
                //変更から遷移したときは設定した数値に個数を変更
                else if(request.getParameter("change") != null){
                    String itemcode = request.getParameter("changeqty");
                    int qty = Integer.parseInt(request.getParameter("qty"));
                    for(CartData cd :cart){
                        if(cd.getItemcode().equals(itemcode)){
                            if(qty <= 0){                            
                                cart.remove(cart.indexOf(cd));
                            }else{                            
                                cd.setQuantity(qty);
                                cart.set(cart.indexOf(cd),cd);
                            }
                            break;
                        }
                    }                
                }
            
                //カートを空にするから遷移したとき、カートをすべて消去
                else if(request.getParameter("alldelete") != null){                
                    cart.clear();
                }            
 
                for(CartData cd : cart){
                    total += cd.getPrice() * cd.getQuantity();
                }
                
                hs.setAttribute("guestcart", cart);
                request.setAttribute("total",total);
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("cart.jsp").forward(request, response);                
            //ログイン時の処理
            }else{           
                int userid = Integer.parseInt(hs.getAttribute("userID").toString());
                cart = (ArrayList<CartData>)hs.getAttribute("cart");
                
                //ログイン時はDB操作をする
                CartDataDAO cda = CartDataDAO.getInstance();     
            
                //cartのページからdeleteが送られてきたらその商品をカートから消去
                if(request.getParameter("delete") != null){
                    String itemcode = request.getParameter("deleteitem");
                    for(CartData cd :cart){
                        if(cd.getItemcode().equals(itemcode)){
                            cart.remove(cart.indexOf(cd));
                            cda.delete(userid, new CartDataDTO(cd));
                            break;
                        }
                    }                
                } 
            
                //変更から遷移したときは設定した数値に個数を変更
                else if(request.getParameter("change") != null){
                    String itemcode = request.getParameter("changeqty");
                    int qty = Integer.parseInt(request.getParameter("qty"));
                    for(CartData cd :cart){
                        if(cd.getItemcode().equals(itemcode)){
                            if(qty <= 0){                            
                                cart.remove(cart.indexOf(cd));
                                cda.delete(userid, new CartDataDTO(cd));
                            }else{                            
                                cd.setQuantity(qty);
                                cart.set(cart.indexOf(cd),cd);
                                cda.update(userid, new CartDataDTO(cd));
                            }
                            break;
                        }
                    }                
                }
            
                //カートを空にするから遷移したとき、カートをすべて消去
                else if(request.getParameter("alldelete") != null){                
                    cart.clear();
                    cda.deleteAll(userid);
                }            
                        
                //カートの変更が完了した後で合計を計算
                if(cart != null){
                    for(CartData c : cart){
                        total += c.getPrice() * c.getQuantity();
                    }
                }
                hs.setAttribute("total", total);
                hs.setAttribute("cart",cart);
                request.setAttribute("total",total);
                request.setAttribute("cart", cart);
                Log.printLog("Cart");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            
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

