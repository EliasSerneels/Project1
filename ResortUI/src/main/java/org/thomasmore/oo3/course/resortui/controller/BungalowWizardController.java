package org.thomasmore.oo3.course.resortui.controller;
 
import java.io.Serializable;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.model.BungalowPageDto;
import org.thomasmore.oo3.course.resortui.model.ParkPageDto;

/**
 *
 * @author Timothy De Groot
 */


@ManagedBean(name = "BungalowWizardController")
@ViewScoped
public class BungalowWizardController implements Serializable {

    @EJB
    private BungalowDao bungalowDao;
    @EJB
    private ParkDao parkDao;
    
    private ParkPageDto parkdto;

    public ParkPageDto getParkdto() {
        return parkdto;
    }

    public void setParkdto(ParkPageDto parkdto) {
        this.parkdto = parkdto;
    }
    public void init() {
        parkdto = new ParkPageDto();
    }
    private boolean skip =false;
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in geval dat gebruiker op back duwt
            return "confirm";
        }
        else {
           
            return event.getNewStep();
        }
    }
    
    // Vanaf hier start de code om zaken toe te voegen met de wizard (inzake Dao's, facades en controllers)

    public BungalowPageDto bungalowAdd(BungalowPageDto dto) {
        
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
    
    public void save(BungalowPageDto bdto){
        System.out.println("*************** Save werd aangeroepen");
        this.bungalowAdd(bdto);
    }

}