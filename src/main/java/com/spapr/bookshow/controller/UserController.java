package com.spapr.bookshow.controller;

import com.spapr.bookshow.Constants.Constant;
import com.spapr.bookshow.controller.request.LoginRequest;
import com.spapr.bookshow.factory.ResponseFactory;
import com.spapr.bookshow.model.User;
import com.spapr.bookshow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResponseFactory responseFactory;

    // Get All Users
    @GetMapping(value = "/all")
    public ResponseEntity getAllUsers() {
        log.info("========== Start get all users ==========");

        List<User> users = userService.getAllUser();
        String message = "";
        int responseCounter = users.size();
        if (responseCounter == 0) {
            message = "No users to display";
        }
        log.info("========== user count ========== {}", responseCounter);

        System.out.print(responseCounter);
        return responseFactory.success(users, message);
    }

    // Create a new User
    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        log.info("========== Start create new user ========== {}", user);

        User newUser = userService.createUser(user);
        if (newUser == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, Constant.USER_REGISTER_FAIL);
        }
        log.info("========== new user ========== {}", newUser);
        return responseFactory.success(newUser, Constant.USER_REGISTER_SUCCESS);
    }


    @PostMapping(value = "/login")
    public ResponseEntity loginApi(@Valid @RequestBody LoginRequest loginRequest){
        log.info("==========  login email ========== {}", loginRequest.getEmail());
        log.info("==========  login password ========== {}", loginRequest.getPassword());

        log.info("========== Start Login Service ========== {}");

        User getOne = userService.findUserIdByEmail(loginRequest.getEmail(), loginRequest.getPassword());
        if (getOne == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, "Incorrect user details");
        }
        log.info("========== login user ========== {}", getOne.getEmail());
        return responseFactory.success(getOne, Constant.USER_LOGIN_SUCCESS);
    }
    @PostMapping(value = "/forgotpassword")
    public ResponseEntity forgotPassword(@PathVariable String email) {

        return responseFactory.success("", Constant.USER_FORGOTPASSWORD_SUCCESS);
    }


    // Get single user
    @GetMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") Integer userId){

        log.info("========== Start get user by ID ========== {}", userId);

        User user = userService.getUserById(userId);
        if (user == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, "User id doesn't exist");
        }
        log.info("========== found user ========== {}", user);

        return responseFactory.success(user, "");
    }

    // Update user
    @PostMapping(value = "/update/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") Integer userId,
                                     @Valid @RequestBody User updateUser) {
        log.info("========== Start update the user by ID ========== {}",updateUser);

        User user = userService.updateUserById(userId, updateUser);
        if (user == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, "User not updated!");
        }
        log.info("========== updated user ========== {}", user);

        return responseFactory.success(user, Constant.USER_UPDATE_SUCCESS);
    }

    // Delete a User
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Integer userId) {

        log.info("========== Start delete the user by ID ========== {}", userId);

        User user = userService.deleteUser(userId);
        if (user == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, Constant.USER_DELETE_FAIL);
        }
        log.info("========== deleted user ========== {}", user);
        return responseFactory.success(user, Constant.USER_DELETE_SUCCESS);
    }

}
