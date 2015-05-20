/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private final SimpleDateFormat dateDate = new SimpleDateFormat("dd/MM/yyyy");

    public StaffPageDto loadStaffOverviewPage(String editId, String deleteId) {
        StaffPageDto dto = new StaffPageDto();
        if (editId != null) {
            StaffEntity staffEntity = staffDao.findById(editId);
            if (staffEntity != null) {
                dto.getDetail().setId(staffEntity.getId());
                dto.getDetail().setFirstname(staffEntity.getFirstname());
                dto.getDetail().setLastname(staffEntity.getLastname());
                dto.getDetail().setBirthdate(staffEntity.getBirthdate());
                dto.getDetail().setBirthdateFormatted(staffEntity.getBirthdateFormatted());
                dto.getDetail().setCountry(staffEntity.getCountry());
                dto.getDetail().setCity(staffEntity.getCity());
                dto.getDetail().setStreet(staffEntity.getStreet());
                dto.getDetail().setHousenumber(staffEntity.getHousenumber());
                dto.getDetail().setPhonenumber(staffEntity.getPhonenumber());
                dto.getDetail().setEmail(staffEntity.getEmail());
                dto.getDetail().setImageID(staffEntity.getImageID());
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

            // Datum formateren
            listDetail.setBirthdateFormatted(dateDate.format(staff.getBirthdate()));

            try {
                listDetail.setBirthdate(dateDate.parse(listDetail.getBirthdateFormatted())
             );
            } catch (ParseException ex) {
                Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Tot hier wordt datum geformateerd
            listDetail.setBirthdate(staff.getBirthdate());
            listDetail.setCountry(staff.getCountry());
            listDetail.setCity(staff.getCity());
            listDetail.setStreet(staff.getStreet());
            listDetail.setHousenumber(staff.getHousenumber());
            listDetail.setPhonenumber(staff.getPhonenumber());
            listDetail.setEmail(staff.getEmail());
            listDetail.setImageID(staff.getImageID());
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
        if (staffEntity == null) {
            staffEntity = new StaffEntity();
        }
        staffEntity.setId(dto.getDetail().getId());
        staffEntity.setLastname(dto.getDetail().getLastname());
        staffEntity.setFirstname(dto.getDetail().getFirstname());
        staffEntity.setBirthdate(dto.getDetail().getBirthdate());
        staffEntity.setBirthdateFormatted(dto.getDetail().getBirthdateFormatted());
        staffEntity.setCountry(dto.getDetail().getCountry());
        staffEntity.setCity(dto.getDetail().getCity());
        staffEntity.setStreet(dto.getDetail().getStreet());
        staffEntity.setHousenumber(dto.getDetail().getHousenumber());
        staffEntity.setPhonenumber(dto.getDetail().getPhonenumber());
        staffEntity.setEmail(dto.getDetail().getEmail());
        staffEntity.setImageID(dto.getDetail().getImageID());
        staffDao.save(staffEntity);
        return dto;
    }
}
