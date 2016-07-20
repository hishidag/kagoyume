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

/**
 *
 * @author user1
 */
public class Registrationconfirm extends HttpServlet {

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
            
            if(request.getParameter("regist") == null){
                response.sendRedirect("top.jsp");
            }else{
            
            String name = request.getParameter("name");
            String passwd = request.getParameter("passwd");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            UserData ud = new UserData(name,passwd,email,address);
            //セッションに登録
            hs.setAttribute("ud",ud);
            //リクエストに登録
            request.setAttribute("ud", ud);
            
            //ほかのアカウントと名前が重複していないか確かめる。trueのとき重複している。
            Boolean flag = UserDataDAO.getInstance().canIUseThisName(name);
            request.setAttribute("cannotuse",flag);
            
            //アクセスチェッカの登録
            AccessCheck.makeAccessCheck(request);            
            
            request.getRequestDispatcher("registrationconfirm.jsp").forward(request, response);
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
