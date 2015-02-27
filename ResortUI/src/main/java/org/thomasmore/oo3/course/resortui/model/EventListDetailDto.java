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
    private String eventcompanyname;
    private String eventtypename;

    public String getId() {
        return id;
    }

    public String getEventcompanyname() {
        return eventcompanyname;
    }

    public String getEventtypename() {
        return eventtypename;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventcompanyname(String eventcompanyname) {
        this.eventcompanyname = eventcompanyname;
    }

    public void setEventtypename(String eventtypename) {
        this.eventtypename = eventtypename;
    }
    
    
    
}
