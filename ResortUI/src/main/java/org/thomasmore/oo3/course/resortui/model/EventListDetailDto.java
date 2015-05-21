/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

import java.util.Date;
import java.util.List;
import org.thomasmore.oo3.course.resortui.business.entity.StaffEntity;

public class EventListDetailDto {

    private String id;
    private String eventname;
    private String eventcompany;
    private String eventtype;
    private String startDateFormatted;
    private String endDateFormatted;
    private Date startDate;
    private Date endDate;   
    private Date startTime;
    private Date endTime;
    private String locationName;
    private String customerName;
    private Date currentDate;
    private String imageID;
    private List<StaffEntity> staff;
    private String staffString;
    
    public String getStaffString() {
        if (!staff.isEmpty()) {
            staffString = staff.get(0).getFullName();
            for (int i = 1; i < staff.size(); i++) {
                staffString += ", " + staff.get(i).getFullName();
            }
        } else {
            staffString = "Geen personeel toegewezen";
        }
        return staffString;
    }

    public List<StaffEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffEntity> staff) {
        this.staff = staff;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getId() {
        return id;
    }

    public String getEventcompany() {
        return eventcompany;
    }

    public String getEventtype() {
        return eventtype;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventcompany(String eventcompany) {
        this.eventcompany = eventcompany;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }  
    public Date getCurrentDate() {
        return currentDate;
    }
}
