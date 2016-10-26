//some comments here
//the main public class is the same as the file name
//a public class can have a method named main, meaning that it is executable
//in that main function, we can instantiate Classes as usual

public class Baby {
	String name;
	boolean isMale;
	static int numBabies = 0;
	
	//constructor for the class Baby
	Baby(String myName, boolean gender){
		name = myName;
		isMale = gender;
		numBabies += 1;
	}
	
	void sayHi(){
		System.out.println("Hi, my name is " + name);
	}
	
	public static void main(String[] args){
		//one main difference in java, is that all classes/arrays are always dynamically allocated to the heap with new
		//no need to free memory, as any free memory is entitled to garbage collection
		//for arrays, it can be initialized with constant values in {} instead of new, but i believe this is still dynamic allocation
		//though arrays are always fixed in length
		
		
		int[] num_array = new int[10];
		int[] num_array2 = {0,1,2}; 
		
		//note here an alternative to declaring arrays
		int[] num_array3;
		num_array3 = new int [10];
		System.out.println(num_array3.length);

		System.out.println(num_array.length);
		System.out.println(num_array2.length);
		
		
		Baby Karl = new Baby("Aleizo Karl Santos", true);
		Baby Lorelai = new Baby("Anne Lorelai Constantino", false);
		
		Karl.sayHi();
		Lorelai.sayHi();
	
		//class members are accessed as usual....
		System.out.println(Karl.name + " and " + Lorelai.name);
		
		//values and references, only the reference to the objects are stored in the stack
		//example this prints 
		System.out.println(num_array);
		System.out.println(Karl);
		
		//whenever that object name is used, it always refer to the reference, and contents maybe accessed only with . and []
		Karl.name = "Kevin Karl Santos";
		Karl.sayHi();
		
		//note here, we are assigning the reference or the address
		//hence, when we modify Karl.name, we also modify Lorelai.name
		Karl = Lorelai;
		Karl.sayHi();
		
		Karl.name = "Lance Constantino";
		Karl.sayHi();
		Lorelai.sayHi();
		
		//static members, same as c++;
		//see that it can be accessed through the Baby class, or just numBabies, since this is main method of Baby
		//can also be accessed through a baby object, but there is warning
		System.out.println(numBabies);
		System.out.println(Karl.numBabies);
		System.out.println(Baby.numBabies);
		
	
		
	}
	
}
