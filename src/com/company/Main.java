package com.company;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.time.temporal.*;
public class Main {


    public static int playerHp = 100;
    public static int enemyHp;
    public static int enemyType;
    public static int tutorialProgress = 0;
    public static int stunChance;
    public static int playerDamageDealt;
    public static int baseDamage;
    public static int stunCooldown;
    public static int playerTurn;
    public static int enemyTurn;
    public static LocalDateTime previousStunTime = LocalDateTime.now();
    public static String enemyName;
    public static String enemyVariety;
    public static String weaponEquipped;
    public static String[] inventory = new String[5];
    public static Scanner scan = new Scanner(System.in);
    public static Random randint = new Random();
    public static String[] lowDiffEnemyList = {"wolf", "drunken man", "fox"};
    public static String[] mediumDiffEnemyList = {"gromp", "scuttle crab", "krug"};
    public static String[] hardDiffEnemyList = {"sentinel", "brambleback", "ironback turtle"};
    public static ArrayList<Character> keyLog = new ArrayList<>();
    public static String[][] weaponList = {{"Pointy Stick", "Wooden Sword", "Iron Sword"},
            {"8", "12", "18"}
    };
    public static boolean playerAlive = true;
    public static boolean enemyAlive;
    public static boolean lightAttack;
    public static boolean tutorialActive = true;

    public static void runGame() {
        openingStory();
        if (tutorialActive == false) {
            storyPartOne();
        }
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
        stunCooldown = 10;
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
        }
        if (tutorialProgress == 3) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Now, try going for a stun with the 'e' key!" +
                        "\nYou currently have a 40% chance to successfully stun an enemy." +
                        "\nThere is also a cooldown on your stun attacks, so you can only stun other turn!");
                getUserInput();
            } catch (Exception e) {
                System.out.println("ERROR OCCURRED");
            }
        }
        if (tutorialProgress == 4) {
            choiceTutorial();
        }
    }

    public static void lightAttack() {
        if (tutorialActive) {
            tutorialProgress = tutorialProgress + 1;
        }
        try {
            lightAttack = true;
            System.out.println("You quickly swing your weapon.");
            TimeUnit.MILLISECONDS.sleep(1500);
            System.out.println(getPlayerDamageDealt() + " damage dealt!");
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
        }
        return;
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
            return;
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");

        }
        return;
    }

    public static void stunAttack() {
        if (tutorialActive) {
            tutorialProgress = tutorialProgress + 1;
        }
        if (!tutorialActive){
            checkStunCooldown();
        }
        if (!stunCooldownActive) {
            try {
                System.out.println("You prepare to stun the " + getEnemyName() + ".");
                TimeUnit.SECONDS.sleep(2);
                int stunSuccess = randint.nextInt(100);
                if (stunSuccess < stunChance) {
                    enemyStunned = true;
                    System.out.println("Successful stun!");
                } else if (stunSuccess == stunChance){
                    enemyStunned = true;
                    System.out.println("Successful stun!");
                }else if (stunSuccess > stunChance) {
                    enemyStunned = false;
                    System.out.println("Stun failed!");
                }
            }
                 catch(Exception e){
                    System.out.println("ERROR OCCURRED");
                }
            } else{
                System.out.println("Stun cooldown is active!");
            }
        }

    public static void checkStunCooldown() {
        if (!keyLog.isEmpty()){
            stunCooldownActive = false;
        } else if (keyLog.get(playerTurn - 1) == 'e'){
            stunCooldownActive = true;
        } else {
            stunCooldownActive = false;
        }
    }
    public static void getUserInput() {
        char keyPressed = scan.nextLine().charAt(0);
        keyLog.add(keyPressed);
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
                break;
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
                TimeUnit.SECONDS.sleep(1);
                fight();
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
        if (tutorialActive) {
            enemyHp = 999;
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
        } else if (enemyHp < 1){
            enemyAlive = false;
            baseEnemyDamage = 0;
        } else {
            baseEnemyDamage = 4;
        }
        int damageDealt = multiplier * baseEnemyDamage;
        playerHp = playerHp - damageDealt;
        return damageDealt;
    }


    public static int getPlayerDamageDealt() {
        if (lightAttack) {
            playerDamageDealt = randint.nextInt(3) + baseDamage;
            return playerDamageDealt;
        } else if (!lightAttack) {
            playerDamageDealt = randint.nextInt(4) + (baseDamage * 2);
            return playerDamageDealt;
        } else {
            System.out.println("ERROR");
        }
        getEnemyDamageTaken();
        return 0;
    }

    public static void getEnemyDamageTaken() {
        enemyHp = enemyHp - playerDamageDealt;
    }
    public static String getEnemyName() {
        if (tutorialActive) {
            enemyName = "tree";
        } else {
            if (enemyType == 1) {
                enemyName = lowDiffEnemyList[randint.nextInt(3)];
            } else if (enemyType == 2) {
                enemyName = mediumDiffEnemyList[randint.nextInt(3)];
            } else {
                enemyName = hardDiffEnemyList[randint.nextInt(3)];
            }
            return enemyName;
        } return enemyName;
    }

    public static void fightOrRun() {
        getUserInput();
        if (yesOrNo) {
            System.out.println("You approach towards the " + enemyName + ".");
            fightingPhase = true;
            System.out.println("Use your first attack!");
        } else if (!yesOrNo) {
            System.out.println("You skedaddle the hell out of there.");
            fightingPhase = false;
            return;
        }
    }
    public static void fight() {
        enemyTurn = 0;
        playerTurn = 0;
        setEnemyHp();
        System.out.println("You have run into a " + enemyName + "!\nAre you going to fight it?");
        if (fightAvoidable = true) {
            fightOrRun();
        } else if (fightAvoidable = false) {
            fightingPhase = true;
        }
        keyLog.clear();
        enemyStunned = false;
        while (fightingPhase) {
            if (playerHp > 0) {
                getUserInput();
                System.out.println("The " + enemyName + " has " + enemyHp + " health remaining!");
                playerTurn = playerTurn + 1;
                if (!enemyStunned) {
                    System.out.println("You have taken " + enemyDamage() + " damage!");
                    System.out.println("You currently have " + playerHp + " health left.");
                    enemyTurn = enemyTurn + 1;
                } else if (enemyStunned) {
                    System.out.println("The enemy is currently stunned, go for another attack while you can!");
                    enemyTurn = enemyTurn + 1;
                    enemyStunned = false;
                }
            } else {
                endGame();
                break;
            }
        }
    }
    public static void endGame(){
        System.out.println("死\nYOU ARE DEAD.");
    }
    public static void main(String[] args) {
        runGame();
    }
}







