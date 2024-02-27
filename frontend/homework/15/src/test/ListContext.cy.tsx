/**
 * Component Testing ListContext
 */
import { Provider } from "react-redux";
import store from "../redux/store";
import { ListProvider } from "../todo-list/ListContext";

describe("<ListProvider />", () => {
	it("renders", () => {
		// see: https://on.cypress.io/mounting-react
		cy.mount(
			<Provider store={store}>
				<ListProvider children={null as React.ReactNode} />
			</Provider>
		);
	});
});
