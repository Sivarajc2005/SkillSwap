package com.SkillShare.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @ManyToOne
    @JoinColumn(name = "matched_skill_id", nullable = false)
    private Skill matchedSkill;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.PENDING;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Match() {}

    public Match(User user1, User user2, Skill matchedSkill) {
        this.user1 = user1;
        this.user2 = user2;
        this.matchedSkill = matchedSkill;
    }

   
}
