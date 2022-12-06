package com.example.userservice.util;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueMapper {

    public static UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setAmountOfPosts(userDTO.getAmountOfPosts());

        return userEntity;
    }

    public static UserDTO convertToDTO(UserEntity entity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setAmountOfPosts(entity.getAmountOfPosts());
        return userDTO;
    }


    public static String jsonAsString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
