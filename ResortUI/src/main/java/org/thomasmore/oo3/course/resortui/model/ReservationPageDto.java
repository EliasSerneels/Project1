/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

import java.util.LinkedList;
import java.util.List;

public class ReservationPageDto {

    private ReservationDetailDto detail = new ReservationDetailDto();
    private List<ReservationListDetailDto> list = new LinkedList<>();
    private List<String> customerList = new LinkedList<>();
    

    public ReservationDetailDto getDetail() {
        return detail;
    }

    public void setDetail(ReservationDetailDto detail) {
        this.detail = detail;
    }

    public List<ReservationListDetailDto> getList() {
        return list;
    }

    public void setList(List<ReservationListDetailDto> list) {
        this.list = list;
    }

    public List<String> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<String> customerList) {
        this.customerList = customerList;
    }

    
}
