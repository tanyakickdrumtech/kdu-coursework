import React from "react";
import "./Header.css";

interface HeaderProps {
	onSearch: (query: string) => void;
}

/**
 * Header component for displaying a search bar.
 * @param {object} props - The props object.
 * @param {function} props.onSearch - Function to handle search queries.
 * @returns {JSX.Element} Header component.
 */
export function Header({ onSearch }: Readonly<HeaderProps>) {
	/**
	 * Handles the change event of the search input.
	 * @param {React.ChangeEvent<HTMLInputElement>} e - The event object.
	 */
	const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
		onSearch(e.target.value);
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
