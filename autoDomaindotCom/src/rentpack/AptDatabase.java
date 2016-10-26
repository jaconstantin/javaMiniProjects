package rentpack;

import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;


public class AptDatabase {
	
	private String url;
	private String price;
	private String address;
	private String availability;
	private String bond;
	private String internalArea;
	private String bed;
	private String bath;
	private String parking;
	private String heating;
	private String laundry;
	
	public void setData(String url, String price, String address, String availability, String bond, 
			String internalArea, String bed, String bath, String parking, String heating, String laundry){
		
		this.url = url;
		this.price = price;
		this.address = address;
		this.availability = availability;
		this.bond = bond;
		this.internalArea = internalArea;
		this.bed = bed;
		this.bath = bath;
		this.parking = parking;
		this.heating = heating;
		this.laundry = laundry;
	}
	
	public void printData(){
		
		System.out.println("\n\n\n\n");
		System.out.println("link: "+this.url);
		System.out.println("Price per week: "+this.price);
		System.out.println("Address: " +this.address);
		System.out.println("Availability: "+this.availability);
		System.out.println("Bond: "+this.bond);
		System.out.println("Internal Area: "+this.internalArea);
		System.out.println("Bedrooms: "+this.bed);
		System.out.println("Bathroom: "+this.bath);
		System.out.println("Parking Space: "+this.parking);
		System.out.println("Heating: "+this.heating);
		System.out.println("Laundry: "+this.laundry);
		
	}
	
	public void saveData(BufferedWriter writer) throws Exception{
		
		writer.write(this.url+";");
		writer.write(this.price+";");
		writer.write(this.address+";");
		writer.write(this.availability+";");
		writer.write(this.bond+";");
		writer.write(this.internalArea+";");
		writer.write(this.bed+";");
		writer.write(this.bath+";");
		writer.write(this.parking+";");
		writer.write(this.heating+";");
		writer.write(this.laundry+"\n");
		
	}
	
	public static void main() throws Exception{
		
		Path p = Paths.get("C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\RentScrape\\data.csv");
		Charset charset = Charset.forName("US-ASCII");
		
		String tempString = "dog,cat,wolf";
		BufferedWriter writer = Files.newBufferedWriter(p,charset,CREATE,APPEND);
		writer.write("dog,cat,wolf");
		
		//FileWriter writer = new FileWriter(csvFile);
        
  
		
	}

}
