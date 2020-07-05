package com.spapr.bookshow.repository;

import com.spapr.bookshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer userId);

    @Query("select u from User u where u.email = :email and u.password = :password")
    User findByEmailAndPassword(@Param("email") String email,
                                @Param("password") String password);


}
