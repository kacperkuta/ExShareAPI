package pl.edu.mimuw.exshare.CoursesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.mimuw.exshare.AssignementsController.AssignmentsRepository;
import pl.edu.mimuw.exshare.AssignementsController.UserCourseAssignment;


public class CoursesRepositoryCustomImpl implements CoursesRepositoryCustom {

    @Autowired
    CoursesRepositoryBasic coursesRepository;

    @Transactional
    public int getNumber(String userId, String courseName, AssignmentsRepository assignementsRepository) {
        Course last = coursesRepository.findFirstByOrderByCourseIdDesc();
        if (last == null) {
            last = new Course(0, "dummy");
        }
        coursesRepository.save(new Course(last.getCourseId() + 1, courseName));
        assignementsRepository.save(new UserCourseAssignment(userId, last.getCourseId() + 1));
        return last.getCourseId() + 1;
    }
}
