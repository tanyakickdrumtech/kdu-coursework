import React from "react";
import { useListContext } from "./ListContext";

interface Styles {
	header: React.CSSProperties;
	heading: React.CSSProperties;
	searchBar: React.CSSProperties;
}
/**
 * jss styling
 */

const styles: Styles = {
	header: {
		display: "flex",
		flexDirection: "row",
		alignItems: "center",
		justifyContent: "space-around",
		backgroundColor: "green",
		color: "white",
		width: "100%",
	},
	heading: {
		fontSize: "40px",
	},
	searchBar: {
		width: "500px",
		height: "20px",
		fontSize: "16px",
	},
};

/**
 * Component representing the header of the item lister.
 * @returns {JSX.Element} The JSX element representing the header.
 */
export function Header() {
	const { setSearchQuery } = useListContext();

	/**
	 * Handles the search input change event.
	 * @param {React.ChangeEvent<HTMLInputElement>} e - The event object representing the input change.
	 */
	const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSearchQuery(e.target.value);
	};

	return (
		<div style={styles.header}>
			<h1 style={styles.heading}>Item Lister</h1>
			<input
				id="search"
				style={styles.searchBar}
				type="text"
				placeholder="Search items..."
				onChange={handleSearch}
			/>
		</div>
	);
}
