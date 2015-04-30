/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "event")
@XmlRootElement
public class EventEntity extends BasicEntity implements Serializable{

    private String eventtype;
    private String eventname;
    private String eventcompany;
  

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="eventid")
    public EventcompanyEntity eventcompanyentity;
    
    public String getEventtype() {
        return eventtype;
    }

    public String getEventcompany() {
        return eventcompany;
    }

    public void setEventcompany(String eventcompany) {
        this.eventcompany = eventcompany;
    }


    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public EventcompanyEntity getEventcompanyentity() {
        return eventcompanyentity;
    }

    public void setEventcompanyentity(EventcompanyEntity eventcompanyentity) {
        this.eventcompanyentity = eventcompanyentity;
    }
    
    
}
