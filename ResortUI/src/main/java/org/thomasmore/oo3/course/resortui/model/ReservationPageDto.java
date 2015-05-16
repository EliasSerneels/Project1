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
    private List<String> bungalowList = new LinkedList<>();
   private List<String> parkList = new LinkedList<>();

    public List<String> getParkList() {
        return parkList;
    }

    public void setParkList(List<String> parkList) {
        this.parkList = parkList;
    }

   
   

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

    public List<String> getBungalowList() {
        return bungalowList;
    }

    public void setBungalowList(List<String> bungalowList) {
        this.bungalowList = bungalowList;
    }

}
