package rentpack;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileTrial {

	public static void main(String[] args) throws Exception {
		//save the file path here and char set 
		Path p = Paths.get("C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\RentScrape\\data.csv");
		Charset charset = Charset.forName("US-ASCII");
		
		
		String tempString = "dog,cat,wolf";
		
		//opens a file stream for writing, use the enum standard open options...
		//then just write a string...
		BufferedWriter writer = Files.newBufferedWriter(p,charset,CREATE,APPEND);
		writer.write(tempString,0,tempString.length());
		writer.close();		//note that for a buffered writing, the writing will only happen upon flush..

	}

}
