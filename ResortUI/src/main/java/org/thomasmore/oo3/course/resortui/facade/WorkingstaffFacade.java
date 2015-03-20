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
import org.thomasmore.oo3.course.resortui.business.entity.WorkingstaffEntity;
import org.thomasmore.oo3.course.resortui.dao.WorkingstaffDao;
import org.thomasmore.oo3.course.resortui.model.WorkingstaffListDetailDto;
import org.thomasmore.oo3.course.resortui.model.WorkingstaffPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class WorkingstaffFacade {
 
    @EJB
    private WorkingstaffDao workingstaffDao;
        
    public WorkingstaffPageDto loadWorkingstaffOverviewPage(String editId, String deleteId) {
WorkingstaffPageDto dto = new WorkingstaffPageDto();
        if (editId != null) {
            WorkingstaffEntity workingstaffEntity = workingstaffDao.findById(editId);
            if (workingstaffEntity != null) {
                dto.getDetail().setId(workingstaffEntity.getId());
                dto.getDetail().setFunction(workingstaffEntity.getFunction());
                dto.getDetail().setEvent(workingstaffEntity.getEvent());
                dto.getDetail().setStaff(workingstaffEntity.getStaff());
                dto.getDetail().setStarttime(workingstaffEntity.getStarttime());
                dto.getDetail().setEndtime(workingstaffEntity.getEndtime());
                
            }
        }

        if (deleteId != null) {
            workingstaffDao.deleteById(deleteId);
        }
        List<WorkingstaffEntity> events = workingstaffDao.listAll();
        
        
        
        
        for (WorkingstaffEntity workingstaff : events) {
            WorkingstaffListDetailDto listDetail = new WorkingstaffListDetailDto();
            listDetail.setId(workingstaff.getId());
            listDetail.setFunction(workingstaff.getFunction());
            listDetail.setEvent(workingstaff.getEvent());
            listDetail.setStaff(workingstaff.getStaff());
            listDetail.setStarttime(workingstaff.getStarttime());
            listDetail.setEndtime(workingstaff.getEndtime());
            
            
            
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public WorkingstaffPageDto add(WorkingstaffPageDto dto) {
        
WorkingstaffEntity workingstaffEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            workingstaffEntity = workingstaffDao.findById(dto.getDetail().getId());
        }

        if (workingstaffEntity == null) {
            workingstaffEntity = new WorkingstaffEntity();            
        }       
        workingstaffEntity.setId(dto.getDetail().getId());
        workingstaffEntity.setFunction(dto.getDetail().getFunction());
        workingstaffEntity.setEvent(dto.getDetail().getEvent());
        workingstaffEntity.setStaff(dto.getDetail().getStaff());
        workingstaffEntity.setStarttime(dto.getDetail().getStarttime());
        workingstaffEntity.setEndtime(dto.getDetail().getEndtime());
        workingstaffDao.save(workingstaffEntity);
        return dto;
}}
