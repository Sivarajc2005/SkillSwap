package com.SkillShare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillShare.Model.Skill;
import com.SkillShare.Model.SkillType;
import com.SkillShare.Model.User;
import com.SkillShare.Model.UserSkill;
import com.SkillShare.Repository.SkillRepository;
import com.SkillShare.Repository.UserRepository;
import com.SkillShare.Repository.UserSkillRepository;


@Service
public class ProfileService {

    @Autowired
    private SkillRepository skillrepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    public UserSkill updataSkills(long  userid,String skill,String type){
        skill = skill.toLowerCase();
        Skill uskill = skillrepository.findBySkillName(skill);

        if(uskill == null){
            Skill newskill = new Skill(skill);
            skillrepository.save(newskill);
        }

        uskill = skillrepository.findBySkillName(skill);
        User user =userRepository.findById(userid);
        SkillType stype;
        
        if(type.equals("offering") ){
            
            stype = SkillType.OFFERED;
        }
        else{
            stype = SkillType.NEEDED;
        }

        UserSkill skillupdate = new UserSkill(user,uskill,stype);
        userSkillRepository.save(skillupdate);
        return skillupdate;

    }
}
