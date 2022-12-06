package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.exception.UserServiceBusinessException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.util.ValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public UserDTO createNewUser(UserDTO userDTO) throws UserServiceBusinessException {

        UserDTO newUser;

        try {
            UserEntity userEntity = ValueMapper.convertToEntity(userDTO);
            UserEntity saveUser = userRepository.save(userEntity);
            newUser = ValueMapper.convertToDTO(saveUser);
        } catch (Exception ex) {
            throw new UserServiceBusinessException("Exception occurred while create a new user");
        }
        return newUser;
    }

    @Cacheable(value = "user")
    @Transactional
    public UserDTO getUserById(long userId) {
        UserDTO userDTO;

        try {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
            userDTO = ValueMapper.convertToDTO(user);

        } catch (Exception ex) {
            throw new UserServiceBusinessException("Exception occurred while fetch user from Database " + userId);
        }
        return userDTO;
    }

    @Cacheable(value = "user")
    @Transactional
    public String deleteUserById(long id) {
        String msg;
        try {
            userRepository.deleteById(id);
            msg = "Successfully deleted!";
        } catch (Exception ex) {
            throw new UserServiceBusinessException("Exception occurred while fetch user from Database " + id);
        }
        return msg;
    }

    @Cacheable(value = "user")
    @Transactional
    public UserDTO editUser(long id, UserDTO userDTO) {

        UserEntity entityUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        entityUser.setUsername(userDTO.getUsername());
        entityUser.setAmountOfPosts(userDTO.getAmountOfPosts());

        userRepository.saveAndFlush(entityUser);

        userDTO = ValueMapper.convertToDTO(entityUser);

        return userDTO;
    }

}