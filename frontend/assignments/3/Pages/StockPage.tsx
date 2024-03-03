/**
 * StockPage component displays information about a selected stock including its price, price change, and trading options.
 */
/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../redux/store";
import { fetchStocks } from "../thunks/FetchStocks";
import { Select, MenuItem, SelectChangeEvent, TextField } from "@mui/material";
import {
	PriceStyle,
	buyButtonStyle,
	buySellContainer,
	detailContainer,
	inputStyle,
	sellButtonStyle,
	stockDropDown,
	symbolStyle,
	upArrowStyle,
	downArrowStyle,
	priceTagPositiveStyle,
	priceTagNegativeStyle,
	graphStyle,
	mainContent,
	historyStockStyle,
	historyUserStyle,
} from "../styles/StockPageStyles";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";

const StockPage = () => {
	const dispatch = useDispatch();
	const location = useLocation();
	const [loading, setLoading] = useState<boolean>(true);
	const [selectedStockName, setSelectedStockName] = useState<string>("");
	const [selectedStockData, setSelectedStockData] = useState<any>(null);
	const [prevPrice, setPrevPrice] = useState<number>(0);
	const [candlesticks, setCandlesticks] = useState<Array<string>>([]);
	const allStocks = useSelector((state: RootState) => state.stocks.stocks);

	/**
	 * Fetch stocks from the server on component mount.
	 */
	useEffect(() => {
		dispatch(fetchStocks());
	}, [dispatch]);

	/**
	 * Update selected stock name based on URL query parameters or local storage.
	 */
	useEffect(() => {
		const searchParams = new URLSearchParams(location.search);
		const stockName = searchParams.get("selectedStock");
		const storedStockName = localStorage.getItem("selectedStockName");
		if (stockName) {
			setSelectedStockName(stockName);
			localStorage.setItem("selectedStockName", stockName);
		} else if (storedStockName) {
			setSelectedStockName(storedStockName);
		}
	}, [location.search]);

	/**
	 * Update selected stock data and candlesticks when allStocks or selectedStockName changes.
	 */
	useEffect(() => {
		const selectedStock = allStocks.find(
			(stock) => stock.stock_name === selectedStockName
		);
		if (selectedStock) {
			setPrevPrice(selectedStock.base_price);
			setSelectedStockData(selectedStock);
			setLoading(false);
			// Reset candlesticks when selected stock changes
			setCandlesticks([]);
		} else {
			setLoading(true);
		}
	}, [allStocks, selectedStockName]);

	/**
	 * Update candlesticks based on current price.
	 * @param {number} currentPrice - The current price of the stock.
	 */
	const updateCandlesticks = (currentPrice: number) => {
		const color = currentPrice > prevPrice ? "#b3f2bb" : "#ffc9c9";
		const borderColor = color === "#b3f2bb" ? "#63be71" : "#e95959";

		let height = Math.abs(currentPrice - prevPrice) * 0.1;
		height = Math.min(height, 480);
		const style = `transform: rotateY(-180deg); width: 20px; background-color: ${color}; height: ${height}px; border: 1px solid ${borderColor}; display: inline-block;`; // Add transform style to rotate candlestick
		const newCandlestick = `<div style="${style}"></div>`;
		setCandlesticks([...candlesticks, newCandlestick]);
	};

	/**
	 * Update selected stock data and candlesticks with random price change.
	 */
	useEffect(() => {
		let timer: ReturnType<typeof setTimeout>;
		if (selectedStockData) {
			timer = setTimeout(() => {
				const newPrice = Math.random() * 5000;
				setPrevPrice(selectedStockData.base_price);
				setSelectedStockData({ ...selectedStockData, base_price: newPrice });
				updateCandlesticks(newPrice);
			}, 3000);
		}

		return () => clearTimeout(timer);
	}, [selectedStockData, updateCandlesticks]);

	/**
	 * Handles dropdown change event.
	 * @param {SelectChangeEvent<string>} event - The event object.
	 */
	const handleDropdownChange = (event: SelectChangeEvent<string>) => {
		const selectedStockName = event.target.value;
		setSelectedStockName(selectedStockName);
		setSelectedStockData(
			allStocks.find((stock) => stock.stock_name === selectedStockName)
		);
		window.history.pushState(null, "", `/stock/${selectedStockName}`);
		localStorage.setItem("selectedStockName", selectedStockName);
		// Reset candlesticks when dropdown changes
		setCandlesticks([]);
	};

	/**
	 * Calculates percentage change between current and previous prices.
	 * @param {number} currentPrice - The current price.
	 * @param {number} previousPrice - The previous price.
	 * @returns {number} - The percentage change.
	 */
	const calculatePercentageChange = (
		currentPrice: number,
		previousPrice: number
	) => {
		if (previousPrice === 0) return 0;
		return (Math.abs(currentPrice - previousPrice) / previousPrice) * 100;
	};

	return (
		<div style={mainContent}>
			<div>
				<div style={detailContainer}>
					<div>
						<Select
							style={{
								...stockDropDown,
								maxHeight: "250px",
								overflowY: "auto",
							}}
							value={selectedStockName}
							onChange={handleDropdownChange}
							MenuProps={{ PaperProps: { style: { maxHeight: 250 } } }}
						>
							{allStocks.map((stock) => (
								<MenuItem key={stock.stock_name} value={stock.stock_name}>
									<span style={symbolStyle}>{stock.stock_symbol}</span>
									{stock.stock_name}
								</MenuItem>
							))}
						</Select>
					</div>
					{selectedStockData && (
						<div
							style={{
								display: "flex",
								flexDirection: "row",
								alignItems: "center",
							}}
						>
							<p style={PriceStyle}>
								Price{" "}
								{selectedStockData.base_price > prevPrice ? (
									<span style={priceTagPositiveStyle}>
										{selectedStockData.base_price}
									</span>
								) : (
									<span style={priceTagNegativeStyle}>
										{selectedStockData.base_price}
									</span>
								)}
								{selectedStockData.base_price > prevPrice ? (
									<span>
										<ArrowUpwardIcon style={upArrowStyle} />
									</span>
								) : (
									<span>
										<ArrowDownwardIcon style={downArrowStyle} />
									</span>
								)}
								<span>
									{calculatePercentageChange(
										selectedStockData.base_price,
										prevPrice
									).toFixed(2)}
									%
								</span>
							</p>
							<div style={buySellContainer}>
								<TextField style={inputStyle} type="number" label="Enter QTY" />
								<button style={buyButtonStyle}>Buy</button>
								<button style={sellButtonStyle}>Sell</button>
							</div>
						</div>
					)}
				</div>
				<div style={graphStyle}>
					{candlesticks.map((candlestick, index) => (
						<div
							key={index}
							dangerouslySetInnerHTML={{ __html: candlestick }}
						/>
					))}
				</div>
			</div>
			<div>
				<div style={historyStockStyle}>
					<p>History</p>
				</div>
				<div style={historyUserStyle}></div>
			</div>
		</div>
	);
};

export default StockPage;
