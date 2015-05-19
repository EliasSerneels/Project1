/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventcompanyDao implements Serializable {
    
    @PersistenceContext
    private EntityManager em;

    public void save(EventcompanyEntity eventcompanyEntity) {
        em.merge(eventcompanyEntity);
    }

    public List<EventcompanyEntity> listAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(EventcompanyEntity.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    public EventcompanyEntity findById(String id) {
        return em.find(EventcompanyEntity.class, id);
    }

    public void deleteById(String id) {
        em.remove(findById(id));
    }
}
