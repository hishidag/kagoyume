/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user1
 * Log作成クラスです
 */
public class Log {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
    private static final String LOG = "C:\\xampp\\tomcat\\camp\\Kagoyume\\web\\WEB-INF\\log\\log.log";    

    /**
     *@param args オブジェクトを渡すと、ログの処理した時点とともに
     *             そのオブジェクトの文字列が記載されたログが作成されます
     *@throws FileNotFoundException 
     */   
    public static void printLog(Object... args) throws FileNotFoundException{
        System.setOut( new PrintStream(new FileOutputStream(LOG,true)));
        for(Object log : args){
            System.out.println( getDateTime() + log.toString());
        }
        System.out.close();
    }
    
    /**
     *@param e 例外を渡すと例外内容とスタックトレースがログに出力されます
     *@throws FileNotFoundException  
     */
    public static void printErrorLog(Exception e) throws FileNotFoundException{
        System.setErr( new PrintStream(new FileOutputStream(LOG,true)));
        System.err.println(getDateTime() + "[!ERROR]" +e.toString());        
        //スタックトレースを記載
        for(StackTraceElement ste : e.getStackTrace()){
            System.err.println(ste.toString());
        }
        System.err.close();
    }
    
    // 現在日時をフォーマットして文字列で返す
    private static String getDateTime(){
        return FORMATTER.format( new Date() );
    }
    
}
