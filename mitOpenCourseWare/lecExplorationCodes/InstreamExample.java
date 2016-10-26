import java.io.*;

public class InstreamExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//System.out and System.in are used for standard input from the keyboard
		//System.out is very simple to use
		
		int x=10;
		String stExample = "Oh no";
		System.out.println("\n hey ");
		System.out.println(x);
		System.out.println(stExample + "!!!"); //concatenate strings easily
		System.out.println(stExample + x); //print Oh no10 -> seems that implicit cast is working
		
		//now, reading an input might be a little complicated...
		//also note.. you must specify in the main method that the function can throw an IO Exception
		//anyway just try to catch the exception, handle it with catch, and
		//always a good practice to put a finally block for clean up - always executes even if try block exited with exception
		//unfortunately, this is very hard to use, upon enter, each char in the stream will be read by consecutive character reads
		
		/* comment this out
		
		//example 1, reading a character, only one character per read...
		
		InputStreamReader cin = new InputStreamReader(System.in);
		
		try{
			char sampleChar = (char) cin.read(); 
			System.out.println(sampleChar);
			sampleChar = (char) cin.read();
			System.out.println(sampleChar);
		}finally{
			//if (cin != null) cin.close();	
		}
		
		
		//example 2 how about reading an integer?
		//also said that once closed, the standard input stream may never be opened.
		
		try{
			int sampleInt = (int) cin.read(); 
			System.out.println(sampleInt);
		}finally{
			if (cin != null) cin.close();	
		}
		
		*/
		
		
		//next example, let us read line by line using buffered reader that requires a streamreader
		//now we're able to get a string per enter in the input stream
		
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		String stExample2 = br.readLine();
		System.out.println(stExample2);
		stExample2 = br.readLine();
		System.out.println(stExample2);
		
		//now say we want an integer
		//putting a different type will flag an exception!!
		int intExample1 = Integer.parseInt(br.readLine());
		intExample1 += 10;
		System.out.println(intExample1);
		
		//now say we want a float
		float floatExample1 = Float.parseFloat(br.readLine());
		floatExample1 += 10.1;
		System.out.println(floatExample1);
		
		//can even add error checking
		//note here... probably wise to continue with file operations
		//also probably wise to close the standard input...
		
	}

}
