package pl.edu.mimuw.exshare.CoursesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.mimuw.exshare.AssignementsController.AssignementsRepository;
import pl.edu.mimuw.exshare.AssignementsController.UserCourseAssignement;
import pl.edu.mimuw.exshare.DBAccessException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CoursesController {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    AssignementsRepository assignementsRepository;

    @PutMapping ("/addCourse/{courseId}/{courseName}")
    public void addCourse(@PathVariable int courseId, @PathVariable String courseName) {
        coursesRepository.save(new Course(courseId, courseName));
    }

    @GetMapping("/courseExists/{courseId}")
    public boolean courseExists(@PathVariable int courseId) {
        return coursesRepository.existsById(courseId);
    }

    @GetMapping("/courseUsers/{courseId}")
    public Set<String> courseUsers(@PathVariable int courseId) {
        List<UserCourseAssignement> usersList = assignementsRepository.findByCourseId(courseId);
        Set<String> usersSet = new HashSet<>();
        for (UserCourseAssignement el : usersList) {
            usersSet.add(el.getUserId());
        }
        return usersSet;
    }

    @GetMapping("/getNewCourse/{userId}/{courseName}")
    public int newCourse(@PathVariable String userId, @PathVariable String courseName) {
        return coursesRepository.getNumber(userId, courseName, assignementsRepository);
    }

    @GetMapping("/getCourseName/{courseId}")
    public String courseName(@PathVariable int courseId) throws DBAccessException {
        Optional<Course> c =  coursesRepository.findById(courseId);
        if (c.isPresent()) {
            return c.get().getCourseName();
        } else {
            throw new DBAccessException("No such course");
        }
    }

}



