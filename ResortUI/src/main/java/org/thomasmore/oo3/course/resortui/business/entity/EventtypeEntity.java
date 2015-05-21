/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "eventtype")
@XmlRootElement
public class EventtypeEntity extends BasicEntity {
    
    private String typeName;

    @OneToMany(targetEntity=EventEntity.class,fetch = FetchType.EAGER, mappedBy = "type")
    private List<EventEntity> events;

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
    
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String eventname) {
        this.typeName = eventname;
    }
}
