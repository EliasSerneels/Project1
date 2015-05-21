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

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private String maxPeople;
    private int numberOfReservations;
    private String description;    
    
    @OneToMany(targetEntity = ReservationEntity.class, fetch = FetchType.EAGER, mappedBy = "bungalow")
    private List<ReservationEntity> reservations;
    
    @ManyToOne(targetEntity=ParkEntity.class,fetch = FetchType.EAGER)
    private ParkEntity park;

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public ParkEntity getPark() {
        return park;
    }

    public void setPark(ParkEntity park) {
        this.park = park;
    }

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

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
    
    public void addReservation() {
        numberOfReservations++;
    }
    
}
