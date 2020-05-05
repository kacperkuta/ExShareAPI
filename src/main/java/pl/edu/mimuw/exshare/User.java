package pl.edu.mimuw.exshare;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exshare_user")
public class User {
    @Id
    @Column(name = "user_id")
    private final String userId;

    public String getUserId() {
        return userId;
    }

    public User(String id) {
        userId = id;
    }

    public User() {
        userId = "dummy";
    }
}
