/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
/**
 *
 * @author user1
 */
public class Loginprocess {
    
    public static void login(HttpServletRequest request,HttpServletResponse response) 
            throws SQLException, FileNotFoundException, ServletException, IOException{
            request.setCharacterEncoding("UTF-8");
        try{
            HttpSession hs = request.getSession();
            String user = request.getAttribute("username").toString();
            String passwd = request.getAttribute("passwd").toString();

            UserDataDTO udd = UserDataDAO.getInstance().login(user, passwd);
            if(udd != null){
                //セッションにログイン情報を登録
                hs.setAttribute("login", true);
                //mydataに遷移する際にuserIDで検索するため保持
                hs.setAttribute("userID", udd.getUserID());
                //ヘッダーに名前を表示するため保持
                hs.setAttribute("username", udd.getName());
                
                //カートの情報の復元
                CartDataDAO cda = CartDataDAO.getInstance();
                ArrayList<CartData> cart = cda.search(udd.getUserID());
                //ゲスト時のカートのデータがあればその処理をする。なければ何もしない。
                if(hs.getAttribute("guestcart") != null){
                    ArrayList<CartData> gcart = (ArrayList<CartData>)hs.getAttribute("guestcart");
                    //ゲストカートが空のときは何もしない
                    if(!gcart.isEmpty()){
                        //DBにカートがないならばゲストカートの内容だけをカートに代入し、DBに登録
                        if(cart.isEmpty()){
                            cart.addAll(gcart);
                            for(CartData c : gcart){
                                cda.insert(udd.getUserID(), new CartDataDTO(c));
                            }
                        }else{
                            for(int i = 0 ; i < gcart.size() ; i++){
                                boolean flug = true;
                                CartData gc = gcart.get(i);
                                //DBのカートに同じ内容がないかチェック
                                for(int j = 0; j < cart.size() ; j++){
                                    CartData c = cart.get(j);
                                    //同じ商品が入っていたときカートを更新
                                    if(gc.getItemcode().equals(c.getItemcode())){
                                        flug = false;
                                        c.setQuantity(gc.getQuantity() + c.getQuantity());
                                        cart.set(cart.indexOf(c),c);          
                                        cda.update(udd.getUserID(), new CartDataDTO(c));
                                        //同じ商品はあっても1つなので見つかった時点でbreak
                                        break;
                                    }
                                }
                                //なければカートを追加
                                if(flug){
                                    cart.add(gc);
                                    cda.insert(udd.getUserID(), new CartDataDTO(gc));
                                }
                            }
                        }   
                    }  
                }
                hs.removeAttribute("guestcart");
                hs.setAttribute("cart",cart);   
                Log.printLog("login ok","userID:" + udd.getUserID() + " username:" + udd.getName());
            }else{
                request.setAttribute("logerr", "ログインに失敗しました");
                //失敗したときに遷移元urlを送りなおす
                request.setAttribute("url", request.getParameter("url"));
                
                Log.printLog("login ng");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        }catch(Exception e){
            Error.errorProcess(request, response, e);
        }
    }
}
