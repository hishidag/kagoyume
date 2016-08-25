/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author user1
 */
public class YahooURL {
    private final static String APPID = "YahooAPPID";
                
    /**
     * @param itemcode 商品の一意なコード
     * @return 商品の詳細情報が記載されたJsonファイルURL
     * @throws  UnsupportedEncodingException "UTF-8"というエンコード形式がない時の例外
     */
    public static String itemDetail(String itemcode) throws UnsupportedEncodingException{
        itemcode = java.net.URLEncoder.encode(itemcode, "UTF-8");
        return "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid="+APPID + "&itemcode="+ itemcode +"&responsegroup=large" + "&image_size=300";
    }

    /**
     * @param query 検索語句
     * @param offset 検索結果を何件目から表示するか たとえば20だと20件目から表示される
     * @return 検索結果のJsonファイルURL
     * @throws  UnsupportedEncodingException "UTF-8"というエンコード形式がない時の例外
     */
    public static String itemSearch(String query,int offset) throws UnsupportedEncodingException{
        query = java.net.URLEncoder.encode(query,"UTF-8");
        return "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid="+APPID + "&query=" + query + "&offset=" + offset;    
    }
        
}
