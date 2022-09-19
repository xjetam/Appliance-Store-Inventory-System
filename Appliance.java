// -----------------------------------------------------
// Assignment 1
// Question: part 1
// Written by: Linden Wheeler 40195748
// For COMP 249 Section D - Fall 2022
// Started on: sept 16, 2022
// -----------------------------------------------------
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
        price = 0.0;
    }
    public Appliance(String type, String brand, double price, long serialNumber){
        this.type = type;
        this.brand = brand;
        this.price = price;
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
        this.price = price;
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
    public static void main(String[] args) {
    
    }

}
