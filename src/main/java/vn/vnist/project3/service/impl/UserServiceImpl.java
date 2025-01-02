package vn.vnist.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.vnist.project3.dto.CreateUserDto;
import vn.vnist.project3.models.UserModel;
import vn.vnist.project3.repository.UserRepository;
import vn.vnist.project3.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserModel createUser(CreateUserDto request) {
        var userModel = new UserModel();
        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userModel.setAge(request.getAge());
        return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }
}
