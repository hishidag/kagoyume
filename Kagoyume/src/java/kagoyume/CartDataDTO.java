/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;
import java.sql.Timestamp;

/**
 *
 * @author user1
 */
public class CartDataDTO {
    private int cartID;
    private int userID;
    private String itemcode;
    private String itemname;
    private int quantity;
    private String imgurl;
    private int price;
    private Timestamp newDate;
    
    public CartDataDTO(){}
    
    public CartDataDTO(CartData cd){
        this.itemcode = cd.getItemcode();
        this.itemname = cd.getItemname();
        this.quantity = cd.getQuantity();
        this.imgurl = cd.getImgurl();
        this.price = cd.getPrice();       
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
    
    public Timestamp getNewdate(){ return this.newDate;}
    public void setNewdate(Timestamp t){ this.newDate = t;}
}
