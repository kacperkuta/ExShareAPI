package pl.edu.mimuw.exshare.TestTasksController;

import java.io.Serializable;
import java.util.Objects;

public class TestTaskAssignmentId implements Serializable {
    private String testName;
    private int courseId;
    private int taskNum;

    public TestTaskAssignmentId(String testName, int courseId, int taskNum) {
        this.testName = testName;
        this.courseId = courseId;
        this.taskNum = taskNum;
    }

    public TestTaskAssignmentId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TestTaskAssignmentId id = (TestTaskAssignmentId) o;
        return id.courseId == courseId && id.testName.equals(testName) && taskNum == id.taskNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, courseId, taskNum);
    }
}
