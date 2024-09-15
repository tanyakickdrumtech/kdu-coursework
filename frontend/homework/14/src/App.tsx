import { Provider } from "react-redux";
import { MainComponent } from "./todo-list/MainComponent";
import store from "./redux/store";

/**
 * Root component of the application.
 * Wraps the Main component with Provider
 */
function App() {
	return (
		<div id="main-section">
			<Provider store={store}>
				<MainComponent />
			</Provider>
		</div>
	);
}

export default App;
