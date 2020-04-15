package pl.edu.mimuw.exshare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBAccess {

    private static final Logger log = LoggerFactory.getLogger(DBAccess.class);

    static JdbcTemplate jdbcTemplate;

    private static final String dbUrl = "jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d3q0u6fb6s18s3?user=ghblszyuhcfszz&password=9d9547e34aa5454076e03f3d5211795b9b4b1d39c095dd540f4192e0defc37e8&sslmode=require";

    private static void initJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void createTables() {

        initJdbcTemplate();

        try {
            jdbcTemplate.execute("create table exshare_user (\n" +
                    "user_id VARCHAR (100) UNIQUE NOT NULL,\n" +
                    "course_id INTEGER);");
        } catch (Exception e) {
            System.err.println("Creating exshare_user corrupted. Exception msg: " + e.getMessage());
        }
        try {
            jdbcTemplate.execute("create table user_course (\n" +
                    "user_id VARCHAR (100) NOT NULL,\n" +
                    "course_id INTEGER NOT NULL);");
        } catch (Exception e) {
            System.err.println("Creating user_course corrupted. Exception msg: " + e.getMessage());
        }
    }

    public static void addUser(String userID) {
        initJdbcTemplate();
        try {
            jdbcTemplate.update("INSERT into exshare_user (user_id) VALUES (?)", userID);
        } catch (Exception e) {
            System.err.println("Adding user failed with msg " + e.getMessage());
        }
    }

    public static void addCourseToUser(String userID, int courseID) {
        initJdbcTemplate();
        try {
            jdbcTemplate.update("INSERT into user_course (user_id, course_id) VALUES (?, ?)", userID, courseID);
        } catch (Exception e) {
            System.err.println("Adding user failed with msg " + e.getMessage());
        }
    }

    public static boolean userExistsInDB(String userId) {
        initJdbcTemplate();
        List<Customer> cc = jdbcTemplate.query("SELECT " + userId + " from user_course;", (rs, rownum) -> {
            Customer c = new Customer();
            c.setId(rs.getString(1));
            c.setCourse_id(rs.getInt(2));
            return c;
        });
        return !cc.isEmpty();
    }
/*
    public static void main(String[] args) {
        initJdbcTemplate();
        List<Customer> cc = jdbcTemplate.query("SELECT * from user_course;", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
                Customer c = new Customer();
                c.setId(rs.getString(1));
                c.setCourse_id(rs.getInt(2));
                return c;
            }
        });

        System.out.println(cc.get(0).course_id);
    }
 */
}