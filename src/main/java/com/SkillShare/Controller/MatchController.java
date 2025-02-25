package com.SkillShare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SkillShare.Exception.InvalidEntityException;
import com.SkillShare.Model.Match;
import com.SkillShare.Model.Skill;
import com.SkillShare.Model.User;
import com.SkillShare.Model.UserSkill;
import com.SkillShare.Service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{userId}")
    public List<UserSkill> getMatches(@PathVariable int userId) {
        return matchService.findMatchesForUser(userId);
    }

     @PostMapping("/request")
    public Match sendRequest(@RequestParam Long user1Id, @RequestParam Long user2Id, 
                             @RequestParam Long skill1Id) throws InvalidEntityException {
        User user1 = new User();
        user1.setId(user1Id);

        User user2 = new User();
        user2.setId(user2Id);

        Skill skill1 = new Skill();
        skill1.setId(skill1Id);

        return matchService.SentSkillSwapRequest(user1, user2, skill1);
    }

    // Get pending requests for a user
    @GetMapping("/pending/{userId}")
    public List<Match> getPendingRequests(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return matchService.getPendingRequests(user);
    }

    // Accept a request
    @PostMapping("/accept/{matchId}")
    public Match acceptRequest(@PathVariable Long matchId) {
        return matchService.acceptRequest(matchId);
    }

    // Reject a request
    @PostMapping("/reject/{matchId}")
    public Match rejectRequest(@PathVariable Long matchId) {
        return matchService.rejectRequest(matchId);
    }
    
}

