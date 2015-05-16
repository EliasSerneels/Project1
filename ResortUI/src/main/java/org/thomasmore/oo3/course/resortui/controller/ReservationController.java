package org.thomasmore.oo3.course.resortui.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
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
        @EJB
   private ParkDao parkDao;

    private List<BungalowEntity> bungalows;
    private List<ReservationEntity> selectedReservation;
    private Date EndDate;
    
    private Date StartDate;

    
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
        
          List<ParkEntity> parks = parkDao.listAll();

        
           for (ParkEntity park : parks) {
               
            dto.getParkList().add(park.getName());
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
            listDetail.setParkName(reservation.getParkName());

        
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
        reservationEntity.setParkName(dto.getDetail().getParkName());

        reservationsDao.save(reservationEntity);
        BungalowEntity be = null;
        for (BungalowEntity bungalow : bungalows) {
            if (bungalow.getName().equals(dto.getDetail().getBungalowName())) {
                be = bungalow;
                be.addReservation();
                bungalowsDao.save(be);
            }
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

 

    public String remove(String id) {
        reservationsDao.deleteById(id);

        return "reservation.xhtml?faces-redirect=true";
    }

    public ReservationPageDto getDto() {
        return dto;
    }

    public void setDto(ReservationPageDto dto) {
        this.dto = dto;
    }
    public Date getEndDate() {
        return EndDate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public List<ReservationEntity> getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(List<ReservationEntity> selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

}
