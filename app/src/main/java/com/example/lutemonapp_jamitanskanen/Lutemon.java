package com.example.lutemonapp_jamitanskanen;

import java.io.Serializable;

public class Lutemon implements Serializable {
    private String name;
    private String color;
    private int attack;
    private int defense;
    private int experience;
    private int health;
    private int maxHealth;
    private int id;
    private static int idCounter = 0;
    private String category;

    public Lutemon() {}


    public Lutemon(String name, String color, int attack, int defense, int maxHealth, String category) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.experience = 0;
        this.id = idCounter++;
        this.category = category;
    }

    public void defense(int attackValue) {
        int damage = attackValue - this.defense;
        if (damage > 0) {
            this.health -= damage;
        }
    }

    public int attack() {
        return this.attack + this.experience * 2;
    }

    public static int getNumberOfCreatedLutemons() {
        return idCounter;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getStats() {
        return getColor() + "(" + getName() + ") att: " + getAttack() + "; def: " + getDefense() + "; exp:" + getExperience() + "; health: " + getHealth() + "/" + getMaxHealth();
    }
    @Override
    public String toString() {
        return name + " (" + color + ")";
    }


}
