package UTIL;

import java.sql.*;

public class dbBean {
    
    String dbURL = "jdbc:sqlserver://localhost;databaseName=DB_DELICIAS;integratedSecurity=true";
    String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private Connection dbCon;
    private String login = "";
    private String password = "";
    
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
            dbCon = DriverManager.getConnection(this.dbURL);
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
    
    public ResultSet execParamSQL(String sql, String[] params) throws SQLException {
        PreparedStatement ps = dbCon.prepareStatement(sql);
        if (params != null) {
            for (int i = 0, j = 1; i < params.length; i++, j++) {
                ps.setString(j, params[i]);
            }
        }
        ResultSet r = ps.executeQuery();
        return (r == null) ? null : r;
    }
    
    public int insertSQL(String sql, Object[] params) throws SQLException {
        int Id = 0;
        PreparedStatement ps = dbCon.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        if (params != null) {
            for (int i = 0, j = 1; i < params.length; i++, j++) {                
                if (params[i] instanceof String) {
                    ps.setString(j, params[i].toString());
                } else if (params[i] instanceof Integer) {
                    ps.setInt(j, Integer.parseInt(params[i].toString()));
                } else if (params[i] instanceof Double) {
                    ps.setDouble(j, Double.parseDouble(params[i].toString()));
                }                
            }
        }
        ps.executeUpdate();
        if (ps.getGeneratedKeys().next()) {
            Id = ps.getGeneratedKeys().getInt(1);
        }       
        
        return Id;
    }
    
    public int updateSQL(String sql) throws SQLException {
        Statement s = dbCon.createStatement();
        int r = s.executeUpdate(sql);
        return (r == 0) ? 0 : r;
    }
    
}
