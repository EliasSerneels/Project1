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
public class EventCompanyPageDto {
    
    
    private EventCompanyDetailDto detail = new EventCompanyDetailDto();

    private List<EventCompanyListDetailDto> list = new LinkedList<>();
    
    
     public EventCompanyDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventCompanyDetailDto detail) {
        this.detail = detail;
    }

    public List<EventCompanyListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventCompanyListDetailDto> list) {
        this.list = list;
}}
