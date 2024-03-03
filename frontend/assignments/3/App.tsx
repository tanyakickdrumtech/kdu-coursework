import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Explore from "./Dashboard/Explore";
import Watchlist from "./Dashboard/Watchlist";
import Header from "./Header/Header";
import StockPage from "./Pages/StockPage";
import PortfolioPage from "./Pages/PortfolioPage";
import SummarizerPage from "./Pages/SummarizerPage";

/**
 * Main component representing the entire application.
 * It sets up routing using BrowserRouter and defines routes for different pages.
 * @returns The JSX for the application.
 */
const App: React.FC = () => {
	return (
		<BrowserRouter>
			<div>
				<Header />
				<Routes>
					<Route path="/explore" element={<Explore />} />
					<Route path="/watchlist" element={<Watchlist />} />
					<Route path="/stock/:stock_name" element={<StockPage />} />
					<Route path="/portfolio" element={<PortfolioPage />} />
					<Route path="/summarizer" element={<SummarizerPage />} />
				</Routes>
			</div>
		</BrowserRouter>
	);
};

export default App;
