package com.alav.gamificationapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alav.gamificationapp.model.entity.Achievement;
import com.alav.gamificationapp.model.entity.User;
import com.alav.gamificationapp.model.entity.UserAchievement;
import com.alav.gamificationapp.repository.AchievementRepository;
import com.alav.gamificationapp.repository.UserAchievementRepository;
import com.alav.gamificationapp.repository.UserRepository;

@Service
@Transactional
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final UserAchievementRepository userAchievementRepository;

    public AchievementService(AchievementRepository achievementRepository,
                              UserRepository userRepository,
                              UserAchievementRepository userAchievementRepository) {
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
        this.userAchievementRepository = userAchievementRepository;
    }

    public Achievement createAchievement(Achievement achievement) {
        if(achievementRepository.existsByName(achievement.getName())) {
            throw new IllegalArgumentException("Achievement with name " + achievement.getName() + " already exists");
        }
        return achievementRepository.save(achievement);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getAchievementsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        return achievementRepository.findAll(); //feat: implement this method, for the moment it returns all achievements
    }

    public void unlockAchievement(Long userId, Long AchievementId){
        //this query is used to find the user by id
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        //this query is used to find the achievement by id
        Achievement achievement = achievementRepository.findById(AchievementId).orElseThrow(() -> new IllegalArgumentException("Achievement with id " + AchievementId + " not found"));

        //this query is used to check if the user has already unlocked the achievement, for the moment it returns false
        boolean alreadyUnlocked = userAchievementRepository.existsByUserIdAndAchievementId(user, achievement);

        //this query is used to check if the user has already unlocked the achievement
        if(alreadyUnlocked) {
            throw new IllegalArgumentException("Achievement with id " + AchievementId + " already unlocked for user with id " + userId);
        }

        //this query is used to check if the user has reached the required level to unlock the achievement
        if(user.getCurrentLevel() < achievement.getRequiredLevel()) {
            throw new IllegalArgumentException("User with id " + userId + " has not reached level " + achievement.getRequiredLevel());
        }
        //This method creates a new UserAchievement object and sets the user
        UserAchievement userAchievement = new UserAchievement();
        userAchievement.setUser(user);
        userAchievement.setAchievement(achievement);
        userAchievement.setUnlockedAt(LocalDateTime.now());
        userAchievementRepository.save(userAchievement);

        user.setTotalPoints(user.getTotalPoints() + achievement.getPointsValue());
        userRepository.save(user);
    }
}
