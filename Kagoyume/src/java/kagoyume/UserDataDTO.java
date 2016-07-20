/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.sql.Timestamp;
/**
 * @author user1
 * ユーザー情報を格納するBeansオブジェクト。型やフィールド名がＤＢと連携している
 * データベースからの格納、取り出しで取得されたデータを最初に格納する
 */
public class UserDataDTO {
    private int userID;
    private String name; 
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteFlg;
    
    public UserDataDTO(){}
    
    //UserData内の情報をマッピング
    public UserDataDTO(UserData ud){
        this.name = ud.getName();
        this.password = ud.getPassword();
        this.mail = ud.getMail();
        this.address = ud.getAddress();
    }
    
    public int getUserID(){ return this.userID;}
    public void setUserID(int id){ this.userID = id;}
    
    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}
    
    public String getPassword(){ return this.password;}
    public void setPassword(String pw){ this.password = pw;}
    
    public String getMail(){ return this.mail;}
    public void setMail(String mail){ this.mail = mail;}
    
    public String getAddress(){ return this.address;}
    public void setAddress(String ad){ this.address = ad;}
    
    public int getTotal(){ return this.total;}
    public void setTotal(int total){ this.total = total;}
    
    public Timestamp getNewdate(){ return this.newDate;}
    public void setNewdate(Timestamp nd){ this.newDate = nd;}
    
    public int getDeleteflag(){ return this.deleteFlg;}
    public void setDeleteflag(int df){ this.deleteFlg = df;}
    
    
    
}
