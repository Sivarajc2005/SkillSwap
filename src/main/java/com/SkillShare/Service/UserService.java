package com.SkillShare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillShare.Exception.InvalidEntityException;
import com.SkillShare.Model.User;
import com.SkillShare.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name,String email,String password) {
        User user = new User(name, email, password);
        userRepository.save(user);
        return user;
    }

    public User LoginUser(String email , String password) throws InvalidEntityException{
        try{
            User  user =userRepository.findByEmail(email);
            if(user.getPassword().equals(password)){
                return user;
            }
            else{
                throw new InvalidEntityException("Wrong Password");
            }
        } catch(Exception e){
            throw new InvalidEntityException("No User Found On Email Id");
        }
    }
    
}
