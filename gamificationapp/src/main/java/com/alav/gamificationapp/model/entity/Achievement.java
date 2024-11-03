package com.alav.gamificationapp.model.entity;

//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "achievements")
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer pointsValue;

    // URL to the icon representing the achievement
    private String iconUrl;

    // Level required to unlock the achievement
    private Integer requiredLevel;

    // Status of the achievement, default is AVAIABLE
    @Enumerated(EnumType.STRING)
    private AchievementStatus status = AchievementStatus.AVAIABLE;

    // Status of the achievement, default is AVAIABLE but can be HIDDEN or COMPLETED
    enum AchievementStatus {
        AVAIABLE, HIDDEN, COMPLETED
    }
}
