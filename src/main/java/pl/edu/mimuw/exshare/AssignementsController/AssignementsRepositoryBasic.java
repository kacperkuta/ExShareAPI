package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignementsRepositoryBasic extends JpaRepository<UserCourseAssignement, UserCourseAssignementId> {
    List<UserCourseAssignement> findByUserId(String userId);
    List<UserCourseAssignement> findByCourseId(int courseId);
    UserCourseAssignement findFirstByCourseId(int courseId);
}
