package pl.edu.mimuw.exshare.CourseTestsController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CourseTestsRepository extends JpaRepository<Test, CourseTestAssignmentId> {
    Set<Test> findByCourseId(int courseId);
}