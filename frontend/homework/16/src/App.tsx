import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ProductListPage } from "./pages/ProductListPage";
import { ProductDetailPage } from "./pages/ProductDetailPage";
import { ProductContextProvider } from "./contexts/ProductContextProvider";

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
