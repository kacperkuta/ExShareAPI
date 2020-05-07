package pl.edu.mimuw.exshare.AssignementsController;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@IdClass(UserCourseAssignmentId.class)
public class UserCourseAssignment {
    @Id
    @Column(name = "user_id")
    private final String userId;

    @Id
    @Column(name = "course_id")
    private final int courseId;

    public UserCourseAssignment(String userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UserCourseAssignment() {
        userId = "fake";
        courseId = 0;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getUserId() {
        return userId;
    }
}
