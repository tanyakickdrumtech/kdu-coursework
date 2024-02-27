/**
 * Unit Testing for the Section component.
 */
import { render, fireEvent, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { Section } from "../todo-list/Section";
import store from "../redux/store";

describe("Section Component", () => {
	/**
	 * Tests if there are no items on the page when it is loaded.
	 */
	it("No items on the page when it is loaded", () => {
		render(
			<Provider store={store}>
				<Section />
			</Provider>
		);

		expect(screen.getByText("No items added")).to.exist;
	});

	/**
	 * Tests if the input, add button, and title are rendered.
	 */
	it("renders input, add button, and the title", () => {
		render(
			<Provider store={store}>
				<Section />
			</Provider>
		);

		expect(screen.getByText("Add Items")).to.exist;
		expect(screen.getByTestId("unique-input")).to.exist;
		expect(screen.getByTestId("submit-btn")).to.exist;
	});

	/**
	 * Tests for adding, deleting, and checking checkboxes.
	 */
	it("to check for add, delete, and checkbox functionality", () => {
		render(
			<Provider store={store}>
				<Section />
			</Provider>
		);

		const input = screen.getByTestId("unique-input");
		const btnSubmit = screen.getByTestId("submit-btn");
		fireEvent.change(input, { target: { value: "Tanya" } });
		fireEvent.click(btnSubmit);
		expect(screen.getByTestId("item")).to.exist;

		const checkbox = screen.getByTestId("checkbox-item");
		fireEvent.click(checkbox);
		expect(screen.getByTestId("item").classList.contains("completed")).to.exist;

		const btnDelete = screen.getByTestId("delete-btn");
		fireEvent.click(btnDelete);
		expect(screen.queryByTestId("item")).not.to.exist;

		fireEvent.click(checkbox);
		const btnClear = screen.getByTestId("clear-btn");
		fireEvent.click(btnClear);
		expect(screen.queryByTestId("item")).not.to.exist;
	});
});
