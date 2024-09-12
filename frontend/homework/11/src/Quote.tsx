import React from "react";
import { ApiQuote } from "./types/quotes.types";
import "./Quote.scss";

/**
 * Props for the Quote component.
 */
interface QuoteProps {
	/**
	 * The quote object containing the content, author, date, and tags.
	 */
	quote: ApiQuote;
	/**
	 * Callback function to handle tag click events.
	 * @param {string} tag - The tag that was clicked.
	 */
	onTagClick: (tag: string) => void;
}

/**
 * Component to display a single quote.
 * @param {QuoteProps} props - Props passed to the component.
 * @returns {JSX.Element} - The rendered Quote component.
 */
export function Quote({ quote, onTagClick }: QuoteProps): JSX.Element {
	/**
	 * Event handler for clicking on a tag.
	 * Invokes the onTagClick callback with the clicked tag.
	 * @param {string} tag - The tag that was clicked.
	 */
	const handleTagClick = (tag: string) => {
		onTagClick(tag);
	};

	// JSX returned by the component
	return (
		<div className="container">
			<h3 className="sub-content">
				{quote.content}
				<p className="author">~{quote.author}</p>
				<p className="date">{quote.dateAdded}</p>
			</h3>
			<div className="tag-container">
				{quote.tags.map((tag) => {
					return (
						<span
							className="quote-tags"
							key={tag}
							onClick={() => handleTagClick(tag)}
						>
							{tag}
						</span>
					);
				})}
			</div>
		</div>
	);
}
