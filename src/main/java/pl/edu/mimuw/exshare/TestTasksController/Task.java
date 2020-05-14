package pl.edu.mimuw.exshare.TestTasksController;

import javax.persistence.*;

@Entity
@Table(name = "test_task")
@IdClass(TestTaskAssignmentId.class)
public class Task {
    @Id
    @Column(name = "course_id")
    int courseId;

    @Id
    @Column(name = "test_name")
    String testName;

    @Id
    @Column(name = "task_number")
    int taskNum;

    public Task() {
    }

    public Task(int courseId, String testName, int taskNum) {
        this.courseId = courseId;
        this.testName = testName;
        this.taskNum = taskNum;
    }
}
