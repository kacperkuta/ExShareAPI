package pl.edu.mimuw.exshare.UsersController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/userExists/{userId}")
    public boolean userExists(@PathVariable String userId) {
        return usersRepository.existsById(userId);
    }

    @PutMapping("/addUser/{userId}")
    public void addUser(@PathVariable String userId) {
        usersRepository.save(new User(userId));
    }
}
