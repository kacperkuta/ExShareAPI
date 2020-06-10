package pl.edu.mimuw.exshare.CommentsController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, CommentId> {
    List<Comment> findAllByCourseIdAndTestNameAndExerciseNumberAndSolutionNumber(
            int courseId, String testName, int exerciseNumber, int solutionNumber);
}
