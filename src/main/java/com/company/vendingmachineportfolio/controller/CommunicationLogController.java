package com.company.vendingmachineportfolio.controller;

import com.company.vendingmachineportfolio.dao.CommunicationLogDAOImpl;
import com.company.vendingmachineportfolio.model.CommunicationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class CommunicationLogController {

    @Autowired
    CommunicationLogDAOImpl communicationLogDAOImpl;

    @PostMapping("/api/communicationLog/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNewCommunicationLog(@RequestBody @Valid CommunicationLog communicationLog) throws Exception{
        communicationLogDAOImpl.createCommunicationLogImpl(communicationLog);
    }

    @GetMapping("/api/communicationLog/findLogListByLeadId/{leadId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CommunicationLog> getCommunicationLogsWithLeadId(@PathVariable String leadId) throws Exception {
        List<CommunicationLog> logList = communicationLogDAOImpl.findAllCommunicationLogsWithLeadIdImpl(leadId);
        return logList;
    }

    @GetMapping("/api/communicationLog/findByLogId/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommunicationLog getCommunicationLogById(@PathVariable Integer id) throws Exception {
        CommunicationLog log = communicationLogDAOImpl.findLogById(id);
        return log;
    }

    @PutMapping("/api/communicationLog/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCommunicationLog(@RequestBody @Valid CommunicationLog communicationLog) throws Exception {
        communicationLogDAOImpl.updateCommunicationLogImpl(communicationLog);
    }

    @DeleteMapping("/api/communicationLog/delete/{logId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCommunicationLog(@PathVariable Integer logId) throws Exception {
        communicationLogDAOImpl.deleteCommunicationLogImpl(logId);
    }
}
