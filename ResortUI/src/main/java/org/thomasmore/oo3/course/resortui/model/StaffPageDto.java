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
public class StaffPageDto {

    private StaffDetailDto detail = new StaffDetailDto();

    private List<StaffListDetailDto> list = new LinkedList<>();

    public StaffDetailDto getDetail() {
        return detail;
    }

    public void setDetail(StaffDetailDto detail) {
        this.detail = detail;
    }

    public List<StaffListDetailDto> getList() {
        return list;
    }

    public void setList(List<StaffListDetailDto> list) {
        this.list = list;
    }
}
