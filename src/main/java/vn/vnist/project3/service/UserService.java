package vn.vnist.project3.service;

import org.springframework.stereotype.Service;
import vn.vnist.project3.dto.CreateUserDto;
import vn.vnist.project3.models.UserModel;

import java.util.List;

@Service
public interface UserService {
    UserModel createUser(CreateUserDto request);
    List<UserModel> getAllUser();
}
