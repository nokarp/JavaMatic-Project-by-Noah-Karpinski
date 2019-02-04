
/* Important Definitions:
 * 		IngredientStock - Represents an ingredients quantity that is held in the JavaMatic.
 * 							An Object that has a quantity and cost used for making drinks.
 * 		IngredientRequirement - Represents an ingredient amount used in a drink.
 * 							An Object that has an amount contained in each drink. Must have a corresponding IngredientStock.
 * 		Drink - Represents a set of IngredientRequirments later taken from the IngredientStock.
 * 		JavaMatic - A device primarily used to take inputs for drinks and respond with the appropriate output.
 */

public class Administrator {

	/*
	 * Creates an instance of JavaMatic and calls the run function for it.
	 * 
	 * Input parameters:
	 * 	An array of Strings which is not used
	 * 
	 * Output:
	 * 	void
	 * 
	 * Contract:
	 * 	Pre-conditions:
	 * 	 - none
	 * 
	 *  Post-conditions:
	 * 	 - The program is no longer running
	 */
	public static void main(String[] args) {
		JavaMatic javaMatic = new JavaMatic();
		javaMatic.run();
	}
}

