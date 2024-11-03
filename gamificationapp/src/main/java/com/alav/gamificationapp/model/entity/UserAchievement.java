package com.alav.gamificationapp.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who unlocked the achievement
    @ManyToOne
    @JoinColumn(name = "achievement_id", nullable = false)
    private User user;

    // Achievement unlocked by the user
    @ManyToOne
    @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;

    // Date when the achievement was unlocked
    @Column(nullable = false)
    private LocalDateTime unlockedAt;

    // Current progress of the achievement(for achievements that require progress)
    private Integer currentProgress;

    // Required progress to unlock the achievement
    private Integer requiredProgress;
}
