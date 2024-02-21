import React, { useEffect, useState } from "react";
import "./App.scss";
import "./Quote.scss";
import { ApiQuote } from "./types/quotes.types";
import { Quote } from "./Quote";
import ClipLoader from "react-spinners/ClipLoader";

/**
 * Main component of the application.
 * Manages the state of quotes, filter tags, and loading status.
 */
function App() {
	// State hooks for managing quotes, filter tags, and loading status
	const [allQuotes, setAllQuotes] = useState<ApiQuote[]>([]);
	const [quotes, setQuotes] = useState<ApiQuote[]>([]);
	const [filterTags, setFilterTags] = useState<string[]>([]);
	const [isLoading, setIsLoading] = useState<boolean>(false);

	/**
	 * Fetches a random quote from the API.
	 * Updates the allQuotes state with the fetched quote.
	 * Sets isLoading to true while fetching.
	 */
	const fetchQuotes = () => {
		setIsLoading(true);
		fetch("https://api.quotable.io/quotes/random?limit=1")
			.then((response) => response.json())
			.then((data: ApiQuote[]) => {
				setAllQuotes([data[0], ...allQuotes]);
				setIsLoading(false);
			});
	};

	/**
	 * Event handler for the "New Quote" button click.
	 * Triggers the fetchQuotes function to fetch a new quote.
	 */
	const onNewQuoteButtonClick = () => {
		fetchQuotes();
	};

	/**
	 * Event handler for clicking on a filter tag.
	 * Adds the clicked tag to the filterTags state if it's not already included.
	 * @param {string} tag - The tag to be added to the filterTags state.
	 */
	const handleTagClick = (tag: string) => {
		if (!filterTags.includes(tag)) {
			setFilterTags([...filterTags, tag]);
		}
	};

	/**
	 * Event handler for removing a filter tag.
	 * Removes the clicked tag from the filterTags state.
	 * @param {string} tag - The tag to be removed from the filterTags state.
	 */
	const handleRemoveTag = (tag: string) => {
		setFilterTags(filterTags.filter((t) => t !== tag));
	};

	// Fetch initial quotes when the component mounts
	useEffect(() => {
		fetch("https://api.quotable.io/quotes/random?limit=3")
			.then((response) => response.json())
			.then((data: ApiQuote[]) => {
				setAllQuotes(data);
			});
	}, []);

	// Filter quotes based on filterTags when filterTags or allQuotes change
	useEffect(() => {
		let filteredQuotes = allQuotes;
		filterTags.forEach((tag) => {
			filteredQuotes = filteredQuotes.filter((quote) =>
				quote.tags.includes(tag)
			);
		});
		setQuotes(filteredQuotes);
	}, [allQuotes, filterTags]);

	// JSX returned by the component
	return (
		<>
			<button
				className="new-quote-btn"
				onClick={onNewQuoteButtonClick}
				disabled={isLoading}
			>
				{isLoading ? (
					<ClipLoader color={"#ffffff"} loading={isLoading} size={35} />
				) : (
					"NEW QUOTE"
				)}
			</button>
			<p className="filter-input">
				Filters:{" "}
				{filterTags.map((tag, index) => (
					<span className="quote-tags-filter" key={index}>
						{tag}
						<span className="close-icon" onClick={() => handleRemoveTag(tag)}>
							{" "}
							×
						</span>
					</span>
				))}
			</p>
			<hr className="line" />
			{quotes.map((quote) => {
				return (
					<Quote key={quote._id} quote={quote} onTagClick={handleTagClick} />
				);
			})}
		</>
	);
}

export default App;
