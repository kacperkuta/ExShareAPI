package pl.edu.mimuw.exshare;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@IdClass(UserCourseAssignementId.class)
public class UserCourseAssignement {
    @Id
    @Column(name = "user_id")
    private final String userId;

    @Id
    @Column(name = "course_id")
    private final int courseId;

    public UserCourseAssignement(String userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UserCourseAssignement() {
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
