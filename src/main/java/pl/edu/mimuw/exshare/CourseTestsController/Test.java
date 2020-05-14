package pl.edu.mimuw.exshare.CourseTestsController;

import javax.persistence.*;

@Entity
@Table(name = "course_test")
@IdClass(CourseTestAssignmentId.class)
public class Test {
    @Column(name = "test_name")
    @Id
    String testName;

    @Column(name = "course_id")
    @Id
    int courseId;

    public Test(int courseId, String testName) {
        this.courseId = courseId;
        this.testName = testName;
    }

    public Test() {
    }
}
