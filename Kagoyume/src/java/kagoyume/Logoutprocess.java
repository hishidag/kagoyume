/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;
/**
 *
 * @author user1
 */
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class Logoutprocess {

    //セッションとクッキーを破棄します
    public static void logout(HttpServletRequest request) throws FileNotFoundException, SQLException{
            HttpSession hs = request.getSession();
            hs.invalidate();
            Cookie cookies[] = request.getCookies();
            for(Cookie c : cookies){
                c.setMaxAge(0);
            }
            Log.printLog("logout"); 
        }
    
}
