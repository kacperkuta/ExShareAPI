package pl.edu.mimuw.exshare.CoursesController;

import javax.persistence.*;

@Entity
@Table(name = "exshare_courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_name")
    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }
}
