package com.backend.domain.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TakesPK implements Serializable {
    private Long student_id;
    private Long subject_id;

    public TakesPK() {}

    public TakesPK(Builder builder) {
        this.student_id = builder.student_id;
        this.subject_id = builder.subject_id;
    }

    public Long getStudentId() {
        return this.student_id;
    }

    public Long getSubjectId() {
        return this.subject_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakesPK takesPK = (TakesPK) o;
        return Objects.equals(student_id, takesPK.student_id) && Objects.equals(subject_id, takesPK.subject_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, subject_id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long student_id;
        private Long subject_id;

        public Builder studentId(Long studentId) {
            this.student_id = studentId;
            return this;
        }

        public Builder subjectId(Long subjectId) {
            this.subject_id = subjectId;
            return this;
        }

        public TakesPK build() {
            return new TakesPK(this);
        }
    }
}
