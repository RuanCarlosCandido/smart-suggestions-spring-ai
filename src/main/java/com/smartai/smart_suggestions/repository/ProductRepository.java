package com.smartai.smart_suggestions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartai.smart_suggestions.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }
