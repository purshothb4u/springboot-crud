package com.crudProject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crudProject.exception.UserNotFoundException;
import com.crudProject.model.User;
import com.crudProject.repository.UserRepository;
import com.crudProject.service.UserService;

@ExtendWith(SpringExtension.class)  // JUnit 5 with SpringExtension
@SpringBootTest
public class CrudBasicApplicationTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Mockito initialization for JUnit 5
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setRole("Admin");
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        assertEquals("Admin", savedUser.getRole());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findById(1);
        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getName());
    }

    @Test
    public void testDeleteById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyInt());
        userService.deleteById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteById_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteById(1);
        });
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        user.setName("Jane Doe");
        User updatedUser = userService.updateUser(1, user);
        assertEquals("Jane Doe", updatedUser.getName());
        assertEquals("Admin", updatedUser.getRole());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(1, user);
        });
    }

    @Test
    public void testFindByRoleCustom() {
        List<User> userList = Arrays.asList(user);
        when(userRepository.findByRole(anyString())).thenReturn(userList);
        List<User> result = userService.findByRoleCustom("Admin");
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }
}
