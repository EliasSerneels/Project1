/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypedetailEntity;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypedetailDao;
import org.thomasmore.oo3.course.resortui.model.EventtypedetailPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventtypedetailFacade {

  private EventtypedetailPageDto dto;

    @EJB
    private EventtypedetailDao eventtypedetailDao;
    @EJB
    private EventtypeDao eventtypeDao;
    @EJB
    private EventcompanyDao eventcompanyDao;

    public EventtypedetailPageDto loadEventtypedetailOverviewPage(String editId, String deleteId) {

        if (editId != null) {
            EventtypedetailEntity eventtypedetailEntity = eventtypedetailDao.findById(editId);
            if (eventtypedetailEntity != null) {
                dto.getDetail().setId(eventtypedetailEntity.getId());
                dto.getDetail().setName(bungalowEntity.getName());
                dto.getDetail().setPrice(bungalowEntity.getPrice());
                
            }
        }

        if (deleteId != null) {
            bungalowDao.deleteById(deleteId);
        }
        List<BungalowEntity> bungalows = bungalowDao.listAll();
        List<ParkEntity> parks = parkDao.listAll();
        dto = new BungalowPageDto();
        dto.getParkList().add("");
        for (ParkEntity park : parks) {
            dto.getParkList().add(park.getName());
        }
        for (BungalowEntity bungalow : bungalows) {
            BungalowListDetailDto listDetail = new BungalowListDetailDto();
            listDetail.setId(bungalow.getId());
            listDetail.setName(bungalow.getName());
            listDetail.setType(bungalow.getType());
            listDetail.setPrice(bungalow.getPrice());
            listDetail.setPark(bungalow.getPark());
            listDetail.setMaxpeople(bungalow.getMaxpeople());
            listDetail.setReservations(bungalow.getReservations());
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventtypedetailPageDto add() {
        
EventtypedetailEntity eventtypedetailEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventtypedetailEntity = eventtypedetailDao.findById(dto.getDetail().getId());
        }

        if (eventtypedetailEntity == null) {
            eventtypedetailEntity = new EventtypedetailEntity();            
        }       
        bungalowEntity.setId(dto.getDetail().getId());
        bungalowEntity.setName(dto.getDetail().getName());
        bungalowEntity.setPrice(dto.getDetail().getPrice());
        bungalowEntity.setType(dto.getDetail().getType());
        bungalowEntity.setPark(dto.getDetail().getPark());
        bungalowEntity.setMaxpeople(dto.getDetail().getMaxpeople());
        bungalowDao.save(bungalowEntity);
        return dto;
}
