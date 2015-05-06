
package org.thomasmore.oo3.course.resortui.facade;

import java.util.List;
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
        List<UserEntity> users = dao.listAll();
        for(UserEntity user: users) {
            UserDetailDto ud = new UserDetailDto();
            ud.setEmail(user.getEmail());
            ud.setFullName(user.getFullName());
            ud.setId(user.getId());
            ud.setPhone(user.getPhone());
            ud.setLevel(user.getLevel());
            ud.setUsername(user.getUsername());
            dto.getList().add(ud);
        }
        return dto;
    }
}
