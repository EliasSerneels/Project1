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
public class EventtypePageDto {

    private EventtypeDetailDto detail = new EventtypeDetailDto();

    private List<EventtypeListDetailDto> list = new LinkedList<>();
    
    
     public EventtypeDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventtypeDetailDto detail) {
        this.detail = detail;
    }

    public List<EventtypeListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventtypeListDetailDto> list) {
        this.list = list;
}}
