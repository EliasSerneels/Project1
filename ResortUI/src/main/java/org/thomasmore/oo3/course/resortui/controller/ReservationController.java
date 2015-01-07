package org.thomasmore.oo3.course.resortui.controller;

import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.ReservationDao;
import org.thomasmore.oo3.course.resortui.model.ReservationDetailDto;
import org.thomasmore.oo3.course.resortui.model.ReservationListDetailDto;
import org.thomasmore.oo3.course.resortui.model.ReservationPageDto;


@Named(value = "reservation")
@RequestScoped
public class ReservationController {

    private ReservationPageDto dto;
    @EJB
    private ReservationDao reservationsDao;
    @EJB
    private BungalowDao bungalowsDao;
    @EJB
    private CustomerDao customersDao;
    
    private List<BungalowEntity> bungalows;

    @PostConstruct
    public void init() {
        bungalows = bungalowsDao.listAll();
        List<ReservationEntity> reservations = reservationsDao.listAll();
        List<CustomerEntity> customers = customersDao.listAll();

        dto = new ReservationPageDto();

        for (CustomerEntity customer : customers) {
            dto.getCustomerList().add(customer.getFirstname() + " " + customer.getLastname());
        }

        for (BungalowEntity bungalow : bungalows) {
            dto.getBungalowList().add(bungalow.getName());
        }

        for (ReservationEntity reservation : reservations) {
            ReservationListDetailDto listDetail = new ReservationDetailDto();
            listDetail.setId(reservation.getId());
            listDetail.setStartDate(reservation.getStartDate());
            listDetail.setStartTime(reservation.getStartTime());
            listDetail.setEndDate(reservation.getEndDate());
            listDetail.setEndTime(reservation.getEndTime());
            listDetail.setCustomerName(reservation.getCustomerName());
            listDetail.setBungalowName(reservation.getBungalowName());
            dto.getList().add(listDetail);
        }
    }

    public void add() {
        dto.getDetail().setId(UUID.randomUUID().toString());
        dto.getList().add(dto.getDetail());
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(dto.getDetail().getId());
        reservationEntity.setStartDate(dto.getDetail().getStartDate());
        reservationEntity.setStartTime(dto.getDetail().getStartTime());
        reservationEntity.setEndDate(dto.getDetail().getEndDate());
        reservationEntity.setEndTime(dto.getDetail().getEndTime());
        reservationEntity.setCustomerName(dto.getDetail().getCustomerName());
        reservationEntity.setBungalowName(dto.getDetail().getBungalowName());
        reservationsDao.save(reservationEntity);
        BungalowEntity be = null;
        for(BungalowEntity bungalow: bungalows) {
            if(bungalow.getName().equals(dto.getDetail().getBungalowName())) {
                be = bungalow;
                be.addReservation();
                bungalowsDao.save(be);
            }
        }
    }

    public void remove() {
        String id = dto.getDetail().getId();
        ReservationListDetailDto removeFromListobject = new ReservationDetailDto();
        for (ReservationListDetailDto reservationListDetailDto : dto.getList()) {
            if (reservationListDetailDto.getId().equals(id)) {
                removeFromListobject = reservationListDetailDto;
            }
        }
        dto.getList().remove(removeFromListobject);
        reservationsDao.deleteById(id);
    }

    public ReservationPageDto getDto() {
        return dto;
    }

    public void setDto(ReservationPageDto dto) {
        this.dto = dto;
    }

}


