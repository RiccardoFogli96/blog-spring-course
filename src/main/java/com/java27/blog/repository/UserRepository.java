package com.java27.blog.repository;

import com.java27.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.uuid = ?1")
    Optional<User> findByUuid(String uuid);
}
