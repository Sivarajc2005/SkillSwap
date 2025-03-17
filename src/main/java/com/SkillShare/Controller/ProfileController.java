package com.SkillShare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SkillShare.Exception.InvalidEntityException;
import com.SkillShare.Model.User;
import com.SkillShare.Model.UserSkill;
import com.SkillShare.Service.ProfileService;
import com.SkillShare.Service.SkillListingService;
import com.SkillShare.Service.UserService;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileservice;

    @Autowired
    private SkillListingService skillListingService;

    @Autowired
    private UserService userservice;

    @PostMapping("/updateSkill")
    public UserSkill updateSkills(@RequestParam long userid,@RequestParam String skill,@RequestParam String type){
        return profileservice.updataSkills(userid, skill, type);
    }

    @PostMapping("/CreateProfile")
    public User createprofile(@RequestParam String name , @RequestParam String email, @RequestParam String password){
        return userservice.createUser(name, email, password);
        
    }

    @PostMapping("/Login")
    public User login(@RequestParam String email,@RequestParam String password) throws InvalidEntityException{
        return userservice.LoginUser(email, password);
    }

    @GetMapping("/ListingLeanersProfiles")
    public List<UserSkill> listleaners(){
        return skillListingService.leaner();
    }

    @GetMapping("/ListingTeachersProfile")
    public List<UserSkill> listteacher(){
        return skillListingService.teacher();
    }
}
