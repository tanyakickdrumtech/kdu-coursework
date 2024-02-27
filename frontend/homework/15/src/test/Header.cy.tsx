/**
 * Component testing for header
 */
import { Header } from "../todo-list/Header";
import { Provider } from "react-redux";
import store from "../redux/store";

describe("<Header />", () => {
	/**
	 * Mounts the Header component wrapped in a Provider with a Redux store before each test.
	 */
	beforeEach(() => {
		cy.mount(
			<Provider store={store}>
				<Header />
			</Provider>
		);
	});

	/**
	 * Test to verify if the header renders with the correct text.
	 */
	it("renders the header with correct text", () => {
		cy.get("h1").should("have.text", "Item Lister");
	});

	/**
	 * Test to verify if the search input field is rendered.
	 */
	it("renders the search input field", () => {
		cy.get("input[type='text']").should("exist");
	});

	/**
	 * Test to verify that the search query updates on input change.
	 */
	it("updates search query on input change", () => {
		const searchText = "Testing Search";
		cy.get("input[type='text']").type(searchText);
		cy.get("input[type='text']").should("have.value", searchText);
	});
});
