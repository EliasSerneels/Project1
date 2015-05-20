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
 * 
 */
public class EventTypePageDto {

    private EventTypeDetailDto detail = new EventTypeDetailDto();
    

    private List<EventTypeListDetailDto> list = new LinkedList<>();
    
    
     public EventTypeDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventTypeDetailDto detail) {
        this.detail = detail;
    }

    public List<EventTypeListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventTypeListDetailDto> list) {
        this.list = list;
}}
