/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 例外処理クラス
 * @author user1
 */
public class Error{
    
    public static void errorProcess(HttpServletRequest req,HttpServletResponse res,Exception e) throws FileNotFoundException{
        try{
            //ログ出力
            Log.printErrorLog(e);
            String errstr = "";
            if(e.toString().equals("java.lang.IllegalMonitorStateException")){
                errstr = "DBへの接続に失敗しました";
            }else if(e.toString().equals("java.sql.SQLException")){
                errstr = "DBの操作に失敗しました";
            }else if(e.getMessage() != null){
                errstr = e.getMessage();
            }else{
                errstr = "ページにつながりませんでした。";
            }
                        
            req.setAttribute("error",errstr);    
            req.getRequestDispatcher("error.jsp").forward(req, res);                
            
            }catch (ServletException ex){
                Log.printErrorLog(ex);
            }catch (IOException ex){
                Log.printErrorLog(ex);
            }
        }
    
}
