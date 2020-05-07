package pl.edu.mimuw.exshare.CoursesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.mimuw.exshare.AssignementsController.AssignementsRepository;
import pl.edu.mimuw.exshare.AssignementsController.UserCourseAssignement;

public class CoursesRepositoryCustomImpl implements CoursesRepositoryCustom {

    @Autowired
    CoursesRepositoryBasic coursesRepository;

    @Transactional
    public int getNumber(String courseName, String userId, AssignementsRepository assignementsRepository) {
        Course last = coursesRepository.findFirstByOrderByCourseIdDesc();
        if (last == null) {
            last = new Course(0, "dummy");
        }
        coursesRepository.save(new Course(last.getCourseId() + 1, courseName));
        assignementsRepository.save(new UserCourseAssignement(userId, last.getCourseId() + 1));
        return last.getCourseId() + 1;
    }
}
