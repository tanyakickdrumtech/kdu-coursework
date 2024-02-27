/**
 * Component Testing for Section.tsx
 */
import { Section } from "../todo-list/Section";
import { Provider } from "react-redux";
import store from "../redux/store";

describe("<Section />", () => {
	/**
	 * Mounts the Section component wrapped in a Provider with a Redux store before each test.
	 */
	beforeEach(() => {
		cy.mount(
			<Provider store={store}>
				<Section />
			</Provider>
		);
	});

	/**
	 * Test to verify if the Section component renders correctly.
	 */
	it("renders", () => {
		cy.get("div").should("have.css", "padding-left", "0px");
	});

	/**
	 * Test to verify if a new item can be added to the list.
	 */
	it("adds a new item to the list", () => {
		cy.get("input[type='text']").type("New Item");

		cy.get("button").contains("Submit").click();

		cy.contains("New Item").should("exist");
	});

	/**
	 * Test to verify if completed items can be cleared from the list.
	 */
	it("clears completed items from the list", () => {
		cy.get("input[type='checkbox']").should("exist");

		cy.get("input[type='checkbox']").check();

		cy.contains("Clear Completed").click();

		cy.get("input[type='checkbox']").should("not.exist");
	});
});
