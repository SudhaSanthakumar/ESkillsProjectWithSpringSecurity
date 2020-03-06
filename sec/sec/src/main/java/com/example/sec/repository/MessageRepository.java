package com.example.sec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sec.model.Message;
import com.example.sec.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	

}
