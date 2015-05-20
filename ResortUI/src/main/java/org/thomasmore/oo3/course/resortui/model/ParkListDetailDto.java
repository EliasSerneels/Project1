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
package org.thomasmore.oo3.course.resortui.model;

import java.util.List;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.LocationEntity;

/**
 *
 * @author lucs
 */
public class ParkListDetailDto {

    private String id;

    private String name;
    private int capacity;
    private List<BungalowEntity> bungalows;
    private List<LocationEntity> locations;
    private String imageID;
    private String description;
    private String bungalowsString;
    private String locationsString;

    public String getBungalowString() {
        if (!bungalows.isEmpty()) {
            bungalowsString = bungalows.get(0).getName();
            for (int i = 1; i < bungalows.size(); i++) {
                bungalowsString += ", " + bungalows.get(i).getName();
            }
        } else {
            bungalowsString = "Geen bungalows aanwezig";
        }
        return bungalowsString;
    }
    
    public String getLocationsString() {        
        if (!locations.isEmpty()) {
            locationsString = locations.get(0).getLocationName();
            for (int i = 1; i < locations.size(); i++) {
                locationsString += ", " + locations.get(i).getLocationName();
            }
        } else {
            locationsString = "Geen locaties aanwezig";
        }
        return locationsString;
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

    public List<BungalowEntity> getBungalows() {
        return bungalows;
    }

    public void setBungalows(List<BungalowEntity> bungalows) {
        this.bungalows = bungalows;
    }

    public List<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
