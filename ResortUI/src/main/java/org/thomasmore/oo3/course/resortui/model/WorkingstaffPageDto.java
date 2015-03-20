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
public class WorkingstaffPageDto {

    
    private WorkingstaffDetailDto detail = new WorkingstaffDetailDto();
    private List<WorkingstaffListDetailDto> list = new LinkedList<>();
    private List<String> eventList = new LinkedList<>();
    private List<String> staffList = new LinkedList<>();

    public WorkingstaffDetailDto getDetail() {
        return detail;
    }

    public void setDetail(WorkingstaffDetailDto detail) {
        this.detail = detail;
    }

    public List<WorkingstaffListDetailDto> getList() {
        return list;
    }

    public void setList(List<WorkingstaffListDetailDto> list) {
        this.list = list;
    }

    public List<String> getEventList() {
        return eventList;
    }

    public void setEventList(List<String> eventList) {
        this.eventList = eventList;
    }
    
    public List<String> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<String> staffList) {
        this.staffList = staffList;
    }
}
