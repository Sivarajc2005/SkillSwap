package com.SkillShare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillShare.Model.SkillType;
import com.SkillShare.Model.User;
import com.SkillShare.Model.UserSkill;
import com.SkillShare.Repository.UserSkillRepository;

@Service
public class SkillListingService {
    @Autowired
    private UserSkillRepository userSkillRepository;
    public List<UserSkill> leaner(){
        return userSkillRepository.findAllBySkillType(SkillType.NEEDED);
    }

    public List<UserSkill> teacher(){
        return userSkillRepository.findAllBySkillType(SkillType.OFFERED);
    }
}
