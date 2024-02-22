import React from "react";
import { Header } from "./Header";
import { Section } from "./Section";

/**
 * Main component representing the main content area of the application.
 * This component renders the header and section components.
 */
export function Main() {
	return (
		<div>
			<Header />
			<Section />
		</div>
	);
}
