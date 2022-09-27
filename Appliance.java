// -----------------------------------------------------
// Assignment 1
// Question: part 1
// Written by: Linden Wheeler 40195748
// For COMP 249 Section D - Fall 2022
// Started on: sept 16, 2022
// ----------------------------------------------------- 

import java.util.Scanner; 

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
        price = 1.0;    // minimum price is $1
    }
    public Appliance(String type, String brand, double price){     
        this.type = type;
        this.brand = brand;
        this.price = (price >= 1) ? price : 1; // if price is less than $1, price is equal to $1
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
    public void setPrice(double price){ 
        this.price = (price >= 1) ? price : 1;	//checking if price is greater than one
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
    
    
    public static void main(String[] args) { 
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the fantastic appliance tracking software!");
        System.out.println("What is the maximum amount of appliances that your store can contain, or the max amount that you would like to aquire?");
        int maxAppliances = integerCheck(userInput);	// go check out integerCheck()
        Appliance[] inventory = new Appliance[maxAppliances];
        
        final String password = "c249";
        int totalAttempts = 0;    // for the total password attempts (max is 12)
        int triedAttempts = 0; // for the password attempts in a row (max is 3)
        int inventoryCount = 0;	// keeping track of how many appliances have actually been added

        int code;
        do {

        	 code = menuOptions(userInput);

            
            if (code == 1){
                while (triedAttempts < 3){	// loops three times to give the user three chances to enter correct password
                    System.out.println("Please enter password to add a new appliance: ");
                    String enteredPassword = userInput.next();
                    if (enteredPassword.equals(password)){
                        System.out.println("How many appliances do you want?");

                        int appliancesToAdd = integerCheck(userInput);	// makes sure that user passes a valid integer value
                        if (inventoryCount + appliancesToAdd <= maxAppliances){	// making sure that the user doesn't add more appliances than limit
                        	for (int i = 1; i <= appliancesToAdd; i++) {	// loops and adds the requested number of appliances
                        		System.out.println();

                        		System.out.println("Adding appliance " + i);
                                System.out.print("Please enter appliance type: ");
                                String enteredType = userInput.next();
                                System.out.print("Please enter appliance brand: ");
                                String enteredBrand = userInput.next();
                                System.out.print("Please enter appliance price: ");
                                double enteredPrice = doubleCheck(userInput);	// makes sure that user passes a valid double value

                                inventory[numOfAppliances] = new Appliance(enteredType, enteredBrand, enteredPrice);
                                // creates appliance object according to given info by user and places in the inventory

                                System.out.println();

                        	}
                           
                        	inventoryCount += appliancesToAdd;	// adds number of appliances to inventory count
                        }
                        else{
                            System.out.println("There are only " + (maxAppliances - inventoryCount) + " spaces left");
                        }
                        
                        triedAttempts = 0;	// password attempts are reset if the user correctly enters the pass-code
                        totalAttempts = 0;
                        break; // to break out of password check loop
                    } 
                    else{
                        System.out.println("Wrong password");
                        triedAttempts++;	// increment both attempts
                        totalAttempts++; 
                    } 
                }	// PASSWORD LOOP
                
                triedAttempts = 0;	// reset the tried attempts but leave the total attempts since after 12 wrong attempts total the system is suspended
                if (totalAttempts == 12){

                    System.out.println("\nProgram detected suspicious activities and will terminate immediately!");

                    System.exit(0);	// suspends program
                }
                System.out.println();
            }	// CODE 1

           
            /*
            else if(code == 2)
            {

            	while(triedAttempts < 3)

                {
                    System.out.println("Please enter password to edit an appliance: ");
                    String enteredPassword = userInput.next();
                    if (enteredPassword.equals(password)){
                        System.out.println("Please enter the serial number of the appliance you would like to edit.");
                        if(userInput.hasNextLong())
                        {
                            long enteredSN = userInput.nextLong();
                            System.out.println("Appliance Serial #: " + findAppliancesBySerialNumber(enteredSN, inventory).getSerialNumber() + "\n"
                            + "Brand: " + findAppliancesBySerialNumber(enteredSN, inventory).getBrand() + "\n" 
                            + "Type: " + findAppliancesBySerialNumber(enteredSN, inventory).getType() + "\n"
                            + "Price: " + findAppliancesBySerialNumber(enteredSN, inventory).getPrice());
                        }
                        triedAttempts = 0;
                    }
                    triedAttempts++;
                }
                menuOptions(userInput);
                

            }
            */
            
            else if(code == 3)
            {

            	System.out.print("Please enter a brand name: ");
            	String brand = userInput.next();
            	findAppliancesBy(brand, inventory);	// all the work is in the static method

            }
            
            else if(code == 4)
            {

            	System.out.print("Please enter a price: ");
            	int price = integerCheck(userInput); // go check out integerCheck()

            	findCheaperThan(price, inventory);	// all the work is in the static method

            }
            
            
        }
        while (code != 5);
        System.out.println("Thanks for using the Appliance manager software!");
        userInput.close();
    }   // MAIN
    
    

    public static int menuOptions(Scanner input){
        while (true) {	// the function loops until the user enters a valid code, redisplaying the menu each time
	        System.out.println("What do you want to do?");
	        System.out.println("\t1.\tEnter new appliances");
	        System.out.println("\t2.\tChange information of an appliance (password required)");
	        System.out.println("\t3.\tDisplay all appliances of by a specific brand");
	        System.out.println("\t4.\tDisplay all appliances under a certain price");
	        System.out.println("\t5.\tQuit");
	        System.out.println("Please enter your choice>"); 
	        
	        int inputNum = integerCheck(input);	// to prevent errors if the user enters a non integer value
	        if (inputNum > 0 && inputNum <= 5){
	            return inputNum;
	        }
	        else{
	            System.out.println("Please enter an valid code\n");
	        }
        }
    }   // MENU OPTIONS
    
    public static void findAppliancesBy(String brand, Appliance[] inventory) {
    	int totalAppliances = 0;	// checks if any appliances match passed brand
    	System.out.println("Here are all the Appliances of the brand '" + brand + "':");
    	for (Appliance appliance : inventory) {	// loop through inventory array and if the brand passed in matches the current brand, that appliance object's info is printed
    		if (appliance != null) {	// checks that an appliance object actually exists at current index
    			if (appliance.getBrand().equals(brand)) {
        			System.out.println(appliance);	// uses toString() method
        			totalAppliances++;
        		}
    		}
    	}
    	
    	if (totalAppliances == 0) {
    		System.out.println("No appliances found with brand '" + brand + "'.");
    	}
    	System.out.println();
    }	// FIND APPLIANCES BY
    

    /*

    
    public static void findCheaperThan(int price, Appliance[] inventory) {
    	int totalAppliances = 0;	// checks if any appliances are cheaper than passed price
    	for (Appliance appliance : inventory) {	// loops through inventory and compares each appliance's price to passed price
    		if(appliance != null) {	// checks that an appliance object actually exists at current index
	    		if (appliance.getPrice() < price) {	// if appliance price is lower than passed price, the appliance's info is printed using toString() method
	    			System.out.println(appliance);
	    			totalAppliances++;
	    		}
    		}
    	}
    	if(totalAppliances == 0) {
    		System.out.println("No appliances found cheaper than $" + price + ".");
    	}
    	System.out.println();
    }	// FIND CHEAPER THAN


    public static Appliance findAppliancesBySerialNumber(long enteredNum, Appliance[] inventory)
    {
        for(int n = 0; n < inventory.length; n++)
        {
            if(inventory[n].getSerialNumber() == enteredNum)
            {
                return inventory[n];
            }
            else if(inventory[n] == null)
            {
                System.out.println("serial number does not exist");
            }
        }
        //menuOptions();
        return inventory[0]; //makes the compiler happy :)
    }

    */
    
    public static void findCheaperThan(int price, Appliance[] inventory) {
    	int totalAppliances = 0;	// checks if any appliances are cheaper than passed price
    	System.out.println("Here are all the appliances cheaper than $" + price + ".");
    	for (Appliance appliance : inventory) {	// loops through inventory and compares each appliance's price to passed price
    		if(appliance != null) {	// checks that an appliance object actually exists at current index
	    		if (appliance.getPrice() < price) {	// if appliance price is lower than passed price, the appliance's info is printed using toString() method
	    			System.out.println(appliance);
	    			totalAppliances++;
	    		}
    		}
    	}
    	if(totalAppliances == 0) {
    		System.out.println("No appliances found cheaper than $" + price + ".");
    	}
    	System.out.println();
    }	// FIND CHEAPER THAN
    
    public static int integerCheck(Scanner input) {
    	while (!(input.hasNextInt())) {	// until user enters an integer, the line is discarded into a trash string, and the process is repeated until an integer value is entered
    		String garbage = input.nextLine();
    		System.out.print("Please enter an integer value: ");
    	}
    	int intOut = input.nextInt();
    	return intOut;	// returns the valid integer value to user
    }
    
    public static double doubleCheck(Scanner input) {	
    	while (!(input.hasNextDouble())) {	// until user enters a double, the line is discarded into a trash string, and the process is repeated until a double value is entered
    		String garbage = input.nextLine();
    		System.out.print("Please enter an double value: ");
    	}
    	double doubleOut = input.nextDouble();
    	return doubleOut;	// returns the valid double value to user
    }
    
}   // CLASS


