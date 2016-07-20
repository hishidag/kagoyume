/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author user1
 * フォームからの入出力されるデータを格納するBeansオブジェクト
 * DTOからの変換、逆に、DTOへの変換を行うメソッドを保持する
 */
public class UserData implements Serializable{
    private String name = ""; 
    private String password = "";
    private String mail = "";
    private String address = "";
    private int total = 0;
    private String newDate;
    
    
    public UserData(){}
    
    public UserData(String name,String pd,String mail,String ad){
        this.name = name;
        this.password = pd;
        this.mail = mail;
        this.address = ad;       
    }
    
    public UserData(UserDataDTO udd){
        this.name = udd.getName();
        this.password = udd.getPassword();
        this.mail = udd.getMail();
        this.address = udd.getAddress();
        this.total = udd.getTotal();
        if(udd.getNewdate() != null){
            this.newDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date(udd.getNewdate().getTime()));
        }
    }
    
    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}
    
    public String getPassword(){ return this.password;}
    public void setPassword(String pw){ this.password = pw;}
    
    public String getMail(){ return this.mail;}
    public void setMail(String mail){ this.mail = mail;}
    
    public String getAddress(){ return this.address;}
    public void setAddress(String ad){ this.address = ad;}
    
    public int getTotal(){ return this.total;}
    
    public String getNewdate(){ return this.newDate;}
        
    public ArrayList<String> checkForm(){
        ArrayList<String> array = new ArrayList();
        if(name == null || "".equals(name)){
            array.add("ユーザー名");
        }
        if(password == null || "".equals(password)){
            array.add("パスワード");
        }    
        if(mail == null || "".equals(mail)){
            array.add("メールアドレス");
        }
        if(address == null || "".equals(address)){
            array.add("住所");
        }        
        return array;
    }
    
}
