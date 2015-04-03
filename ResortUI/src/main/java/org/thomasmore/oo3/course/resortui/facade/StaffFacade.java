/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.StaffEntity;
import org.thomasmore.oo3.course.resortui.dao.StaffDao;
import org.thomasmore.oo3.course.resortui.model.StaffListDetailDto;
import org.thomasmore.oo3.course.resortui.model.StaffPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class StaffFacade {

    
    @EJB
    private StaffDao staffDao;

    public StaffPageDto loadStaffOverviewPage(String editId, String deleteId) {
        StaffPageDto dto = new StaffPageDto();
        if (editId != null) {
            StaffEntity staffEntity = staffDao.findById(editId);
            if (staffEntity != null) {
                dto.getDetail().setId(staffEntity.getId());
                dto.getDetail().setFirstname(staffEntity.getFirstname());
                dto.getDetail().setLastname(staffEntity.getLastname());
                dto.getDetail().setBirthdate(staffEntity.getBirthdate());
                dto.getDetail().setCountry(staffEntity.getCountry());
                dto.getDetail().setCity(staffEntity.getCity());
                dto.getDetail().setStreet(staffEntity.getStreet());
                dto.getDetail().setPhone(staffEntity.getPhone());
                dto.getDetail().setStreet(staffEntity.getStreet());
            }
        }
        if (deleteId != null) {
            staffDao.deleteById(deleteId);
        }
        List<StaffEntity> staffs = staffDao.listAll();
        
        for (StaffEntity staff : staffs) {
            StaffListDetailDto listDetail = new StaffListDetailDto();
            listDetail.setId(staff.getId());
            listDetail.setFirstname(staff.getFirstname());
            listDetail.setLastname(staff.getLastname());
            listDetail.setBirthdate(staff.getBirthdate());
            listDetail.setCountry(staff.getCountry());
            listDetail.setCity(staff.getCity());
            listDetail.setStreet(staff.getStreet());
            listDetail.setPhone(staff.getPhone());
            dto.getList().add(listDetail);

        }
        return dto;

    }

    public StaffPageDto add(StaffPageDto dto) {
        StaffEntity staffEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            staffEntity = staffDao.findById(dto.getDetail().getId());

        }
        if(staffEntity == null){
            staffEntity = new StaffEntity();
        }
        staffEntity.setId(dto.getDetail().getId());
        staffEntity.setLastname(dto.getDetail().getLastname());
        staffEntity.setFirstname(dto.getDetail().getFirstname());
        staffEntity.setBirthdate(dto.getDetail().getBirthdate());
        staffEntity.setCountry(dto.getDetail().getCountry());
        staffEntity.setCity(dto.getDetail().getCity());
        staffEntity.setStreet(dto.getDetail().getStreet());
        staffEntity.setPhone(dto.getDetail().getPhone());
        staffDao.save(staffEntity);
        return dto;
}

     //To change body of generated methods, choose Tools | Templates.
    }

