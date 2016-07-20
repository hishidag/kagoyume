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
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author user1
 */
public class CartDataDAO {
    
    public static CartDataDAO getInstance(){
        return new CartDataDAO();
    }
    
    public void insert(int userID,CartDataDTO cdd) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{            
            con = DBManager.getConnection();
            
            st = con.prepareStatement("INSERT INTO cart(userID,itemcode,itemname,quantity,imgurl,price) VALUE(?,?,?,?,?,?)");
            st.setInt(1,userID);
            st.setString(2,cdd.getItemcode());
            st.setString(3,cdd.getItemname());
            st.setInt(4,cdd.getQuantity());
            st.setString(5,cdd.getImgurl());
            st.setInt(6,cdd.getPrice());
            st.executeUpdate();
            st.close();
            Log.printLog("cart insert completed");

        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public ArrayList<CartData> search(int userID) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM cart WHERE userID = ? ORDER BY newDate");
            st.setInt(1,userID);
            ResultSet rs = st.executeQuery();
            ArrayList<CartData> cart = new ArrayList<CartData>();
            while(rs.next()){
                CartDataDTO cdd = new CartDataDTO();
                cdd.setItemcode(rs.getString(3));
                cdd.setItemname(rs.getString(4));
                cdd.setQuantity(rs.getInt(5));
                cdd.setImgurl(rs.getString(6));
                cdd.setPrice(rs.getInt(7));
                cdd.setNewdate(rs.getTimestamp(8));                
                cart.add(new CartData(cdd));
            }
            st.close();
            Log.printLog("cart search completed");

            return cart;
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void deleteAll(int userID) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("DELETE FROM cart WHERE userID = ?");
            st.setInt(1,userID);
            st.executeUpdate();
            st.close();
            Log.printLog("cart delete completed");

        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void delete(int userID,CartDataDTO cdd) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("DELETE FROM cart WHERE userID = ? and itemcode = ? ");
            st.setInt(1,userID);
            st.setString(2,cdd.getItemcode());
            st.executeUpdate();
            st.close();
            Log.printLog("cart delete completed");

        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void update(int userID,CartDataDTO cdd) throws SQLException,FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE cart SET quantity = ? , newDate = ? WHERE userID = ? and itemcode = ? ");
            st.setInt(1, cdd.getQuantity());
            st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            st.setInt(3,userID);
            st.setString(4,cdd.getItemcode());
            st.executeUpdate();
            st.close();
            Log.printLog("cart update completed");

        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
}
