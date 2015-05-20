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
@Table(name = "eventtype")
@XmlRootElement
public class EventTypeEntity extends BasicEntity {

    private String eventName;

    public String getEventname() {
        return eventName;
    }

    public void setEventname(String eventName) {
        this.eventName = eventName;
    }
}
