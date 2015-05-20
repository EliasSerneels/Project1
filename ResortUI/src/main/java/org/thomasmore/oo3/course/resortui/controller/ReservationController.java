package org.thomasmore.oo3.course.resortui.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.facade.ReservationFacade;
import org.thomasmore.oo3.course.resortui.model.ReservationPageDto;

@Named(value = "reservation")
@RequestScoped
public class ReservationController {

    private ReservationPageDto dto;
    private final String pageRedirect = "reservation.xhtml?faces-redirect=true";
    private List<ReservationEntity> selectedReservation;
    
    @EJB
    private ReservationFacade reservationFacade;

    
    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");

        dto = reservationFacade.loadReservationOverviewPage(editId, deleteId);
    }

    public String add() {
        reservationFacade.add(dto);
        return pageRedirect;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public ReservationPageDto getDto() {
        return dto;
    }

    public void setDto(ReservationPageDto dto) {
        this.dto = dto;
    }

    public List<ReservationEntity> getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(List<ReservationEntity> selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

}
