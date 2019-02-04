
/* Important Definitions:
 * 		IngredientStock - Represents an ingredients quantity that is held in the JavaMatic.
 * 							An Object that has a quantity and cost used for making drinks.
 * 		IngredientRequirement - Represents an ingredient amount used in a drink.
 * 							An Object that has an amount contained in each drink. Must have a corresponding IngredientStock.
 * 		Drink - Represents a set of IngredientRequirments later taken from the IngredientStock.
 * 		JavaMatic - A device primarily used to take inputs for drinks and respond with the appropriate output.
 */

public class IngredientRequirement{

	//Amount of ingredient used in a drink.
	private int amount;
	
	//Name of ingredient.
	private String name;
	
	public IngredientRequirement(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getName() {
		return name;
	}
}
