package com.prog.repository;

import org.springframework.data.jpa.repository.*;

import com.prog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}

