/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.text.SimpleDateFormat;

/**
 * BuyDataDTOから購入履歴表示用に変換するクラス。
 * BuyDataDTOにユーザからの情報を書き込むことはないので読み取り専用クラスとする。
 * @author user1
 */
public class BuyData {
    private final String itemcode;
    private final String itemname;
    private final String imgurl;
    private final int price;
    private final int quantity;
    private final String type;
    private final String buydate;
    
    //BuyDataDTO経由でのみ使用できる
    public BuyData(BuyDataDTO bdd){
        this.itemcode = bdd.getItemcode();
        this.itemname = bdd.getItemname();
        this.imgurl = bdd.getImgurl();
        this.price = bdd.getPrice();
        this.quantity = bdd.getQuantity();
        this.type = typeToString(bdd.getType());
        //タイムスタンプ型を読みやすい形に
        this.buydate = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分").format(bdd.getBuydate());
    }
    
    public String getItemcode(){ return this.itemcode;}
    public String getItemname(){ return this.itemname;}
    public String getImgurl(){ return this.imgurl;}
    public int getPrice(){ return this.price;}
    public int getQuantity(){ return this.quantity;}
    public String getType(){ return this.type;}
    public String getBuyDate(){ return this.buydate;}
    
    
    //送られてきた整数を対応する文字列に変換
    public static String typeToString(int type){ 
        switch(type){
            case 1:
                return "はこBOON";
            case 2:
                return "日本郵便";
            case 3:
                return "ヤマト運輸";
            case 4:
                return "佐川急便";
            default:
                return "";
        }
    }
        
}
