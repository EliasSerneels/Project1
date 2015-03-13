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
  

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="eventid")
    public EventcompanyEntity eventcompanyentity;
    
    public String getEventtype() {
        return eventtype;
    }


    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }
    
    
}
