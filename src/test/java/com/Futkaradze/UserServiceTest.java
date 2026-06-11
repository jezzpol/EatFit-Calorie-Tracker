package com.Futkaradze;

import com.Futkaradze.domain.Gender;
import com.Futkaradze.entity.User;
import com.Futkaradze.exception.UserException;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFullName("Futkaradze Valeria");
        user.setEmail("jezzpol@mail.ru");
        user.setAge(20);
        user.setWeight(47);
        user.setHeight(165);
        user.setGender(Gender.MALE);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser, "Созданный пользователь не должен быть null");
        assertEquals("Futkaradze Valeria", createdUser.getFullName(), "Имя пользователя должно совпадать");
        assertNotNull(createdUser.getDailyCalories(), "Ежедневная норма калорий должна быть рассчитана");

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() throws UserException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFullName("Futkaradze Valeria");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(userId);

        assertNotNull(foundUser, "Найденный пользователь не должен быть null");
        assertEquals(userId, foundUser.getId(), "ID пользователя должно совпадать");
        assertEquals("Makushev Daniil", foundUser.getFullName(), "Имя пользователя должно совпадать");

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFullName("Futkaradze Valeria");

        User user2 = new User();
        user2.setId(2L);
        user2.setFullName("Makushev Daniil");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertNotNull(users, "Список пользователей не должен быть null");
        assertEquals(2, users.size(), "Должны быть возвращены два пользователя");
        assertEquals("Futkaradze Valeria", users.get(0).getFullName(), "Первый пользователь должен быть Futkaradze Valeria");
        assertEquals("Makushev Daniil", users.get(1).getFullName(), "Второй пользователь должен быть Makushev Daniil");

        verify(userRepository, times(1)).findAll();
    }
}