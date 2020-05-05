package pl.edu.mimuw.exshare;

import javax.persistence.*;

@Entity
@Table(name = "exshare_courses")
public class Course {
    @Id
    @Column(name = "course_id")
    int courseId;

    public Course(int courseId) {
        this.courseId = courseId;
    }

    public Course() {
    }
}
