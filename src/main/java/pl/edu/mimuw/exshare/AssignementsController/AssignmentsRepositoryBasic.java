package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentsRepositoryBasic extends JpaRepository<UserCourseAssignment, UserCourseAssignmentId> {
    List<UserCourseAssignment> findByUserId(String userId);
    List<UserCourseAssignment> findByCourseId(int courseId);
    UserCourseAssignment findFirstByCourseId(int courseId);
}
