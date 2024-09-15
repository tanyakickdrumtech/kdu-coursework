import { Header } from "./Header";
import { Section } from "./Section";
import { ListProvider } from "./ListContext";

/**
 * Main component representing the main content area of the application.
 * This component renders the header and section components.
 */
export function MainComponent() {
	return (
		<ListProvider>
			<div>
				<Header />
				<Section />
			</div>
		</ListProvider>
	);
}
