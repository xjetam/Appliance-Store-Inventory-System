
import java.util.Scanner;


/**
 * Assignment 1
 * For COMP  249 Section D - Fall 2022
 * 
 * @author Linden Wheeler 40195748 and Matej Pederson 40209550
 * @version 1.19
 */
class Appliance {
    private String type;
    private String brand;
    private long serialNumber;
    static long SNCtr = 1000000;
    static  int numOfAppliances = 0;
    private double price;

    /**
     * Default constructor.
     */
    public Appliance(){
        type = "unknown";
        brand = "unknown";
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
        price = 1.0;    // minimum price is $1
    }

    /**
     * Constructor that takes parameters.
     * 
     * @param type type of appliance
     * @param brand brand of appliance
     * @param price price of appliance
     */
    public Appliance(String type, String brand, double price){     
        this.type = type;
        this.brand = brand;
        this.price = (price >= 1) ? price : 1; // if price is less than $1, price is equal to $1
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
    } 

    /**
     * Returns type of appliance.
     * 
     * @return string type of appliance
     */
    public String getType(){
        return type;
    }

    /**
     * Sets type of appliance.
     * 
     * @param type of appliance
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Returns brand of appliance.
     * 
     * @return string brand of appliance
     */
    public String getBrand(){
        return brand;
    }

    /**
     * Sets brand of appliance.
     * 
     * @param brand of appliance
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Returns serial sumber of appliance.
     * 
     * @return long serial sumber of appliance
     */
    public long getSerialNumber(){
        return serialNumber;
    }

