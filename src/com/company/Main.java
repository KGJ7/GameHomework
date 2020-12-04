package com.company;

import java.util.*;




public class Main {


    public static int playerHp = 100;
    public static int enemyHp;
    public static String[] inventory = new String[5];
    public static Scanner scan = new Scanner(System.in);
    public static Random randint = new Random();



    public static void runGame(){
        openingStory();


    }

    public static void openingStory(){
        System.out.println("You wake up in a forest with absolutely nothing. \n There is a stick on the floor. \n Would you like to pick it up?");
        String stickOption = scan.nextLine();
        addInventory("stick", 0);


    }

    public static void printControls(){
        System.out.println("");
    }


    public static void addInventory(String object, int place){
        inventory[place] = object;
    }

    public static void displayInventory(){
        for(int i = 0; i<5; i++){
            if(inventory[i]==null){
                System.out.println("(Empty)");
            }else {
                System.out.println(inventory[i]);
            }
        }
    }

    public static void setEnemyHp(){
        int enemyType = randint.nextInt(3);
        if (enemyType == 1) {
           enemyHp = 50;
        }else if (enemyType == 2) {
            enemyHp = 100;
        } else {
            enemyHp = 150;
        }

    }

    public static int enemyDamage() {

        int multiplier = randint.nextInt(5) +1;
        int baseDamage;
        if(enemyHp<100 && enemyHp>75) {
            baseDamage =4;
        }else if(enemyHp<76 && enemyHp>25){
            baseDamage = 3;
        }else{
            baseDamage = 4;
        }

       int damageDealt = multiplier*baseDamage;

        return damageDealt;
    }

    public static int playerDamageTaken(int a) {
        playerHp -= a;
        return playerHp;
    }

    public static void fight(){


    }





    public static void main(String[] args) {
        setEnemyHp();
        for(int i = 0; i<10;i++){
            System.out.println(playerHp);
            int x = enemyDamage();
            System.out.println(x);
            playerDamageTaken(x);
            System.out.println(playerHp);
            System.out.println();
        }





    }
}
