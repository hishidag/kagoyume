/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.text.SimpleDateFormat;
import java.io.Serializable;
/**
 *
 * @author user1
 */
public class CartData implements Serializable {
    private String itemcode;
    private String itemname;
    private int quantity;
    private String imgurl;
    private int price;
    private String newDate;
    
    CartData(ItemData id,int qty){
        this.itemcode = id.itemcode;
        this.itemname = id.name;
        this.quantity = qty;
        this.imgurl = id.imageurl;
        this.price = id.price;       
    }
    
    CartData(CartDataDTO cdd){
        this.itemcode = cdd.getItemcode();
        this.itemname = cdd.getItemname();
        this.quantity = cdd.getQuantity();
        this.imgurl = cdd.getImgurl();
        this.price = cdd.getPrice();
        //DBから引き上げたタイムスタンプを読みやすい形へ変換
        this.newDate = new SimpleDateFormat("yyyy年MM月dd日").format(cdd.getNewdate());
    }
    
    public String getItemcode(){ return this.itemcode;}
    public void setItemcode(String ic){ this.itemcode = ic;}
    
    public String getItemname(){ return this.itemname;}
    public void setItemname(String name){ this.itemname = name;}
    
    public int getQuantity(){ return this.quantity;}
    public void setQuantity(int qty){ this.quantity = qty;}
    
    public String getImgurl(){ return this.imgurl;}
    public void setImgurl(String img){ this.imgurl = img;}
    
    public int getPrice(){ return this.price;}
    public void setPrice(int price){ this.price = price;}
    
    public String getNewdate(){ return this.newDate;}
    

}
