package pl.edu.mimuw.exshare.TestTasksController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class TestTaskController {

    @Autowired
    TestTasksRepository repository;

    @GetMapping("/getTestTasks/{courseId}/{testName}")
    public List<Integer> getTestTasks(@PathVariable int courseId, @PathVariable String testName) {
        List<Task> tasks = repository.findAllByCourseIdAndTestName(courseId, testName);
        List<Integer> tasksOrd = new ArrayList<>();
        for (Task t : tasks) {
            tasksOrd.add(t.taskNum);
        }
        tasksOrd.sort(Integer::compareTo);
        return tasksOrd;
    }

    @PutMapping("/addTestTask/{courseId}/{testName}/{taskNum}")
    public void addTestTask(@PathVariable int courseId, @PathVariable String testName, @PathVariable int taskNum) {
        repository.save(new Task(courseId, testName, taskNum));
    }
}
