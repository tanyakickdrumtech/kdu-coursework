import React, { Dispatch, SetStateAction, useState } from "react";
import "./Section.css";

interface IListItem {
	id: number;
	text: string;
}

interface IListProp {
	list: IListItem[];
	setList: Dispatch<SetStateAction<IListItem[]>>;
	searchQuery: string;
}

/**
 * Section component for managing items.
 * @param {IListProp} props - The props object.
 * @returns {JSX.Element} Section component.
 */
export function Section({ list, setList, searchQuery }: Readonly<IListProp>) {
	const [inputText, setInputText] = useState("");
	/**
	 * Handles adding a new item to the list.
	 */

	const handleAddItemClick = () => {
		if (inputText.trim() === "") return; // Prevent adding empty items

		const newItem: IListItem = {
			id: list.length + 1,
			text: inputText,
		};

		setList([...list, newItem]);
		setInputText("");
	};

	/**
	 * Handles input change in the input field.
	 * @param {React.ChangeEvent<HTMLInputElement>} e - The input change event.
	 */
	const handleInputItem = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputText(e.target.value);
	};

	/**
	 * Handles deleting an item from the list.
	 * @param {number} id - The id of the item to delete.
	 */
	const handleDeleteItemClick = (id: number) => {
		setList(list.filter((item) => item.id !== id));
	};

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
