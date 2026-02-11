package com.backend.domain.entity;

import jakarta.persistence.*;

/**
 * Takes entity: intermediate table that connects Student and Subject as a many-to-many relationship
 * Uses a composited primary key
 * */
@Entity
public class Enrollment {
    // Embed the primary key
    @EmbeddedId
    private EnrollmentPK id;

    // set up the primary keys
    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("subject_id")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Enrollment() {}

    public Enrollment(Builder builder) {
        this.id = builder.enrollmentPK;
        this.student = builder.student;
        this.subject = builder.subject;
    }

    public EnrollmentPK getId() {
        return this.id;
    }

    public Student getStudent() {
        return this.student;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Takes{" +
                "id=" + id +
                ", student=" + student +
                ", subject=" + subject +
                '}';
    }

    public static class Builder {
        private EnrollmentPK enrollmentPK;
        private Student student;
        private Subject subject;

        public Builder takesPK(EnrollmentPK enrollmentPK) {
            this.enrollmentPK = enrollmentPK;
            return this;
        }

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Enrollment build() {
            return new Enrollment(this);
        }
    }
}
