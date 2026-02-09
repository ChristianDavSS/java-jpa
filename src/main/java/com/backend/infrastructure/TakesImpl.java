package com.backend.infrastructure;

import com.backend.domain.entity.Takes;
import com.backend.domain.entity.TakesPK;
import com.backend.domain.ports.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TakesImpl implements Repository<Takes, TakesPK> {
    private final EntityManager em;
    private final CriteriaBuilder cb;
    private final EntityTransaction transaction;

    public TakesImpl() {
        this.em = BeanManager.getEntityManager();
        this.cb = this.em.getCriteriaBuilder();
        this.transaction = this.em.getTransaction();
    }

    @Override
    public Takes getById(TakesPK id) {
        return this.em.find(Takes.class, id);
    }

    @Override
    public List<Takes> getAll() {
        CriteriaQuery<Takes> query = this.cb.createQuery(Takes.class);
        Root<Takes> root = query.from(Takes.class);

        return this.em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public Takes save(Takes instance) {
        this.transaction.begin();
        Takes t = this.em.merge(instance);
        this.transaction.commit();

        return t;
    }

    @Override
    public void update(Takes instance) {
        this.transaction.begin();
        this.em.merge(instance);
        this.transaction.commit();
    }

    @Override
    public void deleteById(TakesPK id) {
        CriteriaDelete<Takes> delete = this.cb.createCriteriaDelete(Takes.class);
        Root<Takes> root = delete.getRoot();

        delete = delete.where(this.cb.equal(root.get("id"), id));

        this.transaction.begin();
        this.em.createQuery(delete).executeUpdate();
        this.transaction.commit();
    }

    @Override
    public Boolean existsById(TakesPK id) {
        CriteriaQuery<Takes> query = this.cb.createQuery(Takes.class);
        Root<Takes> root = query.from(Takes.class);

        return this.em.createQuery(query.where(this.cb.equal(root.get("id"), id))).getSingleResult() != null;
    }
}
