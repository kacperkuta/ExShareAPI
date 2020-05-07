package pl.edu.mimuw.exshare.AssignementsController;

import java.io.Serializable;
import java.util.Objects;

public class UserCourseAssignmentId implements Serializable {
    private String userId;
    private int courseId;

    public UserCourseAssignmentId(String userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UserCourseAssignmentId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserCourseAssignmentId id = (UserCourseAssignmentId)o;
        return id.courseId == courseId && id.userId.equals(userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
