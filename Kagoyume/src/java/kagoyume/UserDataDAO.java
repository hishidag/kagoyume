/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import base.DBManager;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author user1
 * データベース処理系全般をつかさどるオブジェクト
 * 基本的にデータはUserDataDTO経由
 */
public class UserDataDAO {

    UserDataDAO(){}
    
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    public String encryptStr(String text) throws NoSuchAlgorithmException{
        MessageDigest md = null;
        StringBuilder sb = new StringBuilder();
            md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            byte[] passarray = md.digest();
            for(int i = 0;i < passarray.length; i++){
                String tmpStr = Integer.toHexString(passarray[i] & 0xff);
                
                if(tmpStr.length() == 1){
                    sb.append("0").append(tmpStr);
                }else{
                    sb.append(tmpStr);
                }
            }
        return sb.toString();
    }
    
    public void insert(UserDataDTO udd) throws SQLException,FileNotFoundException,IllegalMonitorStateException, NoSuchAlgorithmException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, udd.getName());
            st.setString(2, encryptStr(udd.getPassword()));
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
            Log.printLog("userdatainsert completed");

        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    public Boolean canIUseThisName(String name) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        Boolean flag = true;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE name = ? and deleteFlg = 0 ");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            flag = rs.next();
            Log.printLog("canIUseThisName completed");
            st.close();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return flag;
    }
    
    public UserDataDTO login(String name , String passwd) throws SQLException,FileNotFoundException,IllegalMonitorStateException, NoSuchAlgorithmException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE name = ? and password = ? and deleteFlg = 0");
            st.setString(1, name);
            st.setString(2, encryptStr(passwd));
            ResultSet rs = st.executeQuery();                        
            UserDataDTO udd = new UserDataDTO();                
            if(rs.next()){
                udd.setUserID(rs.getInt(1));
                udd.setName(rs.getString(2));
            }else{
                return null;
            }
            st.close();
            
            Log.printLog("login completed");
            
            return udd;
            
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void delete(int id) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET deleteFlg = 1 where userID = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            Log.printLog("userdatadelete completed");

        }finally{
            if(con != null){
                con.close();
            }
        } 
    }
    
    public void update(UserDataDTO ud,int id) throws SQLException, FileNotFoundException,IllegalMonitorStateException, NoSuchAlgorithmException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET name = ?, password = ?, mail = ?, address = ? where userID = ?");
            st.setString(1, ud.getName());
            st.setString(2, encryptStr(ud.getPassword()));
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, id);
            st.executeUpdate();
            st.close();
            Log.printLog("userdataupdate completed");

        }finally{
            if(con != null){
                con.close();
            }
        } 
    }
    
    public Boolean canIUpdateThisName(String name, int id) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        Boolean flag = true;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE name = ? and deleteFlg = 0 and userID != ? ");
            st.setString(1, name);
            st.setInt(2,id);
            ResultSet rs = st.executeQuery();
            flag = rs.next();
            Log.printLog("canIUpdateThisName completed");
            st.close();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return flag;
    }
    
    public UserDataDTO searchByid(int id) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        UserDataDTO udd = new UserDataDTO();
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE deleteFlg = 0 and userID = ? ");
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            rs.next();
            udd.setUserID(rs.getInt(1));
            udd.setName(rs.getString(2));
            udd.setPassword(rs.getString(3));
            udd.setMail(rs.getString(4));
            udd.setAddress(rs.getString(5));
            udd.setTotal(rs.getInt(6));
            udd.setNewdate(rs.getTimestamp(7));
            
            Log.printLog("userdatasearchByid completed");
            st.close();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return udd;
    }
    
    public void updateTotal(int total,int userID) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET total = total + ? WHERE userID = ?");
            st.setInt(1,total);
            st.setInt(2,userID);
            st.executeUpdate();
            st.close();
            Log.printLog("userdata updatetotal completed");

        }finally{
            if(con != null){
                con.close();
            }
        }

    }

}
