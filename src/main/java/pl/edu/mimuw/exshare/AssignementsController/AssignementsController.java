package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.mimuw.exshare.DBAccessException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AssignementsController {

    @Autowired
    AssignementsRepository assignementsRepository;

    @PutMapping("/assignUserToCourse/{user_id}/{course_id}")
    public void assignUser(@PathVariable String user_id, @PathVariable int course_id) throws DBAccessException {
        assignementsRepository.assignUserToCourse(user_id, course_id);
    }

    @GetMapping("/userCourses/{userId}")
    public Set<Integer> userCourses(@PathVariable String userId) {
        List<UserCourseAssignement> userCourses =  assignementsRepository.findByUserId(userId);
        Set<Integer> coursesSet = new HashSet<>();
        for (UserCourseAssignement el : userCourses) {
            coursesSet.add(el.getCourseId());
        }
        return coursesSet;
    }
}
