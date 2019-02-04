import java.util.Map;

/* Important Definitions:
 * 		IngredientStock - Represents an ingredients quantity that is held in the JavaMatic.
 * 							An Object that has a quantity and cost used for making drinks.
 * 		IngredientRequirement - Represents an ingredient amount used in a drink.
 * 							An Object that has an amount contained in each drink. Must have a corresponding IngredientStock.
 * 		Drink - Represents a set of IngredientRequirments later taken from the IngredientStock.
 * 		JavaMatic - A device primarily used to take inputs for drinks and respond with the appropriate output.
 */

public class Drink {
	
	//Number used to order drink.
	private int number;
	
	//Name of drink.
	private String name;
	
	//Cost of one of the drink.
	private float cost;
	
	//List of IngredientRequirement objects to make the drink.
	private IngredientRequirement[] requiredIngredients;
	
	public Drink(String name, IngredientRequirement[] req, Map<String, IngredientStock> ingredients) {
		this.name = name;
		requiredIngredients = req;
		cost = calculateCost(ingredients);
		number = 0;
	}
	
	/*
	 * Calculates the cost to make a drink by taking the quantity of each ingredient, multiplying it by its respective cost,
	 * and adding all of the costs together. Only called during initialization
	 * 
	 * Input parameters:
	 * 	Map of the ingredient names and IngredientStock objects.
	 * 
	 * Output:
	 * 	float of the total cost of the drink.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - IngredientRequirements names appear exactly once in the ingredients map keys.
	 * 
	 * Post-conditions:
	 * 	 - No names, costs, or amounts were changed.
	 */
	private float calculateCost(Map<String, IngredientStock> ingredients) {
		float tempCost = 0;
		for(IngredientRequirement i: requiredIngredients) {
			tempCost += ingredients.get(i.getName()).getCost() * i.getAmount();
		}
		return tempCost;
	}

	
	/*
	 * Iterates through the required ingredients. Takes the name of each IngredientRequirement in the list (requiredIngredients), and gets the corresponding 
	 * IngredientStock to call the checkStock function. Return true if checkStock, for every IngredientRequirement in the list,
	 * checkStock called on IngredientStock returns true. Return false otherwise.
	 * 
	 * Input parameters:
	 * 	Map of the ingredient names and IngredientStock objects.
	 * 
	 * Output:
	 * 	Boolean of whether a drink has the ingredients to be made.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 -  IngredientRequirements names appear exactly once in the ingredients map keys.
	 * 
	 * Post-conditions:
	 * 	 - No names, costs, or amounts were changed.
	 */
	private boolean inStock(Map<String, IngredientStock> ingredients) {
		for(IngredientRequirement i: requiredIngredients) {
			if(!ingredients.get(i.getName()).checkStock(i.getAmount())) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * Calls instock. If instock returns true, removes the appropriate ingredients from the ingredients 
	 * IngredientStock value and returns true. Otherwise jsut returns false;
	 * 
	 * Input parameters:
	 * 	Map of the ingredient names and IngredientStock objects.
	 * 
	 * Output:
	 * 	Boolean of whether a drink has the ingredients to be made.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 -  IngredientRequirements names appear exactly once in the ingredients map keys.
	 * 
	 * Post-conditions:
	 * 	 - No names, costs, or amounts were changed.
	 */
	public boolean makeDrink(Map<String, IngredientStock> ingredients) {
		if(inStock(ingredients)) {
			for(IngredientRequirement i: requiredIngredients) {
				ingredients.get(i.getName()).useIngredient(i.getAmount());
			}
			return true;
		}
		return false;
	}
	
	
	public String toString(Map<String, IngredientStock> ingredients) {
		return Integer.toString(number) + "," + name + "," +
				"$" + String.format("%.2f", cost) + "," + Boolean.toString(inStock(ingredients));
	}
	
	public String getName() {
		return name;
	}
	
	public void setNumber(int num) {
		number = num;
	}
	
}
