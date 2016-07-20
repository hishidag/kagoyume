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
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.*;


/**
 *
 * @author user1
 */
public class BuyDataDAO {
    
    public static BuyDataDAO getInstance(){
        return new BuyDataDAO();
    }
    
    //購入履歴を挿入
    public void insert(BuyDataDTO bdd) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO buy_t(userID,itemCode,itemname,imgurl,price,quantity,type,buyDate) VALUES(?,?,?,?,?,?,?,?)");
            st.setInt(1, bdd.getUserID());
            st.setString(2,bdd.getItemcode());
            st.setString(3,bdd.getItemname());
            st.setString(4,bdd.getImgurl());
            st.setInt(5,bdd.getPrice());
            st.setInt(6,bdd.getQuantity());
            st.setInt(7,bdd.getType());
            st.setTimestamp(8,new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            st = con.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            
            st.close();
            Log.printLog("buydata insert completed");
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    //ユーザIDから購入履歴を取得    
    public LinkedHashMap<String,ArrayList<BuyData>> search(int userID) throws SQLException, FileNotFoundException,IllegalMonitorStateException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
            st.setInt(1,userID);
            ResultSet rs = st.executeQuery();
            
            LinkedHashMap<String,ArrayList<BuyData>> map = new LinkedHashMap<String,ArrayList<BuyData>>();
            while(rs.next()){
                BuyDataDTO bdd = new BuyDataDTO();
                bdd.setItemcode(rs.getString(3));
                bdd.setItemname(rs.getString(4));
                bdd.setImgurl(rs.getString(5));
                bdd.setPrice(rs.getInt(6));
                bdd.setQuantity(rs.getInt(7));
                bdd.setType(rs.getInt(8));
                bdd.setBuydate(rs.getTimestamp(9));
                BuyData bd = new BuyData(bdd);
                //購入時間によって仕分けする。同じ購入時間であれば、その時間をキーとしたオブジェクト配列に収納。
                if(map.keySet().contains(bd.getBuyDate())){
                    ArrayList<BuyData> a = map.get(bd.getBuyDate());
                    a.add(bd);
                    map.put(bd.getBuyDate(),a);
                }else{
                    ArrayList<BuyData> array = new ArrayList<BuyData>();
                    array.add(bd);
                    map.put(bd.getBuyDate(),array);
                }
            }
            Log.printLog("buydata search complete");
            
            return map;
        }finally{
            if(con != null){
                con.close();
            }
        }
        
    }
    
}
