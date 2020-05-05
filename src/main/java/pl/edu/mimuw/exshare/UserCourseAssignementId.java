package pl.edu.mimuw.exshare;

import java.io.Serializable;
import java.util.Objects;

public class UserCourseAssignementId implements Serializable {
    private String userId;
    private int courseId;

    public UserCourseAssignementId(String userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UserCourseAssignementId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserCourseAssignementId id = (UserCourseAssignementId)o;
        return id.courseId == courseId && id.userId.equals(userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
