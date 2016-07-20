/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user1
 */
public class Buycomplete extends HttpServlet {

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
        try{
            HttpSession hs = request.getSession();
            
            //購入するから遷移しなかったときトップページへリダイレクト
            String ac = request.getParameter("ac");
            if(ac == null || hs.getAttribute("ac") == null || !ac.equals((hs.getAttribute("ac")).toString())){
                response.sendRedirect("top.jsp");
            }else{
                //更新を防ぐ
                hs.removeAttribute("ac");
                
                int type = Integer.parseInt(request.getParameter("type"));
                int userID = Integer.parseInt(hs.getAttribute("userID").toString());

                //商品情報を配列に入れる
                ArrayList<CartData> cart = (ArrayList<CartData>)hs.getAttribute("cart");
                for(CartData c : cart){
                    //購入履歴を挿入
                    BuyDataDAO.getInstance().insert(new BuyDataDTO(userID,type,c));
                }
                //userの総額を更新
                UserDataDAO.getInstance().updateTotal(Integer.parseInt(hs.getAttribute("total").toString()), userID);

                //カートの情報を削除
                CartDataDAO.getInstance().deleteAll(userID);

                request.setAttribute("type",type);
                request.setAttribute("total", hs.getAttribute("total"));
                request.setAttribute("cart",cart);

                //セッションからカートの情報を破棄            
                hs.removeAttribute("cart");            
                hs.removeAttribute("total");
                
                Log.printLog("buycomplete");
                request.getRequestDispatcher("buycomplete.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Error.errorProcess(request, response, ex);
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
