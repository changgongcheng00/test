import java.sql.*;

public class SqlTest {
    public static void main(String[] args) {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2.获取数据库连接
        //jdbc:mysql://hostname:port/databasename
        //jdbc:oracle:thin:@hostname:port:databasename
        //DriverManager.getConnection(String url,String user,String pass);
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/select_test","root","123456");
        //3.创建statement对象
//        createStatement();
//        prepareStatement(String sql);
        Statement stmt = conn.createStatement();
        //4.使用Statement执行SQL语句
//        execute();
//        executeUpdate();
        ResultSet rs = stmt.executeQuery("");
        PreparedStatement pstmt = stmt.prepareStatement("..?");
        pstmt.setString("1");
        pstmt.executeLargeUpdate();

        //CallableStatement 调用存储过程
    }

}
