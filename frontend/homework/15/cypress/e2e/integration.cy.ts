/**
 * Integration Tests for the application.
 */
describe("Integration Tests", () => {
	/**
	 * Visits the application homepage before each test.
	 */
	beforeEach(() => {
		cy.visit("http://localhost:5173");
	});

	/**
	 * Test to verify the functionality of the Header component.
	 */
	it("tests Header component", () => {
		cy.get("h1").contains("Item Lister").should("exist");
		cy.get("input[type='text']").should("exist");
	});

	/**
	 * Test to verify the functionality of the Section component.
	 */
	it("tests Section component", () => {
		cy.get("h2").contains("Add Items").should("exist");
		cy.get("h2").contains("Items").should("exist");
	});

	/**
	 * Test to verify the functionality of the MainComponent.
	 */
	it("tests MainComponent", () => {
		cy.get("h1").contains("Item Lister").should("exist");
		cy.get("#unique-input").type("New Item");
		cy.get("#submit-btn").should("exist");
		cy.get("#submit-btn").click();
	});

	/**
	 * Test to verify if a new item can be added.
	 */
	it("tests if new item can be added", () => {
		cy.get("#unique-input").type("Tanya");
		cy.get("#submit-btn").click();
		cy.contains("Tanya").should("exist");
		cy.get("input[type='checkbox']").check();
		cy.contains("Clear Completed").click();
		cy.get("input[type='checkbox']").should("not.exist");
	});

	/**
	 * Test to verify if items can be deleted.
	 */
	it("tests if items can be deleted", () => {
		cy.get("#unique-input").type("Tanya");
		cy.get("#submit-btn").click();
		cy.contains("Tanya").should("exist");
		cy.get("#delete-btn").click();
	});

	/**
	 * Test to verify if items can be searched.
	 */
	it("tests if items can be searched", () => {
		cy.get("#unique-input").type("Tanya");
		cy.get("#submit-btn").click();
		cy.contains("Tanya").should("exist");
		cy.get("#search").type("Tanya");
		cy.get("#list").should("contain", "Tanya");
	});
});
