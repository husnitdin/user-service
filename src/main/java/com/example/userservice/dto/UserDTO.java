package com.example.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String amountOfPosts;

}
