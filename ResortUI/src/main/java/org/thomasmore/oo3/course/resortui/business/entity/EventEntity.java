/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "event")
@XmlRootElement
public class EventEntity extends BasicEntity implements Serializable {

    private String eventtype;
    private String eventname;
    private String eventcompany;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private String customerName;
    private Date currentDate;

    private String startDateFormatted;
    private String endDateFormatted;

    @ManyToOne(targetEntity = EventtypeEntity.class, fetch = FetchType.EAGER)
    private EventtypeEntity type;
    
    @ManyToOne(targetEntity = EventcompanyEntity.class, fetch = FetchType.EAGER)
    private EventcompanyEntity company;
    
    @ManyToOne(targetEntity = LocationEntity.class, fetch = FetchType.EAGER)
    private LocationEntity location;
    
    @ManyToMany(targetEntity = StaffEntity.class, fetch = FetchType.EAGER)
    private List<StaffEntity> staff;

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public EventcompanyEntity getCompany() {
        return company;
    }

    public void setCompany(EventcompanyEntity company) {
        this.company = company;
    }

    public List<StaffEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffEntity> staff) {
        this.staff = staff;
    }

    public EventtypeEntity getType() {
        return type;
    }

    public void setType(EventtypeEntity type) {
        this.type = type;
    }

    public String getStartDateFormatted() {
        return startDateFormatted;
    }

    public void setStartDateFormatted(String startDateFormatted) {
        this.startDateFormatted = startDateFormatted;
    }

    public String getEndDateFormatted() {
        return endDateFormatted;
    }

    public void setEndDateFormatted(String endDateFormatted) {
        this.endDateFormatted = endDateFormatted;
    }

    private String imageID;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEventtype() {
        return eventtype;
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

    public String getEventcompany() {
        return eventcompany;
    }

    public void setEventcompany(String eventcompany) {
        this.eventcompany = eventcompany;
    }

    public Date getCurrentDate() {
        return currentDate;
    }
}
