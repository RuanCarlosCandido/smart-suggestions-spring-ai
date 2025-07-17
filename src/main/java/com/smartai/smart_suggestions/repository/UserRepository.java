package com.smartai.smart_suggestions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartai.smart_suggestions.entity.User;

public interface UserRepository extends JpaRepository<User, Long> { }
