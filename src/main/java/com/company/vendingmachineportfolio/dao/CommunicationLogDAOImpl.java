package com.company.vendingmachineportfolio.dao;

import com.company.vendingmachineportfolio.model.CommunicationLog;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CommunicationLogDAOImpl {

    CommunicationLogDAO communicationLogDAO = new CommunicationLogDAO();

    public void createCommunicationLogImpl(CommunicationLog communicationLog) throws Exception {
        try {
            communicationLogDAO.createCommunicationLog(communicationLog);
        } catch (Exception ex){
            throw new SQLException("Error at CommunicationLogDAOImpl.createCommunicationLogImpl()");
        }
    }

    public ArrayList<CommunicationLog> findAllCommunicationLogsWithLeadIdImpl(String leadId) throws Exception{
        ArrayList<CommunicationLog> result = new ArrayList<>();
        try {
             result = communicationLogDAO.findAllLogsWithLeadId(leadId);
        } catch (Exception ex){
            throw new SQLException("Error at CommunicationLogDAOImpl.findAllCommunicationLogsWithLeadIdImpl()");
        }
        return result;
    }

    public CommunicationLog findLogById(Integer id) throws Exception {
        CommunicationLog result;
        try {
            result = communicationLogDAO.findLogById(id);
        } catch (Exception ex){
            throw new SQLException("Error at CommunicationLogDAOImpl.findLogById()");
        }
        return result;
    }

    public void updateCommunicationLogImpl(CommunicationLog communicationLog) throws Exception {
        try {
            communicationLogDAO.updateCommunicationLog(communicationLog);
        } catch (Exception ex){
            throw new SQLException("Error at CommunicationLogDAOImpl.updateCommunicationLogImpl()");
        }
    }

    public void deleteCommunicationLogImpl(Integer communicationLogId) throws Exception {
        try {
            communicationLogDAO.deleteLog(communicationLogId);
        } catch (Exception ex){
            throw new SQLException("Error at CommunicationLogDAOImpl.deleteCommunicationLogImpl()");
        }
    }

}
