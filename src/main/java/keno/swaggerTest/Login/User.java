package keno.swaggerTest.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Getter @Builder
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    protected User() {}

    public static User createUser(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

    public void changePassword(String password) {
        this.password = password;
    }

}
