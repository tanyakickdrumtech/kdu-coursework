/**
 * ExplorePage component displays a list of stocks available for exploration.
 * Users can view company names, base prices, and add/remove stocks from their watchlist.
 * They can navigate through the list using pagination.
 */
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store";
import { Stock, fetchStocks } from "../thunks/FetchStocks";
import Pagination from "@mui/material/Pagination";
import { Box } from "@mui/system";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CancelIcon from "@mui/icons-material/Cancel";
import {
	Table,
	TableHead,
	TableRow,
	TableCell,
	TableBody,
	Typography,
	CircularProgress,
} from "@mui/material";
import { addToWatchlist, removeFromWatchlist } from "../redux/StocksSlice";
import { Link } from "react-router-dom";

const ExplorePage = () => {
	const dispatch: AppDispatch = useDispatch();

	const [hoveredStock, setHoveredStock] = useState<string | null>(null);
	const stocks = useSelector((state: RootState) => state.stocks);
	const watchList = useSelector((state: RootState) => state.stocks.watchList);
	const [page, setPage] = useState(1);

	useEffect(() => {
		dispatch(fetchStocks());
	}, [dispatch]);

	/**
	 * Handles page change event for pagination.
	 * @param {React.ChangeEvent<unknown>} event - The event object.
	 * @param {number} newPage - The new page number.
	 */
	const handleChangePage = (
		event: React.ChangeEvent<unknown>,
		newPage: number
	) => {
		setPage(newPage);
	};

	const itemsPerPage = 7;
	const startIndex = (page - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;

	/**
	 * Handles adding a stock to the watchlist.
	 * @param {Stock} stock - The stock to be added.
	 */
	const handleAddToWatchlist = (stock: Stock) => {
		dispatch(addToWatchlist(stock));
	};

	/**
	 * Handles removing a stock from the watchlist.
	 * @param {Stock} stockName - The name of the stock to be removed.
	 */
	const handleRemoveFromWatchlist = (stockName: Stock) => {
		dispatch(removeFromWatchlist(stockName));
	};

	/**
	 * Handles mouse enter event for a stock item.
	 * @param {string} stockName - The name of the stock.
	 */
	const handleMouseEnter = (stockName: string) => {
		setHoveredStock(stockName);
	};

	/**
	 * Handles mouse leave event for a stock item.
	 */
	const handleMouseLeave = () => {
		setHoveredStock(null);
	};

	const [activeLink, setActiveLink] = useState("");
	/**
	 * Handles click event for navigation links.
	 * @param {string} link - The link to navigate to.
	 */
	const handleLinkClick = (link: string): void => {
		setActiveLink(link);
	};

	// Sort stocks alphabetically by stock name
	const sortedStocks = stocks.stocks
		.slice()
		.sort((a, b) => a.stock_name.localeCompare(b.stock_name));

	return (
		<div
			style={{
				display: "flex",
				flexDirection: "column",
				justifyContent: "center",
				alignItems: "center",
			}}
		>
			<Box
				display="flex"
				justifyContent="start"
				marginRight="75rem"
				marginTop="5px"
			>
				<Typography variant="h6">
					<Link
						to="/explore"
						style={{
							color: "inherit",
							textDecoration: "none",
							borderBottom:
								activeLink === "explore" ? "2px solid #1876d1" : "none",
							paddingBottom: "2px",
							marginRight: "20px",
						}}
						onClick={() => handleLinkClick("explore")}
					>
						Explore
					</Link>
				</Typography>
				<Typography variant="h6">
					<Link
						to="/watchlist"
						style={{
							color: "inherit",
							textDecoration: "none",
							borderBottom:
								activeLink === "watchlist" ? "2px solid #1876d1" : "none",
							paddingBottom: "2px",
						}}
						onClick={() => handleLinkClick("watchlist")}
					>
						My Watchlist
					</Link>
				</Typography>
			</Box>
			<div
				style={{
					border: "2px solid gray",
					borderRadius: "20px",
					marginTop: "2rem",
				}}
			>
				{stocks.status === "pending" && (
					<div
						style={{
							display: "flex",
							justifyContent: "center",
							alignItems: "center",
							marginTop: "20px",
						}}
					>
						<CircularProgress color="primary" />
					</div>
				)}
				{stocks.status === "rejected" && <p>Error: {stocks.error}</p>}
				{stocks.status === "fulfilled" && (
					<>
						<Table>
							<TableHead>
								<TableRow>
									<TableCell style={{ paddingRight: "60px" }}>
										Company
									</TableCell>
									<TableCell style={{ paddingLeft: "600px" }}>
										Base Price
									</TableCell>
									<TableCell style={{ paddingLeft: "30px" }}>
										Watchlist
									</TableCell>
								</TableRow>
							</TableHead>
							<TableBody>
								{sortedStocks
									.slice(startIndex, endIndex)
									.map((stock, index) => (
										<React.Fragment key={index}>
											<TableRow style={{ marginBottom: "20px" }}>
												<TableCell>
													<Link
														to={{
															pathname: `/stock/${stock.stock_name}`,
															search: `?selectedStock=${stock.stock_name}`, // Pass the selected stock's name as a query parameter
														}}
														style={{ textDecoration: "none", color: "inherit" }}
													>
														<div>{stock.stock_name}</div>
													</Link>
												</TableCell>
												<TableCell style={{ paddingLeft: "600px" }}>
													₹{stock.base_price}
												</TableCell>
												<TableCell>
													<div
														onMouseEnter={() =>
															handleMouseEnter(stock.stock_name)
														}
														onMouseLeave={handleMouseLeave}
														onClick={() =>
															watchList.includes(stock)
																? handleRemoveFromWatchlist(stock)
																: handleAddToWatchlist(stock)
														}
													>
														{watchList.some(
															(stk) =>
																JSON.stringify(stk) === JSON.stringify(stock)
														) ? (
															hoveredStock === stock.stock_name ? (
																<CancelIcon
																	style={{ fill: "red", marginLeft: "25px" }}
																/>
															) : (
																<CheckCircleIcon
																	style={{
																		fill: "#1876d1",
																		marginLeft: "25px",
																	}}
																/>
															)
														) : (
															<AddCircleOutlineIcon
																style={{ fill: "#1876d1", marginLeft: "25px" }}
															/>
														)}
													</div>
												</TableCell>
											</TableRow>
										</React.Fragment>
									))}
							</TableBody>
						</Table>
						<Box
							style={{
								display: "flex",
								alignItems: "center",
								justifyContent: "center",
								marginTop: "20px",
							}}
						>
							<Pagination
								count={Math.ceil(sortedStocks.length / itemsPerPage)}
								page={page}
								onChange={handleChangePage}
								color="primary"
							/>
						</Box>
					</>
				)}
			</div>
		</div>
	);
};

export default ExplorePage;
