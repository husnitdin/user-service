package com.example.userservice.controller;

import com.example.userservice.dto.APIResponse;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@Slf4j
public class UserController {

    public static final String SUCCESS = "Success";
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<APIResponse> createNewUser(@RequestBody @Valid UserDTO userDTO) {

        UserDTO newUser = userService.createNewUser(userDTO);
        //Builder Design pattern

        APIResponse<UserDTO> responseDTO = APIResponse
                .<UserDTO>builder()
                .status(SUCCESS)
                .results(newUser)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {

        UserDTO userById = userService.getUserById(id);
        APIResponse<UserDTO> responseDTO = APIResponse
                .<UserDTO>builder()
                .status(SUCCESS)
                .results(userById)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable long id, @RequestBody @Valid UserDTO userDTO) {

        UserDTO userById = userService.editUser(id, userDTO);
        APIResponse<UserDTO> responseDTO = APIResponse
                .<UserDTO>builder()
                .status(SUCCESS)
                .results(userById)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
