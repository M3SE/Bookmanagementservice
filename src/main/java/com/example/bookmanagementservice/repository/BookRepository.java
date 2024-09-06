package com.example.bookmanagementservice.repository;

import com.example.bookmanagementservice.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

