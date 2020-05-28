package pl.edu.mimuw.exshare.CourseFoldersController;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseFoldersRepository extends JpaRepository<CourseFolder, CourseFolderId> {

    @Query("SELECT c.folderName FROM CourseFolder c WHERE c.courseId = ?1")
    List<String> selectFoldersByCourseId(int courseId);

    CourseFolder findByCourseIdAndFolderName(int courseId, String folderName);
}
