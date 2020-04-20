package pl.edu.mimuw.exshare;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class DBAccess {

    private static JdbcTemplate jdbcTemplate;

    private static final String dbUrl = "jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d3q0u6fb6s18s3?user=ghblszyuhcfszz&password=9d9547e34aa5454076e03f3d5211795b9b4b1d39c095dd540f4192e0defc37e8&sslmode=require";

    private static void initJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void createTables() {

        initJdbcTemplate();

        jdbcTemplate.execute("create table exshare_user (\n" +
                "user_id VARCHAR (100) UNIQUE NOT NULL);");
        jdbcTemplate.execute("create table user_course (\n" +
                "user_id VARCHAR (100) NOT NULL,\n" +
                "course_id INTEGER NOT NULL);");
    }

    public static void addUser(String userId) {
        assert false;
        //initJdbcTemplate();
        //jdbcTemplate.update("INSERT into exshare_user (user_id) VALUES (?)", userId);
    }

    public static void deleteUser(String userId) {
        initJdbcTemplate();
        jdbcTemplate.update("DELETE from exshare_user WHERE user_id = ?", userId);
        jdbcTemplate.update("DELETE from user_course WHERE user_id = ?", userId);
    }

    public static void addCourseToUser(String userId, int courseId) {
        initJdbcTemplate();
        jdbcTemplate.update("INSERT into user_course (user_id, course_id) VALUES (?, ?)", userId, courseId);
    }

    public static void removeUserFromCourse(String userId, int courseId) {
        initJdbcTemplate();
        jdbcTemplate.update("DELETE from user_course WHERE user_id = ? and course_id = ?", userId, courseId);
    }

    public static boolean userExistsInDB(String userId) {
        initJdbcTemplate();
        List<Object> cc = jdbcTemplate.query("SELECT user_id from exshare_user WHERE user_id = \'" + userId +"\';", (rs, rownum) -> new Object());
        return !cc.isEmpty();
    }
    
    public static List<Integer> userCourses(String userId) {
        initJdbcTemplate();
        return jdbcTemplate.query("SELECT course_id from user_course WHERE user_id = \'" + userId +"\';", (rs, rownum) -> rs.getInt(1));
    }

    public static void resetDB() {
        initJdbcTemplate();
        jdbcTemplate.execute("DROP TABLE exshare_user");
        jdbcTemplate.execute("DROP TABLE user_course");
        createTables();
    }

    public static JdbcTemplate JdbcTemplate () {
        return jdbcTemplate;
    }

    public static void main(String[] args) {
        try {
            addUser("toto");
            addUser("toto");
        } catch (DataAccessException ignore) {}
    }
}