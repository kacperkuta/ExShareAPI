package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.mimuw.exshare.DBAccessException;

public class AssignmentsRepositoryCustomImpl implements AssignmentsRepositoryCustom {

    @Autowired
    AssignmentsRepositoryBasic assignmentsRepository;

    @Transactional
    public void assignUserToCourse(String userId, int courseId) throws DBAccessException {
        if (assignmentsRepository.findFirstByCourseId(courseId) == null) {
            throw new DBAccessException("No such course");
        }
        assignmentsRepository.save(new UserCourseAssignment(userId, courseId));
    }
}