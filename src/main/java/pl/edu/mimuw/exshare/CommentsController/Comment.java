package pl.edu.mimuw.exshare.CommentsController;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="comment")
@IdClass(CommentId.class)
public class Comment {
    @Id
    @Column(name="course_id")
    int courseId;

    @Id
    @Column(name="test_name")
    String testName;

    @Id
    @Column(name="exercise_number")
    int exerciseNumber;

    @Id
    @Column(name="solution_number")
    int solutionNumber;

    @Column(name="content")
    String content;

    @Id
    @Column(name = "time", columnDefinition = "timestamp")
    Timestamp time;


    public Comment() {
    }

    public Comment(int courseId, String testName, int exerciseNumber, int solutionNumber, String content) {
        this.courseId = courseId;
        this.exerciseNumber = exerciseNumber;
        this.solutionNumber = solutionNumber;
        this.testName = testName;
        this.content = content;
        this.time = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }
}
