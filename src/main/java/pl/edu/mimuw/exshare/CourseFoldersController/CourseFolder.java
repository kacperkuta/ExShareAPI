package pl.edu.mimuw.exshare.CourseFoldersController;
import javax.persistence.*;

@Entity
@Table(name="course_folders")
@IdClass(CourseFolderId.class)
public class CourseFolder {
    @Id
    @Column(name="course_id")
    private int courseId;

    @Id
    @Column(name="folder_name")
    private String folderName;

    @Column(name="folder_tests")
    private String folderTests;

    public CourseFolder(int courseId, String folderName, String folderTests) {
        this.courseId = courseId;
        this.folderName = folderName;
        this.folderTests = folderTests;
    }

    public String getFolderTests() {
        return folderTests;
    }

    public CourseFolder() {
    }
}
