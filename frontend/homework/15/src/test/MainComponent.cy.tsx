/**
 * Component Testing MainComponent
 */
import { Provider } from "react-redux";
import store from "../redux/store";
import { MainComponent } from "../todo-list/MainComponent";

describe("<MainComponent />", () => {
	it("renders", () => {
		// see: https://on.cypress.io/mounting-react
		cy.mount(
			<Provider store={store}>
				{" "}
				<MainComponent />
			</Provider>
		);
	});
});
