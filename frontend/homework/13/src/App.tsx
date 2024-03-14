import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ProductListPage } from "./pages/ProductListPage";
import { ProductDetailPage } from "./pages/ProductDetailPage";
import { ProductContextProvider } from "./contexts/ProductContextProvider";

/**
 * App component serves as the root component of the application.
 * It wraps the entire application with BrowserRouter for routing and ProductContextProvider for providing product data.
 * @returns JSX element representing the root component of the application.
 */
function App() {
	return (
		<ProductContextProvider>
			<BrowserRouter>
				<Routes>
					<Route path="/" element={<ProductListPage />} />
					<Route path="/product/:id" element={<ProductDetailPage />} />
				</Routes>
			</BrowserRouter>
		</ProductContextProvider>
	);
}

export default App;
