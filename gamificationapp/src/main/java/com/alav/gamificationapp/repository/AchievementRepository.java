package com.alav.gamificationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alav.gamificationapp.model.entity.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    //this query is used to find all achievements that have a required level less than or equal to the given level
    List<Achievement> findByRequiredLevelLessThanEqual(Integer level);
    //search for an achievement by name
    boolean existsByName(String name);


}
