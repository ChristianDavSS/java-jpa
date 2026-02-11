package com.backend.infrastructure.implementations;

import com.backend.domain.entity.Enrollment;
import com.backend.domain.entity.EnrollmentPK;
import com.backend.domain.ports.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class EnrollmentImpl implements Repository<Enrollment, EnrollmentPK> {
    private final EntityManager em;
    private final CriteriaBuilder cb;
    private final EntityTransaction transaction;

    public EnrollmentImpl() {
        this.em = BeanManager.getEntityManager();
        this.cb = this.em.getCriteriaBuilder();
        this.transaction = this.em.getTransaction();
    }

    @Override
    public Enrollment getById(EnrollmentPK id) {
        return this.em.find(Enrollment.class, id);
    }

    @Override
    public List<Enrollment> getAll() {
        CriteriaQuery<Enrollment> query = this.cb.createQuery(Enrollment.class);
        Root<Enrollment> root = query.from(Enrollment.class);

        return this.em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public Enrollment save(Enrollment instance) {
        if (this.existsById(instance.getId())) {
            return null;
        }
        this.transaction.begin();
        System.out.println(instance.toString());
        Enrollment t = this.em.merge(instance);
        this.transaction.commit();

        return t;
    }

    @Override
    public void update(Enrollment instance) {
        this.transaction.begin();
        this.em.merge(instance);
        this.transaction.commit();
    }

    @Override
    public void deleteById(EnrollmentPK id) {
        CriteriaDelete<Enrollment> delete = this.cb.createCriteriaDelete(Enrollment.class);
        Root<Enrollment> root = delete.getRoot();

        delete = delete.where(this.cb.equal(root.get("id"), id));

        this.transaction.begin();
        this.em.createQuery(delete).executeUpdate();
        this.transaction.commit();
    }

    @Override
    public Boolean existsById(EnrollmentPK id) {
        CriteriaQuery<Enrollment> query = this.cb.createQuery(Enrollment.class);
        Root<Enrollment> root = query.from(Enrollment.class);

        return !this.em.createQuery(query.where(this.cb.equal(root.get("id"), id))).getResultList().isEmpty();
    }
}
