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
public class EventTypeDetailListDetailDto {

    private String id;
    private String Eventtypename;
    private String Eventcompany;

    public String getId() {
        return id;
    }
    
    

    public String getEventtypename() {
        return Eventtypename;
    }

    public String getEventcompany() {
        return Eventcompany;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public void setEventtypename(String Eventtypename) {
        this.Eventtypename = Eventtypename;
    }

    public void setEventcompany(String Eventcompany) {
        this.Eventcompany = Eventcompany;
    }
    
    
}
