/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;
import java.io.Serializable;

/**
 * 商品データを保持するクラス
 * @author user1
 */
public class ItemData implements Serializable{
    
    public String name;
    public String imageurl;
    public String bigimageurl;
    public String headline;
    public int price;
    public String itemurl;
    public String itemcode;
    public String description;
    public String storename;
    public double favorite;
    public int reviewer;
    public String caption;
    public String add1;
    public String add2;
    public String add3;
    
    public ItemData(){}

    
}
