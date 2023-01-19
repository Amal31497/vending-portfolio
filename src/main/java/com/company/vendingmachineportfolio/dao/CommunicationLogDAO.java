package com.company.vendingmachineportfolio.dao;

import com.company.vendingmachineportfolio.model.CommunicationLog;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunicationLogDAO extends CommonDAO{
    private CommunicationLog parse(ResultSet rs) throws SQLException {
        CommunicationLog log = new CommunicationLog();
        log.setId(rs.getInt("id"));
        log.setTimestamp(rs.getTimestamp("time_stamp"));
        log.setType(rs.getString("type"));
        log.setContent(rs.getString("content"));
        log.setLeadId(rs.getString("lead_id"));
        return log;
    }

    public void createCommunicationLog(CommunicationLog log) throws Exception {
        String query = "INSERT INTO vending_crm.communication_log " +
                "(time_stamp,type,content,lead_id) values(now(),?,?,?);";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setString(1, log.getType());
            stmt.setString(2, log.getContent());
            stmt.setString(3, log.getLeadId());
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error at createCommunicationLog() : " + ex);
        } finally {
            cleanUp(con, null, stmt);
        }
    }

    public ArrayList<CommunicationLog> findAllLogsWithLeadId(String leadId) throws Exception{
        String query = "SELECT * FROM communication_log where lead_id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);
        ResultSet rs = null;
        ArrayList<CommunicationLog> result = new ArrayList<>();
        try {
            stmt.setString(1,leadId);
            rs = stmt.executeQuery();
            while(rs.next()){
                result.add(parse(rs));
            }
        } catch (Exception ex){
            throw new SQLException("Error at findAllLogsWithLeadId() : " + ex);
        } finally {
            cleanUp(con, rs, stmt);
        }
        return result;
    }

    public CommunicationLog findLogById(Integer id) throws Exception {
        String query = "SELECT * FROM communication_log where id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);
        ResultSet rs = null;

        CommunicationLog result = new CommunicationLog();
        try {
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) result = this.parse(rs);
        } catch (Exception ex){
            throw new SQLException("Error at findLogById : " + ex);
        } finally {
            cleanUp(con, rs, stmt);
        }
        return result;
    }

    public void updateCommunicationLog(CommunicationLog communicationLog) throws Exception {
        CommunicationLog oldVersion;
        try {
            oldVersion = this.findLogById(communicationLog.getId());
        } catch(Exception ex) {
            throw new SQLException("Error at DAO.updateCommunicationLog : original log was not found in db");
        }

        if(oldVersion == null) return;

        String query = "UPDATE communication_log SET type=?, content=?, lead_id=? WHERE id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setString(1, communicationLog.getType() == null ? oldVersion.getType() : communicationLog.getType());
            stmt.setString(2, communicationLog.getContent() == null ? oldVersion.getContent() : communicationLog.getContent());
            stmt.setString(3, communicationLog.getLeadId() == null ? oldVersion.getLeadId() : communicationLog.getLeadId());
            stmt.setInt(4, communicationLog.getId());
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error at updateCommunicationLog() : " + ex);
        } finally {
            cleanUp(con, null, stmt);
        }
    }

    public void deleteLogsWithLeadId(String leadId) throws Exception {
        String query = "DELETE FROM communication_log WHERE lead_id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setString(1, leadId);
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error at deleteLogWithLeadId() : " + ex);
        } finally {
            cleanUp(con, null, stmt);;
        }
    }

    public void deleteLog(Integer logId) throws Exception {
        String query = "DELETE FROM communication_log WHERE id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setInt(1, logId);
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error at deleteLog() : " + ex);
        } finally {
            cleanUp(con, null, stmt);;
        }
    }
}
