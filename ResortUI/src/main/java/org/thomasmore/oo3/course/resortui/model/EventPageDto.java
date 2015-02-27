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
public class EventPageDto {
    
    private EventDetailDto detail = new EventDetailDto();

    private List<EventListDetailDto> list = new LinkedList<>();

    private List<String> eventcompanyList = new LinkedList<>();
    
    private List<String> eventtypeList = new LinkedList<>();

    public List<String> getEventcompanyList() {
        return eventcompanyList;
    }

    public List<String> getEventtypeList() {
        return eventtypeList;
    }
    
    public void setEventcompanyList(List<String> eventcompanyList) {
        this.eventcompanyList = eventcompanyList;
    }

    public void setEventtypeList(List<String> eventtypeList) {
        this.eventtypeList = eventtypeList;
    }
    
    public EventDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventDetailDto detail) {
        this.detail = detail;
    }

    public List<EventListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventListDetailDto> list) {
        this.list = list;

    
    }
}
