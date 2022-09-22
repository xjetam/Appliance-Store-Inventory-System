// -----------------------------------------------------
// Assignment 1
// Question: part 1
// Written by: Linden Wheeler 40195748
// For COMP 249 Section D - Fall 2022
// Started on: sept 16, 2022
// -----------------------------------------------------

import java.util.Scanner;   // ADDED IMPORT

class Appliance {
    private String type;
    private String brand;
    final private long serialNumber;    // ADDED FINAL
    static long SNCtr = 100000000;
    static  int numOfAppliances = 0;
    private double price;

    //default constructor
    public Appliance(){
        type = "unknown";
        brand = "unknown";
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
        price = 1.0;    // CHANGED TO 1 INSTEAD OF 0
    }
    public Appliance(String type, String brand, double price, long serialNumber){       // ADDED PRICE CATCH
        this.type = type;
        this.brand = brand;
        this.price = (price >= 1) ? price : 1;
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
    }
    //getters and setters
    //type
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    //brand
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    //serialNumber
    public long getSerialNumber(){
        return serialNumber;
    }
    public void setSerialNumber(long serialNumber){
        this.serialNumber = serialNumber;
    }
    //price
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){     // ADDED CATCH
        this.price = (price >= 1) ? price : 1;
    }
    //toString method returns all info of the appliance object
    public String toString()
    {
        return "Type: " + type + " Brand: " + brand + " Serial Number: " + serialNumber + " Price: " + price;
    }
    //returns the number of appliances
    public int findNumberOfCreatedAppliances(){
        return numOfAppliances;
    }
    //checks if two appliance objects have the same type, brand, and price
    public boolean equals(Appliance other)
    {
        return (this.type.equals(other.type) && this.brand.equals(other.brand) && this.price == other.price && this.serialNumber == other.serialNumber);
    }
    
    
    public static void main(String[] args) {    // STARTED DRIVER
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Welcome to the fantastic appliance tracking software!");
        System.out.println("What is the maximum amount of appliances that your store can contain, or the max amount that you would like to aquire?");
        int maxAppliances = userInput.nextInt;
        Appliance[] inventory = new Appliance[maxAppliances];
        
        System.out.println("What do you want to do?");
        System.out.println("\t1.\tEnter new appliances");
        System.out.println("\t2.\tChange information of an appliance (password required)");
        System.out.println("\t3\tDisplay all appliances of by a specific brand");
        System.out.println("\t3\tDisplay all appliances under a certain price");
        System.out.println("\t3\tQuit");
        System.out.println("Please enter your choice>");
        
    }

}
