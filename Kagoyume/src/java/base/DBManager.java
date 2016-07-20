/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 * @author user1
 * DB接続の管理クラス
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManager {    
    //DB接続用のアカウント名とパスワード
    private final static String USER = "hishidag"; 
    private final static String PASSWORD = "hishidag";
    
    /**
    *@return Connection データベースとのコネクション。呼び出した後はcloseすること。 
    */
    public static Connection getConnection() throws IllegalMonitorStateException{        
        Connection con = null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db",USER,PASSWORD);
            System.out.println("DBConnected!!");
            return con;
            
        }catch(ClassNotFoundException e){
            throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            throw new IllegalMonitorStateException();
        }
    }
    
}
