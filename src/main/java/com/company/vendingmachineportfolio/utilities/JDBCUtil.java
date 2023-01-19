package com.company.vendingmachineportfolio.utilities;

import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    public static Connection getConnection(String driverName, String url, String userName, String password) throws SQLException {
        Driver driver = null;
        Properties props = new Properties();

        try {
            props.put("user", userName);
            props.put("password", password);
            driver = (Driver) Class.forName(driverName).newInstance();
        } catch (Exception ex){
            throw new SQLException("getConnection(" + driverName + "," + url + ")" );
        }

        return driver.connect(url, props);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        close(rs);
        close(conn);
        close(stmt);
    }

    public static void close(Connection conn, Statement stmt){
        close(conn);
        close(stmt);
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (Exception ex){
                System.out.println("Could not close connection");
            }
        }
    }

    public static void close(Statement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (Exception ex){
                System.out.println("Could not close statement");
            }
        }
    }

    public static void close(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (Exception ex){
                System.out.println("Could not close result set");
            }
        }
    }

}
