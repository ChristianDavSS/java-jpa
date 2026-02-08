package com.backend.domain.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class TakesPK {
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
