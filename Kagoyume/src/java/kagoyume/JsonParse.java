/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import net.arnx.jsonic.JSON;

/**
 * Json文字列を解析するクラス
 * @author user1
 */
public class JsonParse {
    Map<String, Map<String, Object>> root;                    
    
    public JsonParse(String json){
        root = JSON.decode(json);
    }
    
    //検索結果数を返す
    public int getTotalResults(){
        return Integer.valueOf(root.get("ResultSet").get("totalResultsAvailable").toString());                    
    }
    
    //商品検索結果を取得
    public ArrayList getItemSearchResult()
        throws Exception{
        //返却用のItemData配列
        ArrayList<ItemData> array = new ArrayList<ItemData>();
        
        //見つからなかった場合の例外
        if(Integer.valueOf(root.get("ResultSet").get("firstResultPosition").toString()) == 0){
            throw new Exception("検索結果がありませんでした");
        }
        
        //検索結果数が0件でなければ処理をする
        if(getTotalResults() != 0 ){
            //totalResultsReturned = Jsonファイルにある検索結果件数に到達するまでループ。
            for(int i = 0; i < ((BigDecimal) root.get("ResultSet").get("totalResultsReturned")).intValue(); i++){
                ItemData item = new ItemData();
                //コンパイラによる警告を無視
                @SuppressWarnings("unchecked")
                //i番目の商品データを入手
                Map<String, Object> result = ((Map<String, Object>)(
                                                (Map<String, Map<String, Object>>)root.get("ResultSet").get("0")
                                                    ).get("Result").get(Integer.toString(i)));

                //値を取得
                @SuppressWarnings("unchecked")
                String name = result.get("Name").toString();
                String imageurl = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
                int price = Integer.parseInt(((Map<String, Object>)result.get("Price")).get("_value").toString());                
                String itemcode = result.get("Code").toString();
                double favorite = Double.parseDouble(((Map<String,Object>)result.get("Review")).get("Rate").toString());
                int reviewer = Integer.parseInt(((Map<String,Object>)result.get("Review")).get("Count").toString());
                
                item.name = name;
                item.imageurl = imageurl;
                item.price = price;
                item.itemcode = itemcode;
                item.favorite = favorite;
                item.reviewer = reviewer;
                array.add(item);      
            }
        
        }
        
        return array;
    }
    
    //商品詳細を取得
    public ItemData getItemDetail()
        throws Exception{
        //返却用のItemData
        ItemData item = new ItemData();
        
        //見つからなかった場合の例外
        if(Integer.valueOf(root.get("ResultSet").get("firstResultPosition").toString()) == 0){
            throw new Exception("検索結果がありませんでした");
        }

        //コンパイラによる警告を無視
        @SuppressWarnings("unchecked")
        //i番目の商品データを入手
        Map<String, Object> result = ((Map<String, Object>)(
                                        (Map<String, Map<String, Object>>)root.get("ResultSet").get("0")
                                            ).get("Result").get("0"));

        //値の取得
        @SuppressWarnings("unchecked")
        String name = result.get("Name").toString();
        String headline = result.get("Headline").toString();
        String imageurl = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
        String bigimageurl = ((Map<String, Object>)result.get("ExImage")).get("Url").toString();        
        int price = Integer.parseInt(((Map<String, Object>)result.get("Price")).get("_value").toString());                                
        String itemcode = result.get("Code").toString();
        String storename = ((Map<String,Object>)result.get("Store")).get("Name").toString();
        double favorite = Double.parseDouble(((Map<String,Object>)result.get("Review")).get("Rate").toString());
        int reviewer = Integer.parseInt(((Map<String,Object>)result.get("Review")).get("Count").toString());
        String caption = result.get("Caption").toString();
        String add1 = result.get("Additional1").toString();
        String add2 = result.get("Additional2").toString();
        String add3 = result.get("Additional3").toString();
        
        item.name = name;
        item.headline = headline;
        item.price = price;
        item.imageurl = imageurl;
        item.bigimageurl = bigimageurl;
        item.itemcode = itemcode;
        item.storename = storename;
        item.favorite = favorite;
        item.reviewer = reviewer;                      
        item.caption = caption;
        item.add1 = add1;
        item.add2 = add2;
        item.add3 = add3;
                
        return item;
    }
        
}
