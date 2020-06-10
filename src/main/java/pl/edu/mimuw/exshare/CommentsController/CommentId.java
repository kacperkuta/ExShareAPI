package pl.edu.mimuw.exshare.CommentsController;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class CommentId implements Serializable {
    int courseId;

    String testName;

    int exerciseNumber;

    int solutionNumber;

    Timestamp time;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass())
            return false;
        CommentId id = (CommentId)o;
        return id.courseId == courseId && id.testName.equals(testName) &&
                id.exerciseNumber == exerciseNumber && id.solutionNumber == solutionNumber
                && id.time == time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, testName, exerciseNumber, solutionNumber, time);
    }
}
