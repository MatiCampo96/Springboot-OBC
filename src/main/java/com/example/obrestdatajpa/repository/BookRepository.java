package com.example.obrestdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.obrestdatajpa.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
