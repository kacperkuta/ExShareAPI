package pl.edu.mimuw.exshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CourseController {

    @Autowired
    ICoursesRepository coursesRepository;

    @Autowired
    IAssignementsRepository assignementsRepository;

    @PutMapping ("/addCourse/{courseId}")
    public void addCourse(@PathVariable int courseId) {
        coursesRepository.save(new Course(courseId));
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
}
