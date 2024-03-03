/**
 * PortfolioPage component displays the user's portfolio transactions with filtering options.
 */
/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable no-mixed-spaces-and-tabs */
import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../redux/store";
import { fetchTransactions } from "../thunks/FetchTransactions";
import {
	CircularProgress,
	Checkbox,
	FormControlLabel,
	TextField,
} from "@mui/material";
import { Transaction } from "../redux/PortfolioSlice";
import {
	FilterBoxStyle,
	FilterHeaderstyle,
	clearAllStyle,
	dateStyle,
	searchStyle,
	stockDotStyle,
	stockInfoStyle,
	stockNameStyle,
	stockPriceStyle,
	stockSymbolStyle,
	stockTimeStyle,
} from "../styles/PortFolioPageStyle";

const PortfolioPage = () => {
	const dispatch = useDispatch();
	const { transactions, loading, error } = useSelector(
		(state: RootState) => state.portfolio
	);
	const [filteredTransactions, setFilteredTransactions] = useState<
		Transaction[]
	>([]);
	const [startDate, setStartDate] = useState<string>("");
	const [endDate, setEndDate] = useState<string>("");
	const [stockFilters, setStockFilters] = useState<string[]>([]);
	const [statusFilters, setStatusFilters] = useState<string[]>([]);
	const [searchText, setSearchText] = useState<string>("");
	const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
	const [uniqueStockNames, setUniqueStockNames] = useState<string[]>([]);

	/**
	 * Fetch transactions from the server on component mount.
	 */
	useEffect(() => {
		dispatch(fetchTransactions());
	}, [dispatch]);

	/**
	 * Update filtered transactions when transactions state changes.
	 */
	useEffect(() => {
		setFilteredTransactions(transactions);
	}, [transactions]);

	/**
	 * Update unique stock names when filtered transactions change.
	 */
	useEffect(() => {
		const uniqueNames = Array.from(
			new Set(filteredTransactions.map((transaction) => transaction.stock_name))
		);
		setUniqueStockNames(uniqueNames);
	}, [filteredTransactions]);

	/**
	 * Handles change event for start date input field.
	 * @param {React.ChangeEvent<HTMLInputElement>} event - The event object.
	 */
	const handleStartDateChange = (
		event: React.ChangeEvent<HTMLInputElement>
	) => {
		setStartDate(event.target.value);
	};

	/**
	 * Handles change event for end date input field.
	 * @param {React.ChangeEvent<HTMLInputElement>} event - The event object.
	 */
	const handleEndDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setEndDate(event.target.value);
	};

	/**
	 * Handles change event for search text input field.
	 * @param {React.ChangeEvent<HTMLInputElement>} event - The event object.
	 */
	const handleSearchTextChange = (
		event: React.ChangeEvent<HTMLInputElement>
	) => {
		setSearchText(event.target.value);
	};

	/**
	 * Clears all filters.
	 */
	const handleClearFilters = () => {
		setStartDate("");
		setEndDate("");
		setStockFilters([]);
		setStatusFilters([]);
		setSearchText("");
		setFilteredTransactions(transactions);
		setSelectedStocks([]);
	};

	/**
	 * Handles change event for status filter checkboxes.
	 * @param {React.ChangeEvent<HTMLInputElement>} event - The event object.
	 */
	const handleStatusFilterChange = (
		event: React.ChangeEvent<HTMLInputElement>
	) => {
		const value = event.target.value;
		if (statusFilters.includes(value)) {
			setStatusFilters(statusFilters.filter((status) => status !== value));
		} else {
			setStatusFilters([...statusFilters, value]);
		}
	};

	/**
	 * Handles change event for stock checkbox.
	 * @param {React.ChangeEvent<HTMLInputElement>} event - The event object.
	 * @param {string} stock - The stock name.
	 */
	const handleStockCheckboxChange = (
		event: React.ChangeEvent<HTMLInputElement>,
		stock: string
	) => {
		const isChecked = event.target.checked;

		if (isChecked) {
			setSelectedStocks((prevSelectedStocks) => [...prevSelectedStocks, stock]);
		} else {
			setSelectedStocks((prevSelectedStocks) =>
				prevSelectedStocks.filter((item) => item !== stock)
			);
		}
	};

	/**
	 * Filters transactions based on selected filters.
	 */
	const filterTransactions = () => {
		let filtered = transactions.filter(
			(transaction) =>
				transaction.stock_name
					.toLowerCase()
					.includes(searchText.toLowerCase()) ||
				transaction.stock_symbol
					.toLowerCase()
					.includes(searchText.toLowerCase())
		);

		if (startDate) {
			filtered = filtered.filter(
				(transaction) => new Date(transaction.timestamp) >= new Date(startDate)
			);
		}
		if (endDate) {
			filtered = filtered.filter(
				(transaction) => new Date(transaction.timestamp) <= new Date(endDate)
			);
		}
		if (statusFilters.length > 0) {
			filtered = filtered.filter((transaction) =>
				statusFilters.includes(transaction.status)
			);
		}
		if (selectedStocks.length > 0) {
			filtered = filtered.map((transaction) => ({
				...transaction,
				selected: selectedStocks.includes(transaction.stock_name),
			}));
		}

		filtered.sort(
			(a, b) =>
				new Date(a.timestamp).getTime() - new Date(b.timestamp).getTime()
		);

		setFilteredTransactions(filtered);
	};

	useEffect(() => {
		filterTransactions();
	}, [
		startDate,
		endDate,
		stockFilters,
		statusFilters,
		searchText,
		transactions,
		filterTransactions,
	]);

	return (
		<div style={{ display: "flex" }}>
			<div style={FilterBoxStyle}>
				<div style={FilterHeaderstyle}>
					<div>Filters</div>
					<div style={clearAllStyle}onClick={handleClearFilters}>Clear All</div>
				</div>
				<hr />
				<div>
					<TextField
						style={searchStyle}
						label="Search for a stock"
						value={searchText}
						onChange={handleSearchTextChange}
					/>
				</div>
				<hr />
				<div style={dateStyle}>
					<TextField
						label="Start Date"
						placeholder="Start Date"
						type="date"
						value={startDate}
						onChange={handleStartDateChange}
						InputLabelProps={{
							shrink: true,
						}}
					/>
					<TextField
						label="End Date"
						placeholder="End Date"
						type="date"
						value={endDate}
						onChange={handleEndDateChange}
						InputLabelProps={{
							shrink: true,
						}}
					/>
				</div>
				<hr />
				<div>
					<div>
						<FormControlLabel
							control={
								<Checkbox
									checked={statusFilters.includes("Passed")}
									onChange={handleStatusFilterChange}
									value="Passed"
								/>
							}
							label="Passed"
						/>
					</div>
					<div>
						<FormControlLabel
							control={
								<Checkbox
									checked={statusFilters.includes("Failed")}
									onChange={handleStatusFilterChange}
									value="Failed"
								/>
							}
							label="Failed"
						/>
					</div>
				</div>
				<hr />
				<div style={{ maxHeight: "300px", overflowY: "auto" }}>
					{uniqueStockNames.map((stockName, index) => (
						<div key={index}>
							<FormControlLabel
								control={
									<Checkbox
										checked={selectedStocks.includes(stockName)}
										onChange={(e) => handleStockCheckboxChange(e, stockName)}
										value={stockName}
									/>
								}
								label={stockName}
							/>
						</div>
					))}
				</div>
			</div>
			<div>
				{loading && <CircularProgress />}
				{error && <p>Error: {error}</p>}
				{!loading &&
					filteredTransactions.map((transaction, index) => {
						const currentDate = new Date(transaction.timestamp);
						const previousTransaction = filteredTransactions[index - 1];
						const previousDate =
							previousTransaction && new Date(previousTransaction.timestamp);

						return (
							<div
								key={index}
								style={{
									marginTop: "1rem",
									opacity: selectedStocks.includes(transaction.stock_name)
										? 1
										: 0.7,
									fontWeight: selectedStocks.includes(transaction.stock_name)
										? "bold"
										: "normal",
								}}
							>
								{index === 0 ||
								currentDate.toLocaleDateString("en-GB", {
									day: "2-digit",
									month: "short",
									year: "numeric",
								}) !==
									previousDate.toLocaleDateString("en-GB", {
										day: "2-digit",
										month: "short",
										year: "numeric",
									}) ? (
									<p
										style={{
											color: "gray",
										}}
									>
										{currentDate.toLocaleDateString("en-GB", {
											day: "2-digit",
											month: "short",
											year: "numeric",
										})}
									</p>
								) : null}
								<div style={stockInfoStyle}>
									<div style={stockNameStyle}>{transaction.stock_name}</div>
									<div style={stockSymbolStyle}>{transaction.stock_symbol}</div>
									<div style={stockPriceStyle}>
										₹{transaction.transaction_price}
									</div>
									<div style={stockTimeStyle}>
										{currentDate.toLocaleTimeString("en-US", {
											hour: "numeric",
											minute: "2-digit",
											hour12: true,
										})}
									</div>
									<div style={stockDotStyle}>
										{transaction.status === "Passed" ? (
											<span style={{ color: "green" }}>●</span>
										) : (
											<span style={{ color: "red" }}>●</span>
										)}
									</div>
								</div>
							</div>
						);
					})}
			</div>
		</div>
	);
};

export default PortfolioPage;
