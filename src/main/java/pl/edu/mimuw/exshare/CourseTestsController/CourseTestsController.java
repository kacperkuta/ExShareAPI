package pl.edu.mimuw.exshare.CourseTestsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class CourseTestsController {

    @Autowired
    CourseTestsRepository courseTestsRepository;

    @GetMapping("/getCourseTests/{courseId}")
    public Set<String> getCourseTests(@PathVariable int courseId) {
        Set<Test> set = courseTestsRepository.findByCourseId(courseId);
        Set<String> result= new HashSet<>();
        for (Test t : set) {
            result.add(t.testName);
        }
        return result;
    }

    @PutMapping("/addTestToCourse/{courseId}/{testName}")
    public void addTestToCourse(@PathVariable int courseId, @PathVariable String testName) {
        courseTestsRepository.save(new Test(courseId, testName));
    }
}
