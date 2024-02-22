import React from "react";
import { useListContext } from "./ListContext";
import "./Section.scss";

/**
 * Component representing the section of the application where items can be added and displayed.
 * This component interacts with the list context to add, display, and delete items.
 */
export function Section() {
	const { list, setList, searchQuery } = useListContext();
	const [inputText, setInputText] = React.useState("");

	/**
	 * Handles the addition of a new item to the list.
	 * If the input text is empty or contains only whitespace, the function returns early.
	 * Otherwise, it creates a new item object with an id and text, adds it to the list, and clears the input field.
	 */
	const handleAddItemClick = () => {
		if (inputText.trim() === "") return;

		const newItem = {
			id: list.length + 1,
			text: inputText,
		};

		setList([...list, newItem]);
		setInputText("");
	};

	/**
	 * Handles changes to the input field for adding new items.
	 * Updates the state to reflect the current input text.
	 * @param {React.ChangeEvent<HTMLInputElement>} e - The event object containing the input field's new value.
	 */
	const handleInputItem = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputText(e.target.value);
	};

	/**
	 * Handles the deletion of an item from the list.
	 * Filters out the item with the provided id and updates the list state accordingly.
	 * @param {number} id - The id of the item to be deleted.
	 */
	const handleDeleteItemClick = (id: number) => {
		setList(list.filter((item) => item.id !== id));
	};

	// Filters the list based on the search query entered by the user
	const filteredList = list.filter((item) =>
		item.text.toLowerCase().includes(searchQuery.toLowerCase())
	);

	return (
		<div id="sub-main-section">
			<h2 id="sub-heading">Add Items</h2>
			<input
				id="input-field"
				type="text"
				onChange={handleInputItem}
				value={inputText}
			/>
			<button id="submit-btn" onClick={handleAddItemClick}>
				Submit
			</button>
			<h2 id="sub-heading">Items</h2>
			{list.length === 0 && searchQuery === "" ? (
				<p id="text">No items added</p>
			) : (
				<>
					{filteredList.length === 0 ? (
						<p id="text">No match found</p>
					) : (
						<ul id="list-container">
							{filteredList.map((item) => (
								<li id="list-items" key={item.id}>
									{item.text}
									<button
										id="delete-btn"
										onClick={() => handleDeleteItemClick(item.id)}
									>
										X
									</button>
								</li>
							))}
						</ul>
					)}
				</>
			)}
		</div>
	);
}
