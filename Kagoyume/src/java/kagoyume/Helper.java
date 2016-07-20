/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *ヘルパークラス。Viewの補助に利用。
 * @author user1
 */
public class Helper {
    //ヘッダを取得
    public static final String header(HttpServletRequest request){
        HttpSession hs = request.getSession();
        String cart = " <a class=\"pure-menu-link\" href=\"Cart\">カート</a>";
        
        String query = "";
        if(request.getParameter("query") != null){
            query = request.getParameter("query");
        }
        String search = "<form action=\"Search\"><input type=\"search\" name=\"query\" placeholder=\"検索\"value=\""+ query +"\"style=\"height:30px;\">"
                + "<input type=\"image\" alt=\"検索\" src=\"img/search.jpeg\" style=\"right: 0;top:1px; height:34px;position:absolute;\"></form>\n";
        ArrayList<String> array = new ArrayList<String>();                
        array.add(search);
        //ログイン状態による分岐
        if(hs.getAttribute("login") == null){
            array.add("<a class=\"pure-menu-link\" href=\"Login\">ログイン</a>\n");
            array.add("<a class=\"pure-menu-link\" href=\"Registration\">新規登録</a>");
        }else{
            array.add("<a class=\"pure-menu-link\" href=\"Mydata\" style=\"padding:0;font-weight:bold\">" + hs.getAttribute("username").toString() + "様<div style=\"font-size:small;\">アカウントサービス</div></a>");            
            array.add("<a class=\"pure-menu-link\" href=\"Login\">ログアウト</a>\n");
        }
        array.add(cart);
        //先の文字列にリスト要素を付与
        String list = "";
        for(String stra: array){
            list += "<li class=\"pure-menu-item\">\n" + stra + "</li>\n";
        }
        
        return 
    "       <div class=\"pure-menu pure-menu-horizontal\">\n" +
    "           <a class=\"pure-menu-heading\" href=\"top.jsp\" style=\"font-weight:bold\">かごいっぱいのゆめ</a>\n" +       
    "           <ul class=\"pure-menu-list\" >\n" +
                    list+
    "           </ul>\n" +
    "       </div>\n";
    }

    //ログアウト処理をされると困るときのヘッダを取得
    public static final String logheader(){        
        return 
    "       <div class=\"pure-menu pure-menu-horizontal\">\n" +
    "           <a class=\"pure-menu-heading\" href=\"top.jsp\" style=\"font-weight:bold\">かごいっぱいのゆめ</a>\n" +       
    "           <ul class=\"pure-menu-list\" >\n" +
    "           </ul>\n" +
    "       </div>\n";
    }
    
    //検索画面の下部のページリンクを取得
    public static final String pages(HttpServletRequest request){
        String query = request.getAttribute("query").toString();
        int page = Integer.parseInt(request.getAttribute("page").toString());
        int result = Integer.parseInt(request.getAttribute("totalresults").toString());
        String first = "<a href=\"Search?query="+ query +"&page=" + 1 +"\">最初</a>";
        String back = "<a href=\"Search?query="+ query +"&page=" + (page - 1) +"\">戻る</a>";
        String next = "<a href=\"Search?query="+ query +"&page=" + (page + 1) +"\">次へ</a>";
        String end = "<a href=\"Search?query="+ query +"&page=" + 50 +"\">最後</a>";
        
        String pages = "";
        //現在のページから表示するページリンク数
        int left  = 3;
        int right = 3;
        //最大ページ数
        int max = 50;
        if(result / 20 <= max){
            max = (int) Math.ceil(result / 20.0) ;
        }
        
        //表示されるリンク数が合計7になるように調整
        if(page - left < 1){
            right -= page - left;
        }
        if(max - page < right){
            left += right - (max - page) ;
        }
        
        for(int i = -left ; i <= right; i++){            
            //現在のページにはリンクを貼らない
            if(page + i <= 0){ continue;}
            if(page + i > max){ break;}
            if( i == 0 ){
                pages += "<a disabled style=\"cursor:default\">" + page +"</a>";
                continue;
            }
            pages += "<a href=\"Search?query=" + query + "&page=" + (page + i) +"\">" + (page + i) +"</a>";
        }
        
        return "<div id=\"page\" align=\"center\">" + first + back + pages + next + end + "</div>";
        
    }
    
    //トップページへのリンクを取得
    public static final String top(){
        return "<div align=\"center\"><a href=\"top.jsp\">トップへもどる</a></div>";
    }
    
    //ユーザ情報ページへのリンクを取得
    public static final String mydata(){
        return "<a href=\"Mydata\">アカウント情報へ戻る</a>";
    }
    
}
