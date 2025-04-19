package com.jaeyong.todolist.repository;

import com.jaeyong.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
