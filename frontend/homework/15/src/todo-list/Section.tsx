import React, { CSSProperties, useState } from "react";
import { useListContext } from "./ListContext";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../redux/store";
import {
	addTodo,
	deleteTodo,
	clearCompleted,
	toggleTodo,
} from "../redux/todoSlice";

const listContainer: CSSProperties = {
	paddingLeft: "0",
};
const styles = {
	subMainSection: {
		paddingLeft: "0.5rem",
		margin: "auto",
		width: "73%",
		marginTop: "1rem",
		border: "1px solid gray",
		paddingBottom: "1rem",
		borderRadius: "2px",
	},
	subHeading: {
		fontSize: "32px",
		textTransform: "capitalize" as const,
		"#text": {
			fontSize: "16px",
		},
	},
	inputField: {
		height: "22px",
		width: "175px",
	},
	submitBtn: {
		backgroundColor: "black",
		color: "white",
		outline: "none",
		border: "none",
		width: "65px",
		height: "28px",
		borderRadius: "2px",
		marginLeft: "0.2rem",
	},

	listItems: {
		listStyleType: "none",
		border: "0.5px solid gray",
		borderRadius: "2px",
		height: "50px",
		marginRight: "0.8rem",
		display: "flex",
		flexDirection: "row",
		justifyContent: "space-between",
	},
	deleteButton: {
		marginTop: "0.5rem",
		backgroundColor: "rgb(215, 51, 51)",
		border: "none",
		outline: "none",
		color: "white",
		width: "25px",
		height: "33px",
		borderRadius: "2px",
		marginLeft: "58rem",
	},
};

/**
 * Component representing the section of the application where items can be added and displayed.
 * This component interacts with the list context to add, display, and delete items.
 * @returns {JSX.Element} The JSX element representing the section.
 */
export function Section() {
	const { searchQuery } = useListContext();
	const [inputText, setInputText] = useState("");
	const reduxDispatch = useDispatch();
	const todos = useSelector((state: RootState) => state.todos.todos);

	/**
	 * Handles adding a new item to the list.
	 */
	const handleAddItemClick = () => {
		if (inputText.trim() === "") return;
		reduxDispatch(addTodo(inputText));
		setInputText("");
	};
	/**
	 * Handles input change for adding new items.
	 * @param {React.ChangeEvent<HTMLInputElement>} e - The event object representing the input change.
	 */

	const handleInputItem = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputText(e.target.value);
	};

	return (
		<div style={styles.subMainSection}>
			<h2 style={styles.subHeading}>Add Items</h2>
			<input
				id="unique-input"
				data-testid="unique-input"
				style={styles.inputField}
				type="text"
				onChange={handleInputItem}
				value={inputText}
			/>
			<button
				id="submit-btn"
				data-testid="submit-btn"
				style={styles.submitBtn}
				onClick={handleAddItemClick}
			>
				Submit
			</button>
			<h2 style={styles.subHeading}>Items</h2>
			{todos.length === 0 && searchQuery === "" ? (
				<p id="text">No items added</p>
			) : (
				<>
					{todos.filter((item) =>
						item.text.toLowerCase().includes(searchQuery.toLowerCase())
					).length === 0 ? (
						<p id="text">No match found</p>
					) : (
						<ul id="list" style={listContainer}>
							{todos
								.filter((item) =>
									item.text.toLowerCase().includes(searchQuery.toLowerCase())
								)
								.map((item) => (
									<li
										id="item"
										data-testid="item"
										key={item.id}
										style={{
											textDecoration: item.completed ? "line-through" : "none",
											listStyleType: "none",
											border: "0.5px solid gray",
											borderRadius: "2px",
											height: "50px",
											marginRight: "0.8rem",
											display: "flex",
											flexDirection: "row",
											justifyItems: "center",
											alignItems: "center",
										}}
									>
										<input
											id="checkbox-item"
											data-testid="checkbox-item"
											type="checkbox"
											checked={item.completed}
											onChange={() => reduxDispatch(toggleTodo(item.id))}
										/>
										<span>{item.text}</span>
										<button
											id="delete-btn"
											data-testid="delete-btn"
											style={styles.deleteButton}
											onClick={() => reduxDispatch(deleteTodo(item.id))}
										>
											X
										</button>
									</li>
								))}
						</ul>
					)}
				</>
			)}
			<button
				id="clear-btn"
				data-testid="clear-btn"
				onClick={() => reduxDispatch(clearCompleted())}
			>
				Clear Completed
			</button>
		</div>
	);
}
