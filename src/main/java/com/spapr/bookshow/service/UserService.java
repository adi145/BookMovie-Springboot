package com.spapr.bookshow.service;

import com.spapr.bookshow.exception.UserNotFoundException;
import com.spapr.bookshow.model.User;
import com.spapr.bookshow.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserIdByEmail(String email, String password) {

        User user = userRepository.findByEmailAndPassword(email,password);
        return user;
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
       return users;
    }

    public User getUserById(Integer userId){
        User user = userRepository.findUserById(userId);
        return user;
    }


    public User updateUserById(Integer userId, User user) {

        log.info("========update user======{}", user.getName() + user.getEmail() + user.getMobile_number() + user.getPassword());
        User updateUser = userRepository.findUserById(userId);
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setMobile_number(user.getMobile_number());
        updateUser.setPassword(user.getPassword());
        userRepository.save(updateUser);
        return updateUser;
    }


    public User deleteUser(Integer userid){
        User deleteUser = userRepository.findUserById(userid);
        userRepository.delete(deleteUser);
        return deleteUser;
    }


}
