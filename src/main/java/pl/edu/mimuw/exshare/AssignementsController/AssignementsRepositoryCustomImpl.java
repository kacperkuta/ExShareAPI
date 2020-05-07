package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.mimuw.exshare.DBAccessException;

public class AssignementsRepositoryCustomImpl implements AssignementsRepositoryCustom {

    @Autowired
    AssignementsRepositoryBasic assignementsRepository;

    @Transactional
    public void assignUserToCourse(String userId, int courseId) throws DBAccessException {
        if (assignementsRepository.findFirstByCourseId(courseId) == null) {
            throw new DBAccessException("No such course");
        }
        assignementsRepository.save(new UserCourseAssignement(userId, courseId));
    }
}