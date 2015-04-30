/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

/**
 *
 * @author Jeroen
 */
public class EventListDetailDto {

    private String id;
    private String eventname;
    private String eventcompany;
    private String eventtype;

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

    
    
    
}
