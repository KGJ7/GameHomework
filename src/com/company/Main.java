package com.company;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class Main {


    public static int playerHp = 100;
    public static int enemyHp;
    public static int enemyType;
    public static int tutorialProgress = 0;
    public static int stunChance;
    public static int playerDamageDealt;
    public static int baseDamage;
    public static int stunCooldown;
    public static long previousStunTime;
    public static String enemyName;
    public static String enemyVariety;
    public static String weaponEquipped;
    public static String[] inventory = new String[5];
    public static Scanner scan = new Scanner(System.in);
    public static Random randint = new Random();
    public static String[] lowDiffEnemyList = {"wolf", "drunken man", "fox"};
    public static String[] mediumDiffEnemyList = {"gromp", "scuttle crab", "krug"};
    public static String[] hardDiffEnemyList = {"sentinel", "brambleback", "ironback turtle"};
    public static String[][] weaponList = {{"Pointy Stick", "Wooden Sword", "Iron Sword"},
            {"8", "12", "18"}
    };
    public static boolean playerAlive = true;
    public static boolean lightAttack;
    public static boolean tutorialActive = true;

    public static void runGame() {
        previousStunTime = 0;
        openingStory();
        tutorialActive = false;
        storyPartOne();
//        while (playerAlive) {
//            getUserInput();
        if (playerHp < 1) {
            System.out.println("死\nYOU ARE DEAD.");
        }
    }

    //    }
    public static void openingStory() {
        declareStarterStats();
        System.out.println("You wake up in a forest surrounded by bushes, completely empty-handed. \nThere is a pointy stick on the floor. \nYou pick it up.");
        addInventory(weaponList[0][0], 0);
        enemyName = "wooden log";
        inventoryIntroduction();
    }

    public static void declareStarterStats() {
        stunChance = 40;
        baseDamage = 8;
        stunCooldown = 10000;
    }

    public static void declareCurrentStats() {
    }

    public static void inventoryIntroduction() {
        System.out.println("Now that you have a stick in your inventory, type 'i'.");
        getUserInput();
        if (tutorialProgress == 1) {
            try {
                TimeUnit.SECONDS.sleep(2);
                combatIntroduction();
            } catch (Exception e) {
                System.out.println("ERROR OCCURRED");
            }
        }
    }

    public static void combatIntroduction() {
        System.out.println("This stick has a use, that use being swinging it at your enemies! \nGet some practice in and use an attack against a tree! \nPress the 'q' key to perform a light attack!");
        getUserInput();
        if (tutorialProgress == 2) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Great!\nNow try going for a heavy attack with the 'w' key!");
                getUserInput();
            } catch (Exception e) {
                System.out.println("ERROR OCCURRED");
            }
            if (tutorialProgress ==3){
                tutorialActive = false;
                return;
            }
        }
    }

    public static void currentWeaponEquipped() {

    }

    public static void lightAttack() {
        if (tutorialActive) {
            tutorialProgress += 1;
        }
        try {
            lightAttack = true;
            System.out.println("You quickly swing your weapon.");
            TimeUnit.MILLISECONDS.sleep(1500);
            System.out.println(getPlayerDamageDealt() + " damage dealt!");
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
        }
    }

    public static void heavyAttack() {
        if (tutorialActive){
            tutorialProgress = tutorialProgress + 1;
        }
        try {
            lightAttack = false;
            System.out.println("You charge up an attack.");
            TimeUnit.SECONDS.sleep(4);
            System.out.println(getPlayerDamageDealt() + " damage dealt!");
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");

        }
    }

    public static void stunAttack() {
        if (tutorialActive) {
            tutorialProgress += tutorialProgress;
        }
        checkStunCooldown();
        if (!stunCooldownActive) {
            try {
                System.out.println("You prepare to stun the " + getEnemyName() + ".");
                int stunSuccess = randint.nextInt(100);
                TimeUnit.SECONDS.sleep(2);
                if (stunSuccess < stunChance) {
                    enemyStunned = true;
                    System.out.println("Successful stun!");

                } else if (stunSuccess > stunChance) {
                    enemyStunned = false;
                    System.out.println("Stun failed!");
                }
            } catch (Exception e) {
                System.out.println("ERROR OCCURRED");
            }
        } else {
            System.out.println("Stun cooldown is active!");
        }
    }

    public static void checkStunCooldown() {
        long time = System.currentTimeMillis();
        if (time > previousStunTime + stunCooldown) {
            stunCooldownActive = false;
        } else {
            stunCooldownActive = true;
        }
        previousStunTime = time;
        System.out.println(previousStunTime);
    }
    public static void getUserInput() {
        char keyPressed = scan.nextLine().charAt(0);
        switch (keyPressed) {
            case ('i'):
                displayInventory();
                break;
            case ('q'):
                lightAttack();
                break;
            case ('w'):
                heavyAttack();
                break;
            case ('e'):
                stunAttack();
            default:
                System.out.println("Invalid Input!!");
                break;
        }
    }
    public static void storyPartOne() {
        while (true) {
            try {
                enemyType = 1;
                fightAvoidable = false;
                System.out.println("You look around the surrounding area. A bush rustles...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Suddenly, a " + getEnemyName() + " jumps out at you!");
                break;
            } catch (Exception e) {
                System.out.println("ERROR OCCURRED");
            }
        }
    }

    public static void addInventory(String object, int place) {
        inventory[place] = object;
    }

    public static void displayInventory() {
        if (tutorialActive == true) {
            tutorialProgress += 1;
        }
        for (int i = 0; i < 5; i++) {
            if (inventory[i] == null) {
                System.out.println("(Empty)");
            } else {
                System.out.println(inventory[i]);
            }
        }
    }
    public static int setEnemyHp(){
        int enemyType = randint.nextInt(3);
        if (enemyType == 1) {
            enemyHp = 50;
        } else if (enemyType == 2) {
            enemyHp = 100;
        } else {
            enemyHp = 150;
        }
        return enemyType;
    }

    public static int enemyDamage() {
        int multiplier = randint.nextInt(5) + 1;
        int baseEnemyDamage;
        if (enemyHp < 100 && enemyHp > 75) {
            baseEnemyDamage = 4;
        } else if (enemyHp < 76 && enemyHp > 25) {
            baseEnemyDamage = 3;
        } else {
            baseEnemyDamage = 4;
        }
        int damageDealt = multiplier * baseEnemyDamage;
        return damageDealt;
    }

    public static int playerDamageTaken(int a) {
        playerHp -= a;
        return playerHp;
    }

    public static int getPlayerDamageDealt() {
        if (lightAttack == true) {
            playerDamageDealt = randint.nextInt(3) + baseDamage;
            return playerDamageDealt;
        } else if (!lightAttack) {
            playerDamageDealt = randint.nextInt(4) + (baseDamage * 2);
            return playerDamageDealt;
        } else {
            System.out.println("ERROR");
        }
        return 0;
    }

    public static String getEnemyName() {
        if (enemyType == 1) {
            String enemyName = lowDiffEnemyList[randint.nextInt(3)];
        }
        else if (enemyType == 2){
            String enemyName = mediumDiffEnemyList[randint.nextInt(3)];
        }
        else{
            String enemyName = hardDiffEnemyList[randint.nextInt(3)];
        }
        return enemyName;
    }
    public static void fight() {
        System.out.println("You have run into a " + getEnemyName() + "!\nAre you going to fight it?");
        if (fightAvoidable = true) {
            fightOrRun();
        } else if (fightAvoidable = false) {
            fightingPhase = true;
        }
        while (fightingPhase) {

        }
    }

    public static void main(String[] args) {
        runGame();
    }
}







