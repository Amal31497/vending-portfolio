package com.company.vendingmachineportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "communicationLog")
public class CommunicationLog implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp timeStamp;
    private String type;
    private String content;
    private String leadId;

    public CommunicationLog(){};

    public CommunicationLog(Integer id, Timestamp timeStamp, String type, String content, String leadId) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.type = type;
        this.content = content;
        this.leadId = leadId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationLog that = (CommunicationLog) o;
        return Objects.equals(id, that.id) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(type, that.type) && Objects.equals(content, that.content) && Objects.equals(leadId, that.leadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStamp, type, content, leadId);
    }

    @Override
    public String toString() {
        return "CommunicationLog{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", leadId='" + leadId + '\'' +
                '}';
    }
}
