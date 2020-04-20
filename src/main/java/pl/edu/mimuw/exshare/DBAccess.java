package pl.edu.mimuw.exshare;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.*;

public class DBAccess {

    private static JdbcTemplate jdbcTemplate;

    private static final String dbUrl = "jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d3q0u6fb6s18s3?user=ghblszyuhcfszz&password=9d9547e34aa5454076e03f3d5211795b9b4b1d39c095dd540f4192e0defc37e8&sslmode=require";

    /**
     * Initializes jdbcTemplate with DataSource connected with given database URL.
     */
    private static void initJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Creates tables necessary for ExShare mobile app.
     */
    public static void createTables() {

        initJdbcTemplate();

        jdbcTemplate.execute("create table exshare_user (\n" +
                "user_id VARCHAR (100) UNIQUE NOT NULL);");
        jdbcTemplate.execute("create table user_course (\n" +
                "user_id VARCHAR (100) NOT NULL,\n" +
                "course_id INTEGER NOT NULL);");
    }

    /**
     * Adds user to database.
     * @param userId User identifier - must be unique.
     * @throws DataAccessException On user duplication.
     */
    public static void addUser(String userId) {
        initJdbcTemplate();
        jdbcTemplate.update("INSERT into exshare_user (user_id) VALUES (?)", userId);
    }

    /**
     * Removes user from database if such user exists.
     * @param userId User identifier.
     */
    public static void deleteUser(String userId) {
        initJdbcTemplate();
        jdbcTemplate.update("DELETE from exshare_user WHERE user_id = ?", userId);
        jdbcTemplate.update("DELETE from user_course WHERE user_id = ?", userId);
    }

    /**
     * Assigns course identifier to user identifier.
     * @param userId User identifier.
     * @param courseId Course identifier.
     * @throws DBAccessException On assignment to non-existent user
     */
    public static void addCourseToUser(String userId, int courseId) throws DBAccessException {
        if (!userExistsInDB(userId)) {
            throw new DBAccessException("Course assignement to non-existant user.");
        }
        initJdbcTemplate();
        jdbcTemplate.update("INSERT into user_course (user_id, course_id) VALUES (?, ?)", userId, courseId);
    }

    /**
     * Removes relation user-course from database.
     * @param userId User identifier.
     * @param courseId Course identifier.
     * @throws DBAccessException On non-existent user removal.
     */
    public static void removeUserFromCourse(String userId, int courseId) throws DBAccessException {
        if (!userExistsInDB(userId)) {
            throw new DBAccessException("Non-existent user relation deleting.");
        }
        initJdbcTemplate();
        jdbcTemplate.update("DELETE from user_course WHERE user_id = ? and course_id = ?", userId, courseId);
    }


    /**
     * Checks if user exists in database.
     * @param userId User identifier.
     * @return True if exists, false otherwise.
     */
    public static boolean userExistsInDB(String userId) {
        initJdbcTemplate();
        List<Object> cc = jdbcTemplate.query("SELECT user_id from exshare_user WHERE user_id = \'" + userId +"\';", (rs, rownum) -> new Object());
        return !cc.isEmpty();
    }

    /**
     * Checks courses assigned to user.
     * @param userId User identifier.
     * @return Set of user courses.
     * @throws DBAccessException On non-existent user call.
     */
    public static Set<Integer> userCourses(String userId) throws DBAccessException {
        if (!userExistsInDB(userId)) {
            throw new DBAccessException("Non-existent user courses query.");
        }
        initJdbcTemplate();
        List<Integer> result = jdbcTemplate.query("SELECT course_id from user_course WHERE user_id = \'" + userId +"\';", (rs, rownum) -> rs.getInt(1));
        return new HashSet<Integer>(result);
    }

    /**
     * Resets database - removes tables and creates again.
     */
    public static void resetDB() {
        initJdbcTemplate();
        try {
            jdbcTemplate.execute("DROP TABLE exshare_user");
        } catch (DataAccessException ignore) {
            //possibly there was no such table
        }
        try {
            jdbcTemplate.execute("DROP TABLE user_course");
        } catch (DataAccessException ignore) {
            //possibly there was no such table
        }
        createTables();
    }
}