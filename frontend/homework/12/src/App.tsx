import React from "react";
import "./App.scss";
import { Main } from "./todo-list/Main";
import { ListProvider } from "./todo-list/ListContext";

/**
 * Root component of the application.
 * Wraps the Main component with ListProvider to provide list context to the application.
 */
function App() {
	return (
		<div id="main-section">
			<ListProvider>
				<Main />
			</ListProvider>
		</div>
	);
}

export default App;
