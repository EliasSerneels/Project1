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
public class EventtypedetailPageDto {

    private EventtypeDetailDto detail = new EventtypeDetailDto();

    private List<EventtypedetailListDetailDto> list = new LinkedList<>();

    private List<String> eventtypeList = new LinkedList<>();
    
    private List<String> eventcompanyList = new LinkedList<>();

    public List<String> getEventtypeList() {
        return eventtypeList;
    }
    public List<String> getEventcompanyList() {
        return eventcompanyList;
    }

    public void setEventtypeList(List<String> eventtypeList) {
        this.eventtypeList = eventtypeList;
    }
    
    public void setEventcompanyList(List<String> eventcomapny){
        this.eventcompanyList = eventcompanyList;
    }

    public EventtypeDetailDto getDetail() {
        return detail;
    }

    public void setDetail(EventtypeDetailDto detail) {
        this.detail = detail;
    }

    public List<EventtypedetailListDetailDto> getList() {
        return list;
    }

    public void setList(List<EventtypedetailListDetailDto> list) {
        this.list = list;
    }
}
