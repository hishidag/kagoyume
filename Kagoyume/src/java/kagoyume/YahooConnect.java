/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * YahooAPIに接続するための処理
 * @author user1
 */
public class YahooConnect {
    //json文字列を入れるための変数
    static String json = "";               
    /**
     * @param urlstr YahooURLからURLを受け取ります
     * @return YahooURL先から得たJson形式の文字列を返します
     * @throws FileNotFoundException Logクラスにおいて指定したディレクトリが存在しないときに発生します
     */
    public static String getJson(String urlstr) throws FileNotFoundException {
        
        try{
            URL url = new URL(urlstr);

            HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

            StringBuilder responseBuilder = new StringBuilder();
            while (true){
                String line = reader.readLine();
                if ( line == null ){ break; }
                responseBuilder.append(line);                 
            }
            
            reader.close();            
            urlconn.disconnect();
            json = responseBuilder.toString();
                    
        }catch(Exception e){
            kagoyume.Log.printErrorLog(e);
        }        
    
        return json;
    }
    
    
}
