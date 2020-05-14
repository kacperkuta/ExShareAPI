package pl.edu.mimuw.exshare.CourseTestsController;

import java.io.Serializable;
import java.util.Objects;

public class CourseTestAssignmentId implements Serializable {
    private String testName;
    private int courseId;

    public CourseTestAssignmentId(String testName, int courseId) {
        this.testName = testName;
        this.courseId = courseId;
    }

    public CourseTestAssignmentId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CourseTestAssignmentId id = (CourseTestAssignmentId) o;
        return id.courseId == courseId && id.testName.equals(testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, courseId);
    }
}
