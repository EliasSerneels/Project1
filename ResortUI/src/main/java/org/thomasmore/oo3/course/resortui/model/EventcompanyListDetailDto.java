/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

import java.util.List;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;

/**
 *
 * @author Jeroen
 */
public class EventcompanyListDetailDto {

private String id;
private String name;
private String city;
private String street;
private String phone;
private String contact;
public List<EventEntity> evententity;

    public List<EventEntity> getEvententity() {
        return evententity;
    }

    public void setEvententity(List<EventEntity> evententity) {
        this.evententity = evententity;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
