/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "eventtypedetail")
@XmlRootElement
public class EventtypedetailEntity extends BasicEntity implements Serializable {

    private String Eventtypename;
    private String Eventcompany;

    public String getEventtypename() {
        return Eventtypename;
    }

    public String getEventcompany() {
        return Eventcompany;
    }

    public void setEventtypename(String Eventtypename) {
        this.Eventtypename = Eventtypename;
    }

    public void setEventcompany(String Eventcompany) {
        this.Eventcompany = Eventcompany;
    }

}
