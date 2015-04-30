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
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.model.BungalowListDetailDto;
import org.thomasmore.oo3.course.resortui.model.BungalowPageDto;

/**
 *
 * @author Elias Serneels
 */
@Stateless
public class BungalowFacade {

    @EJB
    private BungalowDao bungalowDao;
    @EJB
    private ParkDao parkDao;

    public BungalowPageDto loadBungalowOverviewPage(String editId, String deleteId) {
        BungalowPageDto dto = new BungalowPageDto();

        if (deleteId != null) {
            bungalowDao.deleteById(deleteId);
        }
        List<ParkEntity> parks = parkDao.listAll();

        
        for (ParkEntity park : parks) {
            dto.getParkList().add(park.getName());
        }
        if (editId != null) {
            BungalowEntity bungalowEntity = bungalowDao.findById(editId);
            if (bungalowEntity != null) {
                dto.getDetail().setId(bungalowEntity.getId());
                dto.getDetail().setName(bungalowEntity.getName());
                dto.getDetail().setPrice(bungalowEntity.getPrice());
                dto.getDetail().setType(bungalowEntity.getType());
                dto.getDetail().setPark(bungalowEntity.getPark());
                dto.getDetail().setMaxpeople(bungalowEntity.getMaxpeople());
                dto.getDetail().setImageID(bungalowEntity.getImageID());
            }
        }
                List<BungalowEntity> bungalows = bungalowDao.listAll();

                for (BungalowEntity bungalow : bungalows) {
                    BungalowListDetailDto listDetail = new BungalowListDetailDto();
                    listDetail.setId(bungalow.getId());
                    listDetail.setName(bungalow.getName());
                    listDetail.setType(bungalow.getType());
                    listDetail.setPrice(bungalow.getPrice());
                    listDetail.setPark(bungalow.getPark());
                    listDetail.setMaxpeople(bungalow.getMaxpeople());
                    listDetail.setReservations(bungalow.getReservations());
                    listDetail.setImageID(bungalow.getImageID());
                    dto.getList().add(listDetail);
                }
                return dto;
            }

    public BungalowPageDto add(BungalowPageDto dto) {
        
        BungalowEntity bungalowEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            bungalowEntity = bungalowDao.findById(dto.getDetail().getId());
        }

        if (bungalowEntity == null) {
            bungalowEntity = new BungalowEntity();
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
}
