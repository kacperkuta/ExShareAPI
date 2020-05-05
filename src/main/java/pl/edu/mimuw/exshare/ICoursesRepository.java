package pl.edu.mimuw.exshare;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoursesRepository extends JpaRepository<Course, Integer> {
}
