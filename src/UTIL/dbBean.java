package UTIL;

import java.sql.*;

public class dbBean {
    String dbURL = "jdbc:sqlserver://DESKTOP-DM870V1;databaseName=DB_DELICIAS";    
    String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private Connection dbCon;
    private String login = "sa";
    private String password = "123456789";

    public dbBean() {
        connect();
    }

    public boolean connect() {
        try {
            Class.forName(this.dbDriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("Error en class");
            return false;
        }
        try {
            dbCon = DriverManager.getConnection(this.dbURL, this.login, this.password);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    public void close() throws SQLException {
        dbCon.close();
    }

    public ResultSet execSQL(String sql) throws SQLException {
        Statement s = dbCon.createStatement();
        ResultSet r = s.executeQuery(sql);
        return (r == null) ? null : r;
    }

    public int updateSQL(String sql) throws SQLException {
        Statement s = dbCon.createStatement();
        int r = s.executeUpdate(sql);
        return (r == 0) ? 0 : r;
    }

}
