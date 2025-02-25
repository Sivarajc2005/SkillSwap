package com.SkillShare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SkillShare.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);
}
