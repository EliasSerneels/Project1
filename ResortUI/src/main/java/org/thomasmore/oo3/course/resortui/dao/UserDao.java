/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.dao;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;

@Stateless
public class UserDao implements Serializable {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(UserEntity ue) {
        em.merge(ue);
    }
    
    public UserEntity findById(String id) {
        return em.find(UserEntity.class, id);
    }
 
}
