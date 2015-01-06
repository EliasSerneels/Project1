/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.model;

/**
 *
 * @author User
 */
public class RegistrationPageDto {
    private UserDetailDto detail = new UserDetailDto();

    public UserDetailDto getDetail() {
        return detail;
    }

    public void setDetail(UserDetailDto detail) {
        this.detail = detail;
    }
    
}
