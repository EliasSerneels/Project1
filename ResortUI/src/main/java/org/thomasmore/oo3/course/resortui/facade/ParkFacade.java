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
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.model.ParkListDetailDto;
import org.thomasmore.oo3.course.resortui.model.ParkPageDto;

/**
 *
 * @author Elias Serneels
 */
@Stateless
public class ParkFacade {
    
    @EJB
    private ParkDao parkDao;
   

    public ParkPageDto loadParkOverviewPage(String editId, String deleteId) {
        ParkPageDto dto = new ParkPageDto();
        
        if (deleteId != null) {
            parkDao.deleteById(deleteId);
        }
        
        List<ParkEntity> parks = parkDao.listAll();

        if (editId != null) {
            ParkEntity parkEntity = parkDao.findById(editId);
            if (parkEntity != null) {
                dto.getDetail().setId(parkEntity.getId());
                dto.getDetail().setName(parkEntity.getName());
                dto.getDetail().setLocation(parkEntity.getLocation());
                dto.getDetail().setCapacity(parkEntity.getCapacity());
                dto.getDetail().setImageID(parkEntity.getImageID());


            }
        }
        
        for (ParkEntity park : parks) {
            ParkListDetailDto listDetail = new ParkListDetailDto();
            listDetail.setId(park.getId());
            listDetail.setName(park.getName());
            listDetail.setLocation(park.getLocation());
            listDetail.setCapacity(park.getCapacity());
            listDetail.setBungalowName(park.getBungalowName());
            listDetail.setImageID(park.getImageID());
            dto.getList().add(listDetail);
        }
        
                
                return dto;
            
    }
    public ParkPageDto add(ParkPageDto dto){
        ParkEntity parkEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            parkEntity = parkDao.findById(dto.getDetail().getId());
        }

        if (parkEntity == null) {
            parkEntity = new ParkEntity();

        }       
        parkEntity.setId(dto.getDetail().getId());
        System.out.println(dto.getDetail().getId());
        parkEntity.setName(dto.getDetail().getName());
        parkEntity.setLocation(dto.getDetail().getLocation());
        parkEntity.setCapacity(dto.getDetail().getCapacity());
        parkEntity.setBungalowName(dto.getDetail().getBungalowName());
        parkDao.save(parkEntity);
        return dto;
    }
}
