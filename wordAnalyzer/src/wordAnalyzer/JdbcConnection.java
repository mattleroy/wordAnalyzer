package wordAnalyzer;
import java.sql.*;

public class JdbcConnection{
	
    private static final String db_url = "jdbc:sqlserver://localhost\\DESKTOP-PVMAKLL\\Exo;databaseName=wordAnalyzer;integratedSecurity=true;";
    private static final String db_user = "admin";
    private static final String db_pass = "password1";
    
    
    public void addWord(String word, int count) {
        System.out.println("Test Func Successful");
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
    	
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	
        try {
            Connection connection = DriverManager.getConnection(db_url, db_user, db_pass);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        	e.printStackTrace();
        }
    }
}