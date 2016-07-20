/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user1
 */
public class AccessCheck {
    //アクセスチェッカを作成
    public static void makeAccessCheck(HttpServletRequest request){
        HttpSession hs = request.getSession();
        int ac = (int)(Math.random() * 1000);
        hs.setAttribute("ac",ac);
        request.setAttribute("ac", ac);
    }

}


