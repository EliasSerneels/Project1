/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "event")
@XmlRootElement
public class EventEntity extends BasicEntity{

    private String eventcompany;
    private String eventtype;

    public String getEventcompany() {
        return eventcompany;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventcompany(String eventcompany) {
        this.eventcompany = eventcompany;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }
    
    
}
