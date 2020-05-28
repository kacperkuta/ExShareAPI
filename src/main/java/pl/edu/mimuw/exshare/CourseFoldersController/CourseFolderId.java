package pl.edu.mimuw.exshare.CourseFoldersController;

import java.io.Serializable;
import java.util.Objects;

public class CourseFolderId implements Serializable {
    private int courseId;
    private String folderName;

    public CourseFolderId(int courseId, String folderName) {
        this.courseId = courseId;
        this.folderName = folderName;
    }

    public CourseFolderId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CourseFolderId id = (CourseFolderId)o;
        return id.courseId == courseId && id.folderName.equals(folderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderName, courseId);
    }
}
