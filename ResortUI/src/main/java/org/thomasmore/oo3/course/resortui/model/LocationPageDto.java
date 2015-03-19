/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jeroen
 */
public class LocationPageDto {
 
    
    private LocationDetailDto detail = new LocationDetailDto();

    private List<LocationListDetailDto> list = new LinkedList<>();

    public LocationDetailDto getDetail() {
        return detail;
    }

    public void setDetail(LocationDetailDto detail) {
        this.detail = detail;
    }

    public List<LocationListDetailDto> getList() {
        return list;
    }

    public void setList(List<LocationListDetailDto> list) {
        this.list = list;
    }
}
