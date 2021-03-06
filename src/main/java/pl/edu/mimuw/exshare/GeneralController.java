package pl.edu.mimuw.exshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.mimuw.exshare.AssignementsController.AssignmentsRepository;
import pl.edu.mimuw.exshare.CourseTestsController.CourseTestsRepository;
import pl.edu.mimuw.exshare.CoursesController.CoursesRepository;
import pl.edu.mimuw.exshare.TestTasksController.TestTasksRepository;
import pl.edu.mimuw.exshare.UsersController.UsersRepository;

@RestController
public class GeneralController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    AssignmentsRepository assignmentsRepository;

    @Autowired
    TestTasksRepository testTasksRepository;

    @Autowired
    CourseTestsRepository courseTestsRepository;

    @GetMapping("/clearAll")
    public void clearAll() {
        usersRepository.deleteAll();
        coursesRepository.deleteAll();
        assignmentsRepository.deleteAll();
        testTasksRepository.deleteAll();
        courseTestsRepository.deleteAll();
    }
}
