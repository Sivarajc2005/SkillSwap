package com.SkillShare.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillShare.Exception.InvalidEntityException;
import com.SkillShare.Model.Match;
import com.SkillShare.Model.MatchStatus;
import com.SkillShare.Model.Skill;
import com.SkillShare.Model.SkillType;
import com.SkillShare.Model.User;
import com.SkillShare.Model.UserSkill;
import com.SkillShare.Repository.MatchRepository;
import com.SkillShare.Repository.UserRepository;
import com.SkillShare.Repository.UserSkillRepository;

@Service
public class MatchService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;


    //recomentation for front page 

    public List<UserSkill> findMatchesForUser(long userid) {
        List<UserSkill> neededSkills = userSkillRepository.findByUserIdAndSkillType(userid, SkillType.NEEDED);
        // List<Match> matches = new ArrayList<>();
        List<UserSkill> provider = new ArrayList<>();

        for (UserSkill neededSkill : neededSkills) {
            List<UserSkill> offeringUsers = userSkillRepository.findByUserIdAndSkillType(neededSkill.getSkill().getId(), SkillType.OFFERED);

            User user = userRepository.findById(userid);
            
            for (UserSkill offeringUser : offeringUsers) {
                if (!offeringUser.getUser().equals(user)) {
                    provider.add(offeringUser);
                    
                }
            }
        }
        return provider;
    }


    //Requesting swap the skill
    
    public Match SentSkillSwapRequest(User user1,User user2 , Skill skill) throws InvalidEntityException {
        try {
            Match match = new Match(user1, user2,skill);
            matchRepository.save(match);
            return match;
            
        } catch (Exception e) {
            throw new InvalidEntityException("failed to sent the Request");
        }
       
    }

    
//    public Match sendSkillSwapRequest(User user1, User user2, Skill skill1, Skill skill2) {
//         Match match = new Match();
//         match.setUser1(user1);
//         match.setUser2(user2);
//         match.setSkill1(skill1);
//         match.setSkill2(skill2);
//         match.setStatus(MatchStatus.PENDING);
//         return matchRepository.save(match);
//     }

    // Get pending requests for a user
    public List<Match> getPendingRequests(User user) {
        return matchRepository.findByUser2AndStatus(user, MatchStatus.PENDING);
    }

    // Accept a request
    public Match acceptRequest(Long matchId) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setStatus(MatchStatus.ACCEPTED);
            return matchRepository.save(match);
        }
        return null; // Handle error appropriately
    }

    // Reject a request
    public Match rejectRequest(Long matchId) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setStatus(MatchStatus.REJECTED);
            return matchRepository.save(match);
        }
        return null; // Handle error appropriately  
    }  
  
}

