package pl.edu.mimuw.exshare.exshare;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.edu.mimuw.exshare.UsersController.User;
import pl.edu.mimuw.exshare.UsersController.UsersRepository;

@DataJpaTest
public class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @ParameterizedTest
    @ValueSource(strings = {"fake1, fake2, fake3, fake4, fake5, fake6, fake7"})
    public void multipleAddTest(String name) {
        User u = new User(name);
        usersRepository.save(u);
        Assert.assertTrue(usersRepository.findById(name).isPresent());
    }
}
