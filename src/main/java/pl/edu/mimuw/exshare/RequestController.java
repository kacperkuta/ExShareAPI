package pl.edu.mimuw.exshare;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {
    @PutMapping("/addUser/{id}")
    private void addUser(@PathVariable String id) {
        DBAccess.addUser(id);
    }

    @PutMapping("/assignUserToCourse")
    private void assignUser(@RequestParam String user_id, @RequestParam int course_id) {
        DBAccess.addCourseToUser(user_id, course_id);
    }

    @GetMapping("/userExists/{id}")
    private boolean userExists(@PathVariable String id) {
        return DBAccess.userExistsInDB(id);
    }

    @GetMapping("/test")
    public String testy() {
        return "OK";
    }

    @GetMapping("/userCourses/{id}")
    private List<Integer> userCourses (@PathVariable String id) {
        return DBAccess.userCourses(id);
    }
}
