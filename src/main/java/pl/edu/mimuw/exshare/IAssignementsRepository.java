package pl.edu.mimuw.exshare;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAssignementsRepository extends JpaRepository<UserCourseAssignement, UserCourseAssignementId> {
    List<UserCourseAssignement> findByUserId(String userId);
    List<UserCourseAssignement> findByCourseId(int courseId);
}
