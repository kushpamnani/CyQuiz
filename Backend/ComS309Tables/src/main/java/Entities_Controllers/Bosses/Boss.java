//package Entities_Controllers.Bosses;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class Boss {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//    private int health;
//    private int attack; // Changed from damage
//    private int defense; // Changed from defenseStat
//    private String flashcards;
//
//    // Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getHealth() {
//        return health;
//    }
//
//    public void setHealth(int health) {
//        this.health = health;
//    }
//
//    public int getAttack() { // Changed from getDamage
//        return attack;
//    }
//
//    public void setAttack(int attack) { // Changed from setDamage
//        this.attack = attack;
//    }
//
//    public int getDefense() { // Changed from getDefenseStat
//        return defense;
//    }
//
//    public void setDefense(int defense) { // Changed from setDefenseStat
//        this.defense = defense;
//    }
//
//    public String getFlashcards() {
//        return flashcards;
//    }
//
//    public void setFlashcards(String flashcards) {
//        this.flashcards = flashcards;
//    }
//}
