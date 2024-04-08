package Entities_Controllers.Battles;

import Entities_Controllers.Bosses.Boss;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
public class Battles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int smallEnemiesCount;
    private int largeEnemiesCount;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmallEnemiesCount() {
        return smallEnemiesCount;
    }

    public void setSmallEnemiesCount(int smallEnemiesCount) {
        this.smallEnemiesCount = smallEnemiesCount;
    }

    public int getLargeEnemiesCount() {
        return largeEnemiesCount;
    }

    public void setLargeEnemiesCount(int largeEnemiesCount) {
        this.largeEnemiesCount = largeEnemiesCount;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}