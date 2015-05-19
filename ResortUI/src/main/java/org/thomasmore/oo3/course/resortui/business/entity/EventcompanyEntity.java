/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "eventcompany")
@XmlRootElement
public class EventCompanyEntity extends BasicEntity implements Serializable{


private String name;
private String city;
private String street;
private String phone;
private String contact;
private String imageID;
private int totalnumberevents;

    public int getTotalnumberevents() {
        return totalnumberevents;
    }

    public void setTotalnumberevents(int totalnumberevents) {
        this.totalnumberevents = totalnumberevents;
    }


    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }




    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    
}
