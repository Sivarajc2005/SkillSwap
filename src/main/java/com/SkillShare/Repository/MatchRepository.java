package com.SkillShare.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SkillShare.Model.Match;
import com.SkillShare.Model.MatchStatus;
import com.SkillShare.Model.Skill;
import com.SkillShare.Model.User;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    // Find matches by a specific user (either as user1 or user2)
    List<Match> findByUser1OrUser2(User user1, User user2);

    // Find matches where a specific user is involved and status is pending
    List<Match> findByUser1OrUser2AndStatus(User user1, User user2, MatchStatus status);

    // Find all matches for a specific skill
    List<Match> findByMatchedSkill(Skill skill);

    // Find matches where a specific user and skill are involved
    List<Match> findByUser1AndMatchedSkillOrUser2AndMatchedSkill(User user1, Skill skill1, User user2, Skill skill2);

    List<Match> findByUser2AndStatus(User user2, MatchStatus status);

}

