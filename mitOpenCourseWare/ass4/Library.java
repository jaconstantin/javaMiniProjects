//some comment in this project
//here, see that per class, there is a main routine that tests the class
//also, i did not have to explicitly add book.java since they are in the same project
//also, notice how each instantiation of object happens
	//always with new, meaning create a reference first, say for a book
	//then, equate that to the book array...


public class Library {
	String address;
	Book[] collection;
	int num_books;
	
	public Library(String libAddress){
		address = libAddress;
		collection = new Book [10];
		num_books = 0;
	}
	
	public void addBook(Book newBook){
		collection[num_books] = newBook;
		num_books += 1;
	}
	
	public void printAddress(){
		System.out.println(address);
	}
	
	public static void printOpeningHours(){
		System.out.println("Effing Lib is always open");
	}
	
	
	public void borrowBook(String bookTitle){
		int x;
		for(x=0; x <= num_books-1; ++x){
			if(bookTitle.equals(collection[x].getTitle())) {
				if(collection[x].isBorrowed()) System.out.println("The books is already borrowed");
				else{
					System.out.println("You have succesfully borrowed "+ bookTitle);
					collection[x].borrowed();
				}
				break;
			}
		}
		if(x > num_books-1) System.out.println("That book is not in our Catalogue");
	}
	
	public void printAvailableBooks(){
		int x;
		for(x=0; x <= num_books-1; ++x){
			System.out.println(collection[x].getTitle());
		}
	}
	
	
	// Add the missing implementation to this class
	public static void main(String[] args) {
	// Create two libraries
	Library firstLibrary = new Library("10 Main St.");
	Library secondLibrary = new Library("228 Liberty St.");
	// Add four books to the first library
	firstLibrary.addBook(new Book("The Da Vinci Code"));
	firstLibrary.addBook(new Book("Le Petit Prince"));
	firstLibrary.addBook(new Book("A Tale of Two Cities"));
	firstLibrary.addBook(new Book("The Lord of the Rings"));
	
	// Print opening hours and the addresses
	System.out.println("Library hours:");
	printOpeningHours();
	System.out.println();
	System.out.println("Library addresses:");
	firstLibrary.printAddress();
	secondLibrary.printAddress();
	System.out.println();
	
	// Try to borrow The Lords of the Rings from both libraries
	System.out.println("Borrowing The Lord of the Rings:");
	firstLibrary.borrowBook("The Lord of the Rings");
	firstLibrary.borrowBook("The Lord of the Rings");
	secondLibrary.borrowBook("The Lord of the Rings");
	System.out.println();
	
	// Print the titles of all available books from both libraries
	System.out.println("Books available in the first library:");
	firstLibrary.printAvailableBooks();
	System.out.println();
	System.out.println("Books available in the second library:");
	secondLibrary.printAvailableBooks();
	System.out.println();
	/*
	// Return The Lords of the Rings to the first library
	System.out.println("Returning The Lord of the Rings:");
	firstLibrary.returnBook("The Lord of the Rings");
	System.out.println();
	// Print the titles of available from the first library
	System.out.println("Books available in the first library:");
	firstLibrary.printAvailableBooks();
	
	*/
	
	}
	
}