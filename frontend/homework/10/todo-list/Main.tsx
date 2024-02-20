import React, { useState } from "react";
import { Header } from "./Header";
import { Section } from "./Section";

interface IList {
	id: number;
	text: string;
}

/**
 * Main component for managing the application state.
 * @returns {JSX.Element} Main component.
 */
export function Main() {
	let listItem: IList[] = [];
	const [list, setList] = useState(listItem);
	const [searchQuery, setSearchQuery] = useState("");

	/**
	 * Handles search query changes.
	 * @param {string} query - The search query.
	 */
	const handleSearch = (query: string) => {
		setSearchQuery(query);
	};

	return (
		<div>
			<Header onSearch={handleSearch} />
			<Section list={list} setList={setList} searchQuery={searchQuery} />
		</div>
	);
}
