package pl.edu.mimuw.exshare.TestTasksController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestTasksRepository extends JpaRepository<Task, TestTaskAssignmentId> {
    List<Task> findAllByCourseIdAndTestName(int courseId, String testName);
}
