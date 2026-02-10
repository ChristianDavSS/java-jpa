package com.backend.infrastructure.implementations;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.*;

import java.util.List;


public class StudentImpl implements Repository<Student, Long> {
    // Get the EntityManager in order to manage the table
    private final EntityManager em;
    // Get the criteria builder used for this class
    private final CriteriaBuilder cb;
    private final EntityTransaction entityTransaction;

    public StudentImpl() {
        this.em = BeanManager.getEntityManager();
        this.cb = this.em.getCriteriaBuilder();
        this.entityTransaction = this.em.getTransaction();
    }

    @Override
    public Student getById(Long id) {
        // Fetch the student by their primary key and return the result
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        CriteriaQuery<Student> query = this.cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        return this.em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public Student save(Student instance) {
        if (instance.getId() != null) {
            System.out.println("Error... (testing)");
            return null;
        }

        this.entityTransaction.begin();
        Student s = em.merge(instance);
        this.entityTransaction.commit();
        return s;
    }

    @Override
    public void update(Student instance) {
        if (instance.getId() == null) {
            System.out.println("Error...");
            return;
        }

        this.entityTransaction.begin();
        em.merge(instance);
        this.entityTransaction.commit();
    }

    @Override
    public void deleteById(Long id) {
        // get the criteria delete from the criteria builder of our classes
        CriteriaDelete<Student> delete = this.cb.createCriteriaDelete(Student.class);
        // get the root node (allows me to get column values from the table)
        Root<Student> root = delete.getRoot();

        // set the predicate or WHERE clause
        delete = delete.where(this.cb.equal(root.get("id"), id));

        this.entityTransaction.begin();
        // execute the query and delete the registry
        em.createQuery(delete).executeUpdate();
        this.entityTransaction.commit();
    }

    @Override
    public Boolean existsById(Long id) {
        // Create the query from the criteria builder
        CriteriaQuery<Student> query = this.cb.createQuery(Student.class);
        // get the root node (for the WHERE clause conditions)
        Root<Student> root = query.from(Student.class);
        // set up a where condition
        query = query.select(root).where(this.cb.equal(root.get("id"), id));

        return !this.em.createQuery(query).getResultList().isEmpty();
    }
}
