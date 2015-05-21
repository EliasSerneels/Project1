/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elliot
 */
@Entity
@Table(name = "location")
@XmlRootElement
public class LocationEntity extends BasicEntity implements Serializable {

private String locationName;

    @ManyToOne(targetEntity = ParkEntity.class, fetch = FetchType.EAGER)
    private ParkEntity park;
    
    @OneToMany(targetEntity = EventEntity.class, fetch = FetchType.EAGER, mappedBy = "location")
    private List<EventEntity> events;

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    public ParkEntity getPark() {
        return park;
    }

    public void setPark(ParkEntity parkje) {
        this.park = parkje;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
