import React, { useRef } from "react";
import searchIcon from "../assets/search-icon.svg";

/**
 * Props interface for the Header component.
 */
interface HeaderProps {
	/**
	 * Callback function to handle search.
	 * @param searchTerm The search term entered by the user.
	 */
	onSearch: (searchTerm: string) => void;
}

/**
 * Header component for the application.
 * @param onSearch Callback function to handle search.
 * @returns Header component JSX.
 */
export const Header: React.FC<HeaderProps> = ({ onSearch }) => {
	const searchRef = useRef<HTMLInputElement>(null);

	/**
	 * Handles the search button click event.
	 */
	const handleSearchClick = () => {
		if (searchRef.current && searchRef.current.value) {
			onSearch(searchRef.current.value);
		}
	};

	// Define styles using object literal
	const styles = {
		header: {
			backgroundColor: "#2a2a72",
			height: "35px",
		},
		input: {
			marginTop: "0.3rem",
			marginLeft: "7rem",
		},
		button: {
			marginLeft: "0",
			marginTop: "0.3rem",
			cursor: "pointer",
		},
		image: {
			width: "12px",
			cursor: "pointer",
		},
	};

	return (
		<div style={styles.header}>
			<input
				style={styles.input}
				type="text"
				ref={searchRef}
				placeholder="Search..."
			/>
			<button style={styles.button} onClick={handleSearchClick}>
				<img style={styles.image} src={searchIcon} alt="Search" />
			</button>
		</div>
	);
};
