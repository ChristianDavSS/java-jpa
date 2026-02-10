package com.backend.infrastructure.implementations;

import com.backend.domain.entity.Subject;
import com.backend.domain.ports.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class SubjectImpl implements Repository<Subject, Long> {
    private final EntityManager em;
    private final CriteriaBuilder cb;
    private final EntityTransaction transaction;

    public SubjectImpl() {
        this.em = BeanManager.getEntityManager();
        this.cb = this.em.getCriteriaBuilder();
        this.transaction = this.em.getTransaction();
    }

    @Override
    public Subject getById(Long id) {
        return em.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        CriteriaQuery<Subject> query = this.cb.createQuery(Subject.class);
        Root<Subject> root = query.from(Subject.class);

        return this.em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public Subject save(Subject instance) {
        if (instance.getId() != null) {
            System.out.println("Error... (testing)");
            return null;
        }

        this.transaction.begin();
        Subject s = this.em.merge(instance);
        this.transaction.commit();

        return s;
    }

    @Override
    public void update(Subject instance) {
        if (instance.getId() == null) {
            System.out.println("Error... (testing)");
            return;
        }

        this.transaction.begin();
        this.em.merge(instance);
        this.transaction.commit();
    }

    @Override
    public void deleteById(Long id) {
        CriteriaDelete<Subject> delete = this.cb.createCriteriaDelete(Subject.class);
        Root<Subject> root = delete.getRoot();

        delete = delete.where(this.cb.equal(root.get("id"), id));

        this.transaction.begin();
        this.em.createQuery(delete).executeUpdate();
        this.transaction.commit();
    }

    @Override
    public Boolean existsById(Long id) {
        CriteriaQuery<Subject> cq = this.cb.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);

        return !this.em.createQuery(cq.select(root).where(this.cb.equal(root.get("id"), id))).getResultList().isEmpty();
    }
}
