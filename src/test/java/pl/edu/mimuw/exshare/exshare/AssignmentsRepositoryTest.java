package pl.edu.mimuw.exshare.exshare;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.edu.mimuw.exshare.AssignementsController.AssignmentsRepository;
import pl.edu.mimuw.exshare.AssignementsController.UserCourseAssignment;
import pl.edu.mimuw.exshare.CoursesController.CoursesRepository;
import pl.edu.mimuw.exshare.DBAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
public class AssignmentsRepositoryTest {

    @Autowired
    AssignmentsRepository assignmentsRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Test
    void assignUserToFakeCourseTest() {
        try {
            //-1 courseId is surely incorrect
            assignmentsRepository.assignUserToCourse("fakeUser", -1);
            fail("Exception should be thorwn");
        } catch (DBAccessException e) {
            //correct behaviour
        } catch (Exception e) {
            fail("Unhandled exception");
        }
    }

    @Test
    void assignUserToCourseTest() {
        try {
            //to enable saving assignments to course -1
            assignmentsRepository.save(new UserCourseAssignment("fakeUser", -1));
            assignmentsRepository.assignUserToCourse("fake1", -1);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void getUserCoursesTest() {
        try {
            assignmentsRepository.save(new UserCourseAssignment("fake1", -1));
            assignmentsRepository.save(new UserCourseAssignment("fake1", -2));
            assignmentsRepository.save(new UserCourseAssignment("fake1", -3));
            assignmentsRepository.save(new UserCourseAssignment("fake1", -4));
            List<UserCourseAssignment> userCourses =  assignmentsRepository.findByUserId("fake1");
            Assert.assertEquals(4, userCourses.size());
            for (UserCourseAssignment a : userCourses) {
                assert(a.getCourseId() <= -1 && a.getCourseId() >= -4);
            }
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

}
