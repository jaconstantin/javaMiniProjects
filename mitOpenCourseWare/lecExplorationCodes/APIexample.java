//here is an example of using various objects from the API


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class APIexample {

	public static void main(String[] args) {
		//first example is a dynamically sized array, implemented internally as array
		//very similar to STL actually in c++
		
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("Evan");
		strings.add("Eugene");
		strings.add("Adam");
		System.out.println(strings.size());
		System.out.println(strings.get(0));
		System.out.println(strings.get(1));
		
		strings.set(0,"Goodbye");
		strings.remove(1);
		
		for(int i=0; i < strings.size(); i++){
			System.out.println(strings.get(i));
		}
		
		//i think this one is a special syntax to iterate on an entire arraylist and other objects
		for (String s : strings){ 
			System.out.println(s); 
		}
		
		//this time let us use an iterator
		System.out.println("#4 iterator");
		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		//this time, try a TreeSet, or an ordered set
		//says that there should only be one copy per each object
		//and that it has no array index
		//also seems that it is auto sorted when used with tree, but the other type has is pseudo random
		
		System.out.println();
		System.out.println();
		
		TreeSet<String> strings2 = new TreeSet<String>(); 
		strings2.add("Evan"); 
		strings2.add("Eugene");
		strings2.add("Adam");
		System.out.println(strings2.size());
		System.out.println(strings2.first());
		System.out.println(strings2.last());
		
		for (String s: strings2){
			System.out.println(s);
		}

	}

}
