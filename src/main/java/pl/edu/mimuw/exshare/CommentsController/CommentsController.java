package pl.edu.mimuw.exshare.CommentsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    CommentsRepository repository;

    @GetMapping("/getComments/{courseId}/{testName}/{exerciseNumber}/{solutionNumber}")
    public List<String> getComments(@PathVariable int courseId, @PathVariable String testName,
                                     @PathVariable int exerciseNumber, @PathVariable int solutionNumber) {
        List<Comment> list = repository.findAllByCourseIdAndTestNameAndExerciseNumberAndSolutionNumber(courseId,
                testName, exerciseNumber, solutionNumber);

        List<String> contents_list = new ArrayList<>();
        list.sort(Comparator.comparing(comment -> comment.time));

        for (Comment c : list) {
            contents_list.add(c.content);
        }

        return contents_list;
    }

    @PutMapping("/addComment/{courseId}/{testName}/{exerciseNumber}/{solutionNumber}/{content}")
    public void addComment(@PathVariable int courseId, @PathVariable String testName, @PathVariable int exerciseNumber,
                           @PathVariable int solutionNumber, @PathVariable String content) {
        repository.save(new Comment(courseId, testName, exerciseNumber, solutionNumber, content));
    }
}
