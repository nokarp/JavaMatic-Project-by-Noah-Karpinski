import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

/* Important Definitions:
 * 		IngredientStock - Represents an ingredients quantity that is held in the JavaMatic.
 * 							An Object that has a quantity and cost used for making drinks.
 * 		IngredientRequirement - Represents an ingredient amount used in a drink.
 * 							An Object that has an amount contained in each drink. Must have a corresponding IngredientStock.
 * 		Drink - Represents a set of IngredientRequirments later taken from the IngredientStock.
 * 		JavaMatic - A device primarily used to take inputs for drinks and respond with the appropriate output.
 */

public class JavaMatic {
	
	
	
	
	//Boolean JavaMatic is running.
	private boolean running = true;
	
	//Scanner for taking input.
	private Scanner scanner;
	
	//Map of ingredient name and IngredientStock object.
	private Map<String, IngredientStock> ingredients;
	
	//List of drinks.
	private List<Drink> drinks;
	
	//Collection of ingredient name Strings.
	private Collection<String> ingredientNames;
	
	
	public JavaMatic() {
		
		//Set up scanner to read input.
		scanner = new Scanner(System.in);
	
		//Initialize all ingredients in a LinkedHashMap with the ingredient name as the key and a IngredientStock as the value.
		ingredients = new LinkedHashMap<String, IngredientStock>();
		ingredients.put("Coffee",new IngredientStock(0.75f));
		ingredients.put("Decaf Coffee", new IngredientStock(0.75f));
		ingredients.put("Sugar", new IngredientStock(0.25f));
		ingredients.put("Cream", new IngredientStock(0.25f));
		ingredients.put("Steamed Milk", new IngredientStock(0.35f));
		ingredients.put("Foamed Milk", new IngredientStock(0.35f));
		ingredients.put("Espresso", new IngredientStock(1.10f));
		ingredients.put("Cocoa", new IngredientStock(0.90f));
		ingredients.put("Whipped Cream", new IngredientStock(1.00f));
		
		//Initialize all drinks into an ArrayList.
		drinks = new ArrayList<Drink>();
		drinks.add(new Drink("Coffee", new IngredientRequirement[] {
					new IngredientRequirement("Coffee",3),
					new IngredientRequirement("Sugar",1),
					new IngredientRequirement("Cream",1),}, ingredients));
		drinks.add(new Drink("Decaf Coffee", new IngredientRequirement[] {
					new IngredientRequirement("Decaf Coffee",3),
					new IngredientRequirement("Sugar",1),
					new IngredientRequirement("Cream",1),}, ingredients));
		drinks.add(new Drink("Caffe Latte", new IngredientRequirement[] {
					new IngredientRequirement("Espresso",2),
					new IngredientRequirement("Steamed Milk",1),}, ingredients));
		drinks.add(new Drink("Caffe Americano", new IngredientRequirement[] {
					new IngredientRequirement("Espresso",3),}, ingredients));
		drinks.add(new Drink("Caffe Mocha", new IngredientRequirement[] {
					new IngredientRequirement("Espresso",1),
					new IngredientRequirement("Cocoa",1),
					new IngredientRequirement("Steamed Milk",1),
					new IngredientRequirement("Whipped Cream",1),}, ingredients));
		drinks.add(new Drink("Cappuccino", new IngredientRequirement[] {
					new IngredientRequirement("Espresso",2),
					new IngredientRequirement("Steamed Milk",1),
					new IngredientRequirement("Foamed Milk",1),}, ingredients));
		
		//Create a TreeSet of ingredient names for alphabetical ordering purposes.
		ingredientNames = new TreeSet<String>(Collator.getInstance());
		
		
		//Ingredient names automatically sorted as they are added to the TreeSet.
		Iterator<Entry<String, IngredientStock>> itr = ingredients.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, IngredientStock> ing = itr.next();
			ingredientNames.add(ing.getKey());
		}
		
		//Sort drinks list by the names of each drink.
		Collections.sort(drinks, new Comparator<Drink>() {
		      @Override
		      public int compare(final Drink d1, final Drink d2) {
		          return d1.getName().compareTo(d2.getName());
		      }
		  });
		
