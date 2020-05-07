package pl.edu.mimuw.exshare.CoursesController;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepositoryBasic extends JpaRepository<Course, Integer> {
    //TODO: It is slow. Make it faster.
    Course findFirstByOrderByCourseIdDesc();
}
