package pl.edu.mimuw.exshare.exshare;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.mimuw.exshare.AssignementsController.AssignmentsRepository;
import pl.edu.mimuw.exshare.CoursesController.Course;
import pl.edu.mimuw.exshare.CoursesController.CoursesRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CoursesRepositoryTest {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    AssignmentsRepository assignmentsRepository;

    int old_num = -1;

    @ParameterizedTest
    @ValueSource(strings = {"fake1, fake2, fake3, fake4, fake5, fake6, fake7"})
    public void addCourseTest(String name) {
        try {
            int num = coursesRepository.getNumber(name, "fake", assignmentsRepository);
            if (old_num == -1) {
                assert(num > 0);
            } else {
                //Numbers in one transaction should be following
                Assert.assertEquals(num, old_num + 1);
            }
            old_num = num;
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void addCourseTestName() {
        int num = coursesRepository.getNumber("fakeUser", "fakeCourse", assignmentsRepository);
        assertTrue(num > 0);
        Optional<Course> c = coursesRepository.findById(num);
        assert(c.isPresent() && c.get().getCourseName().equals("fakeCourse"));
    }

    @Test
    @Transactional
    void nonexistantCourseTest() {
        //Id == -1 is illegal in normal app usage, that's why it's absent in database.
        Optional<Course> o = coursesRepository.findById(-1);
        Assert.assertFalse(o.isPresent());
    }
}
