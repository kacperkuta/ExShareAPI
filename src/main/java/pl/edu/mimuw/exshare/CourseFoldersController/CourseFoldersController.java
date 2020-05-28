package pl.edu.mimuw.exshare.CourseFoldersController;

import org.json.JSONArray;
import org.json.JSONException;
import org.postgresql.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseFoldersController {

    @Autowired
    CourseFoldersRepository courseFoldersRepository;

    @PutMapping("/addCourseFolder/{courseId}/{folderTestsString}")
    public void addCourseFolder(@PathVariable int courseId, @PathVariable String folderTestsString) throws JSONException {
        JSONArray folderTests = new JSONArray(new String(Base64.decode(folderTestsString)));

        JSONArray withoutName = new JSONArray();
        for (int i = 1; i < folderTests.length(); i++) {
            withoutName.put(folderTests.get(i));
        }
        courseFoldersRepository.save(new CourseFolder(courseId, folderTests.get(0).toString(), withoutName.toString()));
    }

    @GetMapping("/getCourseFolders/{courseId}")
    public List<String> getCourseFolders(@PathVariable int courseId) {
        return courseFoldersRepository.selectFoldersByCourseId(courseId);
    }

    @GetMapping("/getFolderTests/{courseId}/{folderName}")
    public String getFolderTests(@PathVariable int courseId, @PathVariable String folderName) throws JSONException {
        return courseFoldersRepository.findByCourseIdAndFolderName(courseId, folderName).getFolderTests();
    }
}
