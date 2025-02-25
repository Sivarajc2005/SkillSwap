package com.SkillShare.Repository;


import com.SkillShare.Model.User;
import com.SkillShare.Model.SkillType;
import com.SkillShare.Model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findByUserIdAndSkillType(Long userId, SkillType skillType);
}

