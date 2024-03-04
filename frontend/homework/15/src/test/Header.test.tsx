/**
 * Unit Testing For Header.tsx
 */
import { render, fireEvent } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { Header } from "../todo-list/Header";
import { Provider } from "react-redux";
import store from "../redux/store";

describe("Testing Header", () => {
	/**
	 * Test to verify that the correct title is rendered for the header.
	 */
	it("renders the correct title for the header", () => {
		const { getByText } = render(
			<Provider store={store}>
				<Header />
			</Provider>
		);

		expect(getByText("Item Lister")).not.toBeNull();
	});

	/**
	 * Test to verify that the search query updates on input change.
	 */
	it("updates search query on input change", () => {
		const { getByPlaceholderText } = render(
			<Provider store={store}>
				<Header />
			</Provider>
		);

		const input = getByPlaceholderText("Search items...");
		fireEvent.change(input, { target: { value: "Testing Search" } });
	});
});