		//Sets the number on the menu for each drink.
		for(int i = 0; i < drinks.size(); i++) {
			drinks.get(i).setNumber(i+1);
		}
		
	}


	/*
	 * Runs the cycle of receiving input and displaying output. Starting with a display of the output (stock).
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - JavaMatic is not running
	 * 
	 * Post-conditions:
	 * 	 - JavaMatic is done with all inputs and outputs
	 */
	public void run() {
		String input = "";
		displayStock();
		while(running) {
			input = obtainInput();
			HandleOutput(input);
			displayStock();
			
		}
	}
	
	/*
	 * Uses a Scanner to obtain a line of input from the console that is used.
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	A string of the line of input.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - The Scanner is on a line that can be read
	 * 
	 *  Post-conditions:
	 * 	 - Nothing additional has been input or output
	 */
	private String obtainInput() {
		return scanner.nextLine();
	}
	
	
	/*
	 * Determines the appropriate output given the input. If the input is 'R' or 'r', restock the inventory and redisplay the menu.
	 * If the input is 'Q' or 'q', quit the application. If the input is a number [1-x] where x is the total number of drinks, 
	 * order the drink and either dispense it if there is enough stock or output that it is out of stock. If it is anything else,
	 * output that the input is invalid.
	 * 
	 * Input parameters:
	 * 	String of input from the user.
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - A valid string is input
	 * 
	 *  Post-conditions:
	 * 	 - Some new text was output
	 */
	private void HandleOutput(String input) {
		String out = "";
		if(input.equalsIgnoreCase("r")) {
			restock();
		}
		else if(input.equalsIgnoreCase("q")) {
			running = false;
		}
		else if(drinkNumberInRange(input)) {
			int num = Integer.parseInt(input);
			Drink drink = drinks.get(num-1);
			boolean made = drink.makeDrink(ingredients);
			String drinkName = drink.getName();
			if(made) {
				out = "Dispensing:" + drinkName;
			}
			else {
				out = "Out of stock:" + drinkName;
			}
		}
		else {
			out = "Invalid selection:" + input;
		}
		if(!out.equals(""))
			System.out.println(out);		
	}
	
	
	/*
	 * Determines if input string is a number and in the range of the drink numbers. 
	 * Helper function for the HandleOutput function.
	 * 
	 * Input parameters:
	 * 	String of input from the user.
	 * 
	 * Output:
	 * 	A boolean if the input was a number in the range of the drink numbers.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - A valid string is input
	 * 
	 *  Post-conditions:
	 * 	 - The input has not changed
	 */
	private boolean drinkNumberInRange(String input) {
		 int num = 0;
		 try{
			 num = Integer.parseInt(input);
		 }catch(Exception e) {
			 return false;
		 }
		 if(num >= 1 && num <= drinks.size()) {
			 return true;
		 }
		 return false;
	}
	
	
	/*
	 * Calls the display functions for output.
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 *  Post-conditions:
	 * 	 - none
	 */
	private void displayStock() {
		displayInventory();
		displayMenu();
	}
	
	/*
	 * Iterates through every ingredient name and prints out the corresponding IngredientStock toString along with the name of the ingredient.
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 *  Post-conditions:
	 * 	 - Some new text was output
	 */
	private void displayInventory() {
		System.out.println("Inventory:");
		for(String str: ingredientNames) {
			IngredientStock ing = ingredients.get(str);
			System.out.println(str + ing.toString());
		}
	}
	
	/*
	 * Iterates through every drink and prints out the corresponding drink toString.
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 *  Post-conditions:
	 * 	 - Some new text was output
	 */
	private void displayMenu() {
		System.out.println("Menu:");
		for(Drink dri : drinks) {
			System.out.println(dri.toString(ingredients));
		}
	}
	
	/*
	 * Iterates through the ingredients map and for each index, calls the restock function on the IngredientStock object of that index.
	 * 
	 * Input parameters:
	 * 	void
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 *  Post-conditions:
	 * 	 - Each ingredient was restock to the maxQuantity attribute of IngredientStock.
	 */
	private void restock() {
		Iterator<Entry<String, IngredientStock>> itr = ingredients.entrySet().iterator();
		while (itr.hasNext()) {
		    Entry<String, IngredientStock> ing = itr.next();
			ing.getValue().restock();
		}
	}
	


}
