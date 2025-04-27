package com.codeWithMadhuri.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithMadhuri.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
