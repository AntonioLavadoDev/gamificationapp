package com.alav.gamificationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alav.gamificationapp.model.entity.Achievement;
import com.alav.gamificationapp.model.entity.User;
import com.alav.gamificationapp.model.entity.UserAchievement;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {

    // Check if a user has a specific achievement
    boolean existsByUserIdAndAchievementId(User user, Achievement achievement);

    // Find all achievements of a user
    List<UserAchievement> findByUser(User user);

    List<UserAchievement> findByAchievement(Achievement achievement);

    void deleteByUser(User user);
}
