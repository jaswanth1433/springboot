package com.example.jpaprac;
import com.example.jpaprac.model.User;
import com.example.jpaprac.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest/*(basePackageClasses = UserRepository.class)*/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;
    private User user1;
    private User user2;
    private User user3;
    @BeforeEach
    void setUp() {
        userRepository.deleteAllInBatch();
        user1 = new User("sai", "sai@example.com");
        user2 = new User("aryan", "aryan@example.com");
        user3 = new User("Ben", "Ben@example.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void testFindByEmailFound() {
        Optional<User> foundUser = userRepository.findByEmail(user1.getEmail());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(user1.getName());
        assertThat(foundUser.get().getEmail()).isEqualTo(user1.getEmail());
        assertThat(foundUser.get().getId()).isEqualTo(user1.getId());
    }
    @Test
    void testFindByEmailNotFound() {
        Optional<User> foundUser = userRepository.findByEmail("notemail@example.com");
        assertThat(foundUser).isNotPresent();

    }
    @Test
    void testSaveUser(){
        User newUser = new User("Charlie Chaplin", "charlin@example.com");
        User savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Charlie Chaplin");
    }
    @Test
    void testUpdateUser(){
        String newname = "jaswanth reddy";
        String newemail = "jaswanthreddy@gmail.com";
        User user3 = new User("Mahi", "Mahi@example.com");
        user3.setName(newname);
        user3.setEmail(newemail);
        User updatedUser = userRepository.save(user3);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo(newname);
        assertThat(updatedUser.getEmail()).isEqualTo(newemail);

    }
}