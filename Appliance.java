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
    private long serialNumber;
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
    public Appliance(String type, String brand, double price){       // ADDED price catch and REMOVED serialnumber
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
    public void setPrice(double price){     // ADDED catch
        this.price = (price >= 1) ? price : 1;
    }
    //toString method returns all info of the appliance object
    public String toString()
    {
        return "Type: " + type + "\tBrand: " + brand + "\tSerial Number: " + serialNumber + "\tPrice: " + price;
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
    
    
    public static void main(String[] args) {    // STARTED driver
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Welcome to the fantastic appliance tracking software!");
        System.out.println("What is the maximum amount of appliances that your store can contain, or the max amount that you would like to aquire?");
        int maxAppliances = userInput.nextInt();
        Appliance[] inventory = new Appliance[maxAppliances];
        
        final String password = "c249";
        int totalAttempts = 0;    // for the total password attempts (max is 12)
        int triedAttempts = 0; // for the password attemps in a row (max is 3)
        int inventoryCount = 0;

        int code;
        do {
        	 code = menuOptions();
            
            if (code == 1){
                while (triedAttempts < 3){
                    System.out.println("Please enter password to add a new appliance: ");
                    String enteredPassword = userInput.next();
                    if (enteredPassword.equals(password)){
                        System.out.println("How many appliances do you want?");
                        int appliancesToAdd = userInput.nextInt();
                        if (inventoryCount + appliancesToAdd <= maxAppliances){
                        	for (int i = 1; i <= appliancesToAdd; i++) {
                        		System.out.println("Adding appliance " + i);
                                System.out.print("Please enter appliance type: ");
                                String enteredType = userInput.next();
                                System.out.print("Please enter appliance brand: ");
                                String enteredBrand = userInput.next();
                                System.out.print("Please enter appliance price: ");
                                double enteredPrice = userInput.nextDouble();

                                inventory[numOfAppliances] = new Appliance(enteredType, enteredBrand, enteredPrice);
                                System.out.println();
                        	}
                           
                        	inventoryCount += appliancesToAdd;
                        }
                        else{
                            System.out.println("There are only " + (maxAppliances - inventoryCount) + " spaces left");
                        }
                        
                        triedAttempts = 0;
                        totalAttempts = 0;
                        break; // to break out of password check loop
                    }
                    else{
                        System.out.println("Wrong password");
                        triedAttempts++;
                        totalAttempts++; 
                    }
                    
                }
                
            }	// code 1
            
            else if(code == 2)
            {
                


            }
            
            else if(code == 3)
            {
                
            	System.out.print("Please enter a brand name: ");
            	String brand = userInput.next();
            	findAppliancesBy(brand, inventory);

            }
            
            
            if (totalAttempts == 12){
                System.out.println("Program detected suspicious activities and will terminate immediately!");
                System.exit(0);
            }
            //menuOptions();
            
            triedAttempts = 0;
        }
        while (code != 5);
        System.out.println("Bye bye now!");
        userInput.close();
    }   // main
    
    
    public static int menuOptions(){
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What do you want to do?");
        System.out.println("\t1.\tEnter new appliances");
        System.out.println("\t2.\tChange information of an appliance (password required)");
        System.out.println("\t3.\tDisplay all appliances of by a specific brand");
        System.out.println("\t4.\tDisplay all appliances under a certain price");
        System.out.println("\t5.\tQuit");
        System.out.println("Please enter your choice>"); 
        
        if (userInput.hasNextInt()) {
            int inputNum = userInput.nextInt();
            if (inputNum > 0 && inputNum <= 5){
                return inputNum;
            }
            else{
                System.out.println("Please enter an valid code");
                menuOptions();
            }
        }
        else {
          
            System.out.println("Please enter an integer code");
            menuOptions();
             
        }
        userInput.close();
        return 0; //to make the compiler happy, needs that guaranteed return
    }   // menuOptions()
    
    public static void findAppliancesBy(String brand, Appliance[] inventory) {
    	int totalAppliances = 0;
    	System.out.println("Here are all the Appliances of the brand '" + brand + "':");
    	for (Appliance appliance : inventory) {
    		if (appliance != null) {
    			if (appliance.getBrand().equals(brand)) {
        			System.out.println(appliance);
        			totalAppliances++;
        		}
    		}
    	}
    	
    	if (totalAppliances == 0) {
    		System.out.println("No appliances found with brand '" + brand + "'.");
    	}
    	System.out.println();
    }	// findAppliancesBy()
}   // class

