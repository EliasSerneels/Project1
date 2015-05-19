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
public class EventcompanyPageDto {
    
    
    private EventcompanyDetailDto detail = new EventcompanyDetailDto();

    private List<EventcompanyListDetailDto> list = new LinkedList<>();
    
    
     public EventcompanyDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventcompanyDetailDto detail) {
        this.detail = detail;
    }

    public List<EventcompanyListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventcompanyListDetailDto> list) {
        this.list = list;
}}
