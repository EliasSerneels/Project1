/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;
 

/**
 *
 * @author Jeroen
 * 
 */
public class EventtypeListDetailDto {

private String id;

private String eventTypeName;
private String eventName;
private String eventCompany;


    public String getEventname() {
        return eventName;
    }

    public void setEventname(String eventName) {
        this.eventName = eventName;
    }


    public String getEventcompany() {
        return eventCompany;
    }

    public void setEventcompany(String eventCompany) {
        this.eventCompany = eventCompany;
    }

    public String getEventtypename() {
        return eventTypeName;
    }

    public void setEventtypename(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }  

}