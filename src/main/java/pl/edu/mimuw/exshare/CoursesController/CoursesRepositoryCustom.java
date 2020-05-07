package pl.edu.mimuw.exshare.CoursesController;

import org.springframework.stereotype.Repository;
import pl.edu.mimuw.exshare.AssignementsController.AssignmentsRepository;

@Repository
public interface CoursesRepositoryCustom {
    int getNumber(String courseName, String userId, AssignmentsRepository assignementsRepository);
}
