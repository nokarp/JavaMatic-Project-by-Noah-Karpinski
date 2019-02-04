
/* Important Definitions:
 * 		IngredientStock - Represents an ingredients quantity that is held in the JavaMatic.
 * 							An Object that has a quantity and cost used for making drinks.
 * 		IngredientRequirement - Represents an ingredient amount used in a drink.
 * 							An Object that has an amount contained in each drink. Must have a corresponding IngredientStock.
 * 		Drink - Represents a set of IngredientRequirments later taken from the IngredientStock.
 * 		JavaMatic - A device primarily used to take inputs for drinks and respond with the appropriate output.
 */

public class IngredientStock{

	//Cost of one ingredient.
	private float cost;
	
	//Amount of ingredient held.
	private int quantity;
	
	//Maximum quantity of ingredient that can be held.
	private final int maxQuantity = 10;
	
	public IngredientStock(float cost) {
		this.cost = cost;
		quantity = maxQuantity;
	}

	/*
	 * Checks to see if there are enough of the ingredient in stock to make a drink. 
	 * Returns false if the quantity minus the amount required is less than 0.
	 * Returns true otherwise.
	 * 
	 * Input parameters:
	 * 	Integer of number of the ingredient required.
	 * 
	 * Output:
	 * 	Boolean of whether a there is enough of the ingredient.
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 * Post-conditions:
	 * 	 - Quantity did not change
	 */
	public boolean checkStock(int count) {
		if(quantity - count < 0) {
			return false;
		}
		return true;
	}
	
	/*
	 * Removes the input count from the quantity of the ingredient
	 * 
	 * Input parameters:
	 * 	Integer of number of the ingredient required.
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - The checkStock function was called on this ingredient since the last time its quantity changed.
	 * 
	 * Post-conditions:
	 * 	 - Quantity did change
	 */
	public void useIngredient(int count) {
		quantity -= count;
	}
	
	/*
	 * Sets the quantity of the ingredient to the value of the maxQuantity.
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
	 * Post-conditions:
	 * 	 - Quantity did change
	 */
	public void restock() {
		quantity = maxQuantity;
	}
	
	
	public String toString() {
		return "," + Integer.toString(quantity);
	}
	
	public float getCost() {
		return cost;
	}
	

}
