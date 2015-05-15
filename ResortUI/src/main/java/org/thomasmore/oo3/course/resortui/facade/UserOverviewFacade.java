
package org.thomasmore.oo3.course.resortui.facade;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;
import org.thomasmore.oo3.course.resortui.dao.UserDao;
import org.thomasmore.oo3.course.resortui.model.UserDetailDto;
import org.thomasmore.oo3.course.resortui.model.UserOverviewPageDto;

@Stateless
public class UserOverviewFacade {
    @EJB
    private UserDao dao;
    
    public UserOverviewPageDto loadUserOverviewPage(String editId, String deleteId) {
        UserOverviewPageDto dto = new UserOverviewPageDto();
        if(deleteId != null) {
            dao.deletebyId(deleteId);
        }
        if (editId != null) {
            UserEntity ue = dao.findById(editId);
            if (ue != null) {
                dto.getDetail().setId(ue.getId());
                dto.getDetail().setUsername(ue.getUsername());
                dto.getDetail().setPassword(ue.getPassword());
                dto.getDetail().setEmail(ue.getEmail());
                dto.getDetail().setLevel(ue.getLevel());
                dto.getDetail().setImageID(ue.getImageID());

            }
        }
        List<UserEntity> users = dao.listAll();
        for(UserEntity user: users) {
            UserDetailDto ud = new UserDetailDto();
            ud.setEmail(user.getEmail());
            ud.setFullName(user.getFullName());
            ud.setId(user.getId());
            ud.setLevel(user.getLevel());
            ud.setUsername(user.getUsername());
            ud.setImageID(user.getImageID());
            dto.getList().add(ud);
        }
        return dto;
    }
    
    public UserOverviewPageDto add(UserOverviewPageDto dto) {
        UserEntity ue = null;
         if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            ue = dao.findById(dto.getDetail().getId());
        }
         
        if(ue == null) {
            ue = new UserEntity();
        }
        ue.setId(dto.getDetail().getId());
        ue.setUsername(dto.getDetail().getUsername());
        ue.setPassword(dto.getDetail().getPassword());
        ue.setFullName(dto.getDetail().getFullName());
        ue.setEmail(dto.getDetail().getEmail());
        ue.setLevel(dto.getDetail().getLevel());
        dao.save(ue);
        return dto;
    }
}
