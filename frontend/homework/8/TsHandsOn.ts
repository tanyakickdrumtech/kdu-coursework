/**
 * Represents a recipe object with details like image, name, rating, etc.
 */
interface Recipe {
	image: string;
	name: string;
	rating: number;
	cuisine: string;
	ingredients: string[];
	difficulty: string;
	prepTimeMinutes: number;
	cookTimeMinutes: number;
	caloriesPerServing: number;
}

/**
 * Represents a class for searching and managing recipes.
 */
class RecipeSearch {
	private recipes: Recipe[] = [];

	/**
	 * Fetches recipes from an external API and populates the recipes array.
	 */
	public async fetchRecipeFromApi(): Promise<void> {
		try {
			const response = await fetch("https://dummyjson.com/recipes");
			const data = await response.json();
			this.recipes = data.recipes as Recipe[];
		} catch (error) {
			console.log("Error fetching the api for recipes", error);
		}
	}

	/**
	 * Searches for recipes based on the provided query.
	 * @param query The search query string.
	 */
	public async searchRecipe(query: string): Promise<void> {
		try {
			const response = await fetch(
				`https://dummyjson.com/recipes/search?q=${query}`
			);
			const data = await response.json();
			console.log(data);
		} catch (error) {
			console.log("Error searching recipe", error);
		}
	}

	/**
	 * Prints details of all recipes stored in the recipes array.
	 */
	public printAllRecipe(): void {
		console.log("All recipes");
		this.recipes.map((recipe) => {
			console.log(`Name: ${recipe.name}`);
			console.log(`Image: ${recipe.image}`);
			console.log(`Rating: ${recipe.rating}`);
			console.log(`Cuisine: ${recipe.cuisine}`);
			console.log(`Ingredients: ${recipe.ingredients.join(", ")}`);
			console.log(`Difficulty: ${recipe.difficulty}`);
			console.log(`Preparation Time: ${recipe.prepTimeMinutes} minutes`);
			console.log(`Cooking Time: ${recipe.cookTimeMinutes} minutes`);
			console.log(
				`Total Time: ${recipe.cookTimeMinutes + recipe.prepTimeMinutes} minutes`
			);
			console.log(`Calorie Count: ${recipe.caloriesPerServing}`);
		});
	}
}

const app = new RecipeSearch();
app.fetchRecipeFromApi().then(() => {
	app.printAllRecipe();
	app.searchRecipe("pizza");
});
