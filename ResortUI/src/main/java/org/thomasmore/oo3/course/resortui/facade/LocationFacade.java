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
import org.thomasmore.oo3.course.resortui.business.entity.LocationEntity;
import org.thomasmore.oo3.course.resortui.dao.LocationDao;
import org.thomasmore.oo3.course.resortui.model.LocationListDetailDto;
import org.thomasmore.oo3.course.resortui.model.LocationPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class LocationFacade {

    
    @EJB
    private LocationDao locationDao;

    public LocationPageDto loadLocationOverviewPage(String editId, String deleteId) {
        LocationPageDto dto = new LocationPageDto();
        if (editId != null) {
            LocationEntity locationEntity = locationDao.findById(editId);
            if (locationEntity != null) {
                dto.getDetail().setId(locationEntity.getId());
                dto.getDetail().setCity(locationEntity.getCity());
                dto.getDetail().setStreet(locationEntity.getStreet());
                
            }
        }
        if (deleteId != null) {
            locationDao.deleteById(deleteId);
        }
        List<LocationEntity> locations = locationDao.listAll();
        
        for (LocationEntity location : locations) {
            LocationListDetailDto listDetail = new LocationListDetailDto();
            listDetail.setId(location.getId());
            listDetail.setCity(location.getCity());
            listDetail.setStreet(location.getStreet());
            dto.getList().add(listDetail);

        }
        return dto;

    }

    public LocationPageDto add(LocationPageDto dto) {
        LocationEntity locationEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            locationEntity = locationDao.findById(dto.getDetail().getId());

        }
        if(locationEntity == null){
            locationEntity = new LocationEntity();
        }
        locationEntity.setId(dto.getDetail().getId());
        locationEntity.setCity(dto.getDetail().getCity());
        locationEntity.setStreet(dto.getDetail().getStreet());
        
        locationDao.save(locationEntity);
        return dto;
    }
}
