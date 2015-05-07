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
public class EventtypeListDetailDto {

private String id;

private String eventtypename;
private String eventname;
private String eventcompany;

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

    public String getEventtypename() {
        return eventtypename;
    }

    public void setEventtypename(String eventtypename) {
        this.eventtypename = eventtypename;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }  

}