/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user1
 */
public class Login extends HttpServlet {

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
            
            //ログイン状態で遷移した場合ログアウト
            if(hs.getAttribute("login") != null){
                Logoutprocess.logout(request);
                response.sendRedirect("top.jsp");
            }else{
                //loginボタンを押して遷移したかチェック
                //押していないならログインチェック
                if(request.getParameter("login") == null){
                    Logincheck.loginCheck(request, response);                
                }else{                
                    //ログイン処理
                    request.setAttribute("username", request.getParameter("username"));
                    request.setAttribute("passwd",request.getParameter("passwd"));
                    Loginprocess.login(request, response);
                    //Logincheckによって登録されたurlに遷移。登録されていないならばtopにリダイレクト。
                    if(request.getParameter("url") != null){
                        response.sendRedirect(request.getParameter("url"));
                    }else{
                        response.sendRedirect("top.jsp");
                    }
                }     
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