    /**
     * Sets serial sumber of appliance.
     * 
     * @param serialNumber serial number of appliance
     */
    public void setSerialNumber(long serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * Returns price of appliance.
     * 
     * @return double price of appliance
     */
    public double getPrice(){
        return price;
    }

    /**
     * Sets price of appliance.
     * 
     * @param price of appliance
     */
    public void setPrice(double price){ 
        this.price = (price >= 1) ? price : 1;	//checking if price is greater than one
    }

    /**
     * Allows object info to be printed out
     * 
     * @return string of information about appliance
     */
    public String toString()
    {
        return "Type: " + type + "\nBrand: " + brand + "\nSerial Number: " + serialNumber + "\nPrice: " + price;
    }

    /**
     * Returns number of appliances created.
     * 
     * @return int number of appliances
     */
    public int findNumberOfCreatedAppliances(){
        return numOfAppliances;
    }

    /**
     * Checks if two appliance objects have the same type, brand, and price.
     * 
     * @param other appliance to be compared
     * @return boolean true if appliances are equal, false if appliances have different attributes
     */
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
        do 
        {
            code = menuOptions(userInput);

            if (code == 1)
            {
                while (triedAttempts < 3){	// loops three times to give the user three chances to enter correct password
                    System.out.println("Please enter password to add a new appliance: ");
                    String enteredPassword = userInput.next();
                    if (enteredPassword.equals(password)){
                        System.out.println("How many appliances do you want?");

                        int appliancesToAdd = integerCheck(userInput);	// makes sure that user passes a valid integer value
                        if (inventoryCount + appliancesToAdd <= maxAppliances)
                        {	// making sure that the user doesn't add more appliances than limit
                            for (int i = 1; i <= appliancesToAdd; i++)
                            {	// loops and adds the requested number of appliances
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
                        else
                        {
                            System.out.println("There are only " + (maxAppliances - inventoryCount) + " spaces left");
                        }
                        
                        triedAttempts = 0;	// password attempts are reset if the user correctly enters the pass-code
                        totalAttempts = 0;
                        break; // to break out of password check loop
                    } 
                    else
                    {
                        System.out.println("Wrong password");
                        triedAttempts++;	// increment both attempts
                        totalAttempts++; 
                    } 
                }	// PASSWORD LOOP
                
                triedAttempts = 0;	// reset the tried attempts but leave the total attempts since after 12 wrong attempts total the system is suspended
                if (totalAttempts == 12)
                {

                    System.out.println("\nProgram detected suspicious activities and will terminate immediately!");

                    System.exit(0);	// suspends program
                }
                System.out.println();
            }	// CODE 1

            
            else if(code == 2)
            {
                long enteredSN;
                while(triedAttempts < 3)
                {
                    System.out.println("Please enter password to edit an appliance: ");
                    String enteredPassword = userInput.next();
                    if (enteredPassword.equals(password)){
                        boolean updatingAppliance = true;
                        while (updatingAppliance)
                        {
                            
                            
                            System.out.println("Please enter the serial number of the appliance you would like to edit.");
                            enteredSN = longCheck(userInput);
                            if (findAppliancesBySerialNumber(enteredSN, inventory) != null)
                            { 
                                System.out.println("Appliance Serial # " + findAppliancesBySerialNumber(enteredSN, inventory).getSerialNumber()
                                + "\nBrand: " + findAppliancesBySerialNumber(enteredSN, inventory).getBrand()
                                + "\nType: " + findAppliancesBySerialNumber(enteredSN, inventory).getType()
                                + "\nPrice: " + findAppliancesBySerialNumber(enteredSN, inventory).getPrice());
                                
                                    int editCode = editMenuOptions(userInput);
                                    if(editCode == 4)
                                    {
                                        break;
                                    }
                                    findAppliancesBySerialNumber(enteredSN, inventory).editAppliance(editCode, userInput);
                            }
                            else
                            {
                                System.out.println("Serial number does not match any appliance.");
                                System.out.println("If you would like to try another serial number, enter y below. " +
                                "Otherwise you will be taken back to the main menu");
                                String response = userInput.next();
                                if (!(response.equals("y")))
                                {
                                    break;
                                }
                            }
                            
                            
                        }
                        triedAttempts = 0;
                        break;
                    }
                    else
                    {
                        System.out.println("Wrong password.");
                        triedAttempts++;
                    }
                    
                }

                triedAttempts = 0;
            }
            
            
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
    



    public static Appliance findAppliancesBySerialNumber(long enteredNum, Appliance[] inventory)
    {
        for(Appliance appliance : inventory)
        {
            if (appliance != null){
                if(appliance.getSerialNumber() == enteredNum)
                {
                    return appliance;
                }
            }
        }
        return null; //makes the compiler happy :)
    }
    
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
    public static long longCheck(Scanner input)
    {
        while(!(input.hasNextLong())) // until user enters a long, the line is discarded into a trash string, and the process repeats until a long is entered
        {
            String garbage = input.nextLine();
            System.out.println("Please enter a long value: ");
        }
        long longOut = input.nextLong();
        return longOut; //returns the valid long value
    }
    public static Boolean typeCheck(String enteredType)
    {
        String[] types = { "Fridge", "Air Conditioner", "Washer", "Dryer",
            "Freezer", "Stove", "Dishwasher", "Water Heaters", "Microwave"};
            for(int n = 0; n<8; n++)
            {
                if(types[n].equals(enteredType))
                {
                    return true;
                }
            }
            System.out.println("Please enter a valid type.");
            return false; 
    }
    public static int editMenuOptions(Scanner input)
    {
        while (true) 
        {	// the function loops until the user enters a valid code, redisplaying the menu each time
            System.out.println("What information would you like to change?");
            System.out.println("\t1.\tBrand");
            System.out.println("\t2.\tType");
            System.out.println("\t3.\tPrice");
            System.out.println("\t4.\tQuit");
            System.out.println("Please enter your choice>"); 
            
            int inputNum = integerCheck(input);	// to prevent errors if the user enters a non integer value
            if (inputNum > 0 && inputNum <= 4)
            {
                return inputNum;
            }
            else
            {
                System.out.println("Please enter an valid code\n");
            }
        }
        
    }

    
    public void editAppliance(int code, Scanner input)
    {
                switch(code)
                {
                    case 1:

                        System.out.println("Please enter the new brand: ");
                        String newBrand = input.next();
                        this.setBrand(newBrand);
                        System.out.println("Updated info for this applaince:");
                        System.out.println(this);
                        break;

                    case 2:
                        System.out.println("Please enter the new type: ");
                        if(input.hasNext())
                        {
                            String newType = input.next();
                            typeCheck(newType);
                            if(typeCheck(newType) == true)
                            {
                                this.setType(newType);
                                System.out.println("Updated info for this applaince:");
                                System.out.println(this);
                            }
                            
                        }
                        else
                        {
                            System.out.println("Please enter a valid string.");
                        }
                        break;

                    case 3:
                        System.out.println("Please enter the new brand: ");
                        double newPrice = input.nextDouble();
                        this.setPrice(newPrice);
                        System.out.println(this);
                    break;

                    case 4:
                        break;       
                }

    }
    

}   // CLASS


