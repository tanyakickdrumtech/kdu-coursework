import React from "react";
import { useListContext } from "./ListContext";
import "./Header.scss";

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
		<div id="header">
			<h1 id="heading">Item Lister</h1>
			<input
				id="search-bar"
				type="text"
				placeholder="Search items..."
				onChange={handleSearch}
			/>
		</div>
	);
}
