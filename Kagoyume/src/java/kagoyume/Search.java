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
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user1
 */
public class Search extends HttpServlet {

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
            //フォームから検索語句を受け取りJsonファイルを取得
            if(request.getParameter("query") == null || "".equals(request.getParameter("query"))){
                throw new Exception("検索語句を入力してください");
            }
            
            //検索ページの取得
            int page = 1;
            if(request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            //検索ページの検索結果の取得
            String query = request.getParameter("query");
            String json = YahooConnect.getJson(YahooURL.itemSearch(query, (page - 1) * 20));
            
            //json文字列をパースしItemDataが入った配列を取得
            JsonParse jp = new JsonParse(json);
            int totalresults = jp.getTotalResults();
            ArrayList<ItemData> array = jp.getItemSearchResult();

            kagoyume.Log.printLog("Searchに遷移","検索語句:"+query);            

            request.getSession().setAttribute("query", query);
            request.getSession().setAttribute("page", page);                        
            request.setAttribute("query",query);
            request.setAttribute("page", page);
            request.setAttribute("totalresults", totalresults);
            request.setAttribute("searchresults", array);            
            request.getRequestDispatcher("search.jsp").forward(request, response);
            
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
