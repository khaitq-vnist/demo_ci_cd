package vn.vnist.project3.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vn.vnist.project3.dto.CreateUserDto;
import vn.vnist.project3.models.UserModel;
import vn.vnist.project3.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Test
    public void testCreateUserWithValidInput() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository);

        CreateUserDto request = new CreateUserDto("John Doe", "john.doe@example.com", 30);
        UserModel expectedUser = new UserModel(null, "John Doe", "john.doe@example.com", 30);

        Mockito.when(mockUserRepository.save(Mockito.any(UserModel.class))).thenReturn(expectedUser);

        UserModel createdUser = userService.createUser(request);

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        assertEquals(30, createdUser.getAge());
    }
}
