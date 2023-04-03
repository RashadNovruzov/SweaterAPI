package dev.rashad.springboot.repository;

import dev.rashad.springboot.model.Role;
import dev.rashad.springboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        user1 = User.builder()
                .username("user1")
                .email("user1@example.com")
                .password("password1")
                .role(Role.USER)
                .build();
        user2 = User.builder()
                .username("user2")
                .email("user2@example.com")
                .password("password2")
                .role(Role.ADMIN)
                .build();
        user3 = User.builder()
                .username("user3")
                .email("user3@example.com")
                .password("password3")
                .role(Role.USER)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    @DisplayName("Test findByEmail")
    public void testFindByEmail() {
        // Given
        String email = user2.getEmail();

        // When
        User found = userRepository.findByEmail(email).orElse(null);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("Test findUserWhichIsNotInDB")
    public void findUserWhichIsNotInDB() {
        // Given
        String email = "hello@gmail.com";

        // When
        User found = userRepository.findByEmail(email).orElse(null);

        // Then
        assertThat(found).isNull();
    }

    @Test
    @DisplayName("Test findByUsernameContaining")
    public void testFindByUsernameContaining() {
        // Given
        String name = "user";

        // When
        List<User> foundList = userRepository.findByUsernameContaining(name);

        // Then
        assertThat(foundList).isNotNull();
        assertThat(foundList).hasSize(3);
    }
}

