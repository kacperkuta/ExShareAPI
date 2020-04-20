package pl.edu.mimuw.exshare.exshare;
import org.junit.jupiter.params.provider.NullSource;
import pl.edu.mimuw.exshare.DBAccess;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DBAccessTests {

    @Test
    @Order(1)
    void addSingleUser() {
        try {
            DBAccess.deleteUser("dummy1");
        } catch (DataAccessException ignore) {
            fail("Deleting user should not fail.");
        }
        try {
            DBAccess.addUser("dummy1");
            assert DBAccess.userExistsInDB("dummy1");
        } catch (DataAccessException e) {
            fail("Failed adding user");
        }
    }

    @Test
    @Order(2)
    void removeSingleUser() {
        try {
            DBAccess.addUser("dummy1");
        } catch (DataAccessException ignore) {
            //If user exists, exception is thrown.
        }
        try {
            DBAccess.deleteUser("dummy1");
            assert !DBAccess.userExistsInDB("dummy1");
        } catch (DataAccessException e) {
            fail("Failed deleting user");
        }
    }

    @Test
    @Order(3)
    void multipleUserAdd() {
        try {
            DBAccess.addUser("dummy1");
        } catch (DataAccessException ignore) {
            //If user exists, exception is thrown.
        }
        for (int i = 0; i < 10; i++) {
            try {
                DBAccess.addUser("dummy1");
                fail("Multiple user add should not succeed.");
            } catch (DataAccessException ignore) {
                //Everything is ok
            }
        }
        assert true;
    }

    @Test
    @Order(4)
    void multipleUserDelete() {
        try {
            DBAccess.deleteUser("dummy1");
        } catch (DataAccessException ignore) {
            fail("Deleting user should not fail.");
        }
        for (int i = 0; i < 10; i++) {
            try {
                DBAccess.deleteUser("dummy1");
            } catch (DataAccessException e) {
                fail("Deleting non-existent user should succeed");
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @Order(5)
    void fewUserAdd(String userId) {
        try {
            DBAccess.deleteUser(userId);
        } catch (DataAccessException e) {
            fail("Exception should not be thrown while deleting.");
        }

        try {
            DBAccess.addUser(userId);
        } catch (DataAccessException e) {
            fail("User should be added.");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @Order(6)
    void fewUserExists(String userId) {
        assert DBAccess.userExistsInDB(userId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @Order(7)
    void fewUserDelete(String userId) {
        try {
            DBAccess.deleteUser(userId);
        } catch (DataAccessException e) {
            fail("Exception should not be thrown while deleting.");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @Order(8)
    void fewUserNotExists(String userId) {
        assert !DBAccess.userExistsInDB(userId);
    }

    @ParameterizedTest
    @NullSource
    @Order(9)
    void nullUserIsNotValid(String userId) {
        try {
            DBAccess.addUser(userId);
            fail("Null userId is not valid!");
        } catch (DataAccessException ignore) {
            //Exception should be thrown.
        }
        try {
            DBAccess.addCourseToUser(userId, 123);
            fail("Null userId in relation userId-courseId is not valid!");
        } catch (DataAccessException ignore) {
            //Everything is ok
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @Order(10)
    void assignCourseToUser(int courseId) {
        DBAccess.addCourseToUser("dummy1", courseId);
    }


}

