package com.company;

import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {


    public static int playerHp = 100;
    public static int enemyHp;
    public static int enemyType;
    public static int tutorialProgress = 0;
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
        System.out.println("You wake up in a forest surrounded by bushes, completely empty-handed. \nThere is a pointy stick on the floor. \nYou pick it up.");
        addInventory(weaponList[0][0], 0);
        inventoryIntroduction();
    }
    public static void inventoryIntroduction() {
            System.out.println("Now that you have a stick in your inventory, type 'i'.");
            getUserInput();
            if (tutorialProgress == 1){
                try{
                    TimeUnit.SECONDS.sleep(2);
                    combatIntroduction();
                }catch(Exception e){
                    System.out.println("ERROR OCCURRED");
                }
            }
        }
    public static void combatIntroduction() {
        System.out.println("This stick has a use, that use being swinging it at your enemies! \nGet some practice in and use an attack against a tree! \nPress the 'q' key to perform a light attack!");
        getUserInput();
        if (tutorialProgress==2){
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Great!\nNow try going for a heavy attack with the 'e' key!");
                getUserInput();
            }catch(Exception e){
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
        if (tutorialActive){
            tutorialProgress += 1;
        }
        try {
            lightAttack = true;
            System.out.println("You quickly swing your weapon.");
            TimeUnit.MILLISECONDS.sleep(1500);
            System.out.println(getPlayerDamageDealt()+ " damage dealt!");
        } catch (Exception e){
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
        } catch (Exception e){
            System.out.println("ERROR OCCURRED");

        }
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
            case ('e'):
                heavyAttack();
                break;
            default:
                System.out.println("Invalid Input!!");
                break;
        }
    }
    public static void storyPartOne() {
        while (true){
            System.out.println("IT WORKS");
            getUserInput();
            break;
        }
    }
    public static void addInventory(String object, int place) {
        inventory[place] = object;
    }
    public static void displayInventory() {
        if (tutorialActive == true){
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
            int playerDamageDealt = randint.nextInt(3) + 8;
            return playerDamageDealt;
        } else if (!lightAttack) {
            int playerDamageDealt = randint.nextInt(4) + 15;
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
        boolean fightingPhase = true;
        System.out.println("You have run into a " + getEnemyName() + "!");

    }
    public static void main(String[] args) {
        runGame();
    }
}







