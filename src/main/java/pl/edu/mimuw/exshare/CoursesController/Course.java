package pl.edu.mimuw.exshare.CoursesController;

import javax.persistence.*;

@Entity
@Table(name = "exshare_courses")
public class Course {
    @Id
    @Column(name = "course_id")
    int courseId;

    @Column(name = "course_name")
    String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }
}
