/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class Registrationcomplete extends HttpServlet {

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
            
            String ac = request.getParameter("ac");            
            if(ac == null || hs.getAttribute("ac") == null ||  !hs.getAttribute("ac").toString().equals(ac)){
                response.sendRedirect("top.jsp");
            }else{
            //このページの更新を防ぐ    
            hs.removeAttribute("ac");
            UserData ud = (UserData)hs.getAttribute("ud");
            
            //UserDataDTOにマッピングしDBに新規登録
            UserDataDTO udd = new UserDataDTO(ud);
            UserDataDAO.getInstance().insert(udd);
            
            //リクエストにUserData登録
            request.setAttribute("ud",ud);
            //セッションを破棄
            hs.removeAttribute("ud");
            
            request.setAttribute("username", ud.getName());
            request.setAttribute("passwd", ud.getPassword());            
            //ログイン処理
            Loginprocess.login(request, response);                       
            request.getRequestDispatcher("registrationcomplete.jsp").forward(request, response);
            }
            
        }catch(Exception ex) {
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
