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
 * @author User
 */
public class UserOverviewPageDto {
    
    private UserDetailDto detail = new UserDetailDto();
    
    private List<UserDetailDto> list = new LinkedList<>();

    public List<UserDetailDto> getList() {
        return list;
    }

    public void setList(List<UserDetailDto> users) {
        this.list = users;
    }

    public UserDetailDto getDetail() {
        return detail;
    }

    public void setDetail(UserDetailDto detail) {
        this.detail = detail;
    }    
}
