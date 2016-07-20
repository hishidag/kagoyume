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
public class Myupdateresult extends HttpServlet {

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
            //アクセスチェック
            String ac = request.getParameter("ac");
            if(ac == null || hs.getAttribute("ac") == null || !ac.equals((hs.getAttribute("ac")).toString())){
                throw new Exception("不正なアクセスです");
            }else{

                //セッションからuserIDを取得
                int userID = (Integer)hs.getAttribute("userID");            

                //myupdateから情報を引き上げUserDataに格納
                String name = request.getParameter("name");
                String passwd = request.getParameter("passwd");
                String newpasswd = request.getParameter("newpasswd");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                UserData ud = new UserData(name,passwd,email,address);

                //エラー文保持変数
                String errstr = "";

                //DBにアクセスするためのインスタンス生成
                UserDataDAO uda = UserDataDAO.getInstance();
                //ユーザー名重複チェック
                if(uda.canIUpdateThisName(name,userID)){
                    errstr += "そのユーザー名は他の方が使用しています<br>";
                }            
                //以前まで使用していたパスワードと一致するかチェック
                if(UserDataDAO.getInstance().login(hs.getAttribute("username").toString(), passwd) == null){
                    errstr = "パスワードが一致しませんでした<br>";
                }
                //新パスワードが入力されているか、確認用と一致するかチェック
                if(newpasswd.equals("")){
                    errstr = "新パスワードが入力されていません<br>";
                }else if(!newpasswd.equals(request.getParameter("passwdconfirm"))){
                    errstr = "新パスワードが確認用と一致しません<br>";
                }
                //記入漏れがないかチェック
                if(ud.checkForm() != null){
                    for(String str : ud.checkForm()){
                        errstr += str + "が未記入です<br>";
                    }
                }

                //今までのエラー文を登録
                request.setAttribute("cannotuse", errstr);
                //リクエストに登録
                request.setAttribute("ud", ud);

                //エラーがないなら更新
                if(errstr.equals("")){
                    //UserDataDTOにマッピング
                    UserDataDTO udd = new UserDataDTO(ud);            
                    uda.update(udd, userID);                
                    request.setAttribute("username",ud.getName());
                    request.setAttribute("passwd", ud.getPassword());
                    Loginprocess.login(request, response);
                    //ブラウザの更新を防ぐ
                    hs.removeAttribute("ac");    
                }
                //ログ出力            
                kagoyume.Log.printLog("myupdateresult");
                request.getRequestDispatcher("myupdateresult.jsp").forward(request, response);
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
