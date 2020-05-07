package pl.edu.mimuw.exshare.AssignementsController;

import org.springframework.stereotype.Repository;
import pl.edu.mimuw.exshare.DBAccessException;

@Repository
public interface AssignementsRepositoryCustom {
    void assignUserToCourse(String userId, int courseId) throws DBAccessException;
}