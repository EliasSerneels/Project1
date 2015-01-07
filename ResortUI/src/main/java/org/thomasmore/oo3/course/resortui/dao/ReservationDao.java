package org.thomasmore.oo3.course.resortui.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;


@Stateless
public class ReservationDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void save(ReservationEntity reservationEntity) {
        em.persist(reservationEntity);
    }

    public List<ReservationEntity> listAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservationEntity.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    public ReservationEntity findById(String id) {
        return em.find(ReservationEntity.class, id);
    }

    public void deleteById(String id) {
        em.remove(findById(id));
    }
}

