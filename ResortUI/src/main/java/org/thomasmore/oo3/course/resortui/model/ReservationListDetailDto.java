package org.thomasmore.oo3.course.resortui.model;

import java.util.Date;


public class ReservationListDetailDto {
    private String id;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private String bungalowName;
    private String customerName;
    private String parkName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBungalowName() {
        return bungalowName;
    }

    public void setBungalowName(String bungalowName) {
        this.bungalowName = bungalowName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }    

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
    
    
}