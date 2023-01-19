package com.company.vendingmachineportfolio.dao;

import com.company.vendingmachineportfolio.utilities.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CommonDAO {

    private Connection connection;
    final Properties props = new Properties();

    public Connection getConnection() throws SQLException, Exception {
        try {
            props.load(CommonDAO.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception ex){
            throw new Exception("Could not load props");
        }

        connection = JDBCUtil.getConnection(
                "com.mysql.cj.jdbc.Driver",
                this.props.getProperty("spring.datasource.url"),
                this.props.getProperty("spring.datasource.username"),
                this.props.getProperty("spring.datasource.password")
        );
        return connection;
    }

    public void cleanUp(Connection conn, ResultSet rs, Statement stmt) throws Exception {
        try {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(conn != null && conn.getAutoCommit()) conn.close();
        } catch (Exception ex){
            throw new SQLException(ex);
        }
    }
}
