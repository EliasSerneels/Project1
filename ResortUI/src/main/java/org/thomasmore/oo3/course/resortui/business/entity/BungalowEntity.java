/*
 * Copyright (C) 2014 lucs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "bungalow")
@XmlRootElement
public class BungalowEntity extends BasicEntity {

    private String imageID;
    private String type;
    private int price;
    private String name;
    private String park;
    private String maxPeople;
    private int reservations;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getMaxpeople() {
        return maxPeople;
    }

    public void setMaxpeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }
    

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservaties) {
        this.reservations = reservaties;
    }
    
    public void addReservation() {
        reservations++;
    }
    
}
