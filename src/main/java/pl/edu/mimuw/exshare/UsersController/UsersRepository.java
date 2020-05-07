package pl.edu.mimuw.exshare.UsersController;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    List<User> findAll();
}
