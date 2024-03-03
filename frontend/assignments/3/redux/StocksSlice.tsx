/**
 * This file defines a Redux slice for managing stocks-related state, including stocks data, status, errors, and watchlist.
 */

import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { fetchStocks, Stock } from "../thunks/FetchStocks";
import { RootState } from "./store";

/**
 * Interface representing the state of stocks slice.
 */
interface StocksState {
	stocks: Stock[];
	status: "pending" | "fulfilled" | "rejected";
	error?: string;
	watchList: Stock[];
}

/**
 * Initial state for the stocks slice.
 */
const initialState: StocksState = {
	stocks: [],
	status: "pending",
	watchList: [],
};

/**
 * Redux slice for managing stocks state.
 */
const stocksSlice = createSlice({
	name: "stocks",
	initialState,
	reducers: {
		/**
		 * Reducer for adding a stock to the watchlist.
		 * @param {StocksState} state - The current stocks state.
		 * @param {PayloadAction<Stock>} action - Payload containing the stock to be added.
		 */
		addToWatchlist(state, action: PayloadAction<Stock>) {
			const stock = action.payload;
			if (
				!state.watchList.some((item) => item.stock_name === stock.stock_name)
			) {
				state.watchList.push(stock);
			}
		},
		/**
		 * Reducer for removing a stock from the watchlist.
		 * @param {StocksState} state - The current stocks state.
		 * @param {PayloadAction<Stock>} action - Payload containing the stock to be removed.
		 */
		removeFromWatchlist(state, action: PayloadAction<Stock>) {
			const stock = action.payload;
			state.watchList = state.watchList.filter(
				(item) => item.stock_name !== stock.stock_name
			);
		},
	},
	extraReducers: (builder) => {
		builder
			.addCase(fetchStocks.pending, (state) => {
				state.status = "pending";
			})
			.addCase(
				fetchStocks.fulfilled,
				(state, action: PayloadAction<Stock[]>) => {
					state.status = "fulfilled";
					state.stocks = action.payload;
				}
			)
			.addCase(fetchStocks.rejected, (state, action) => {
				state.status = "rejected";
				state.error = action.error.message ?? "Something went wrong.";
			});
	},
});

/**
 * Selector function to retrieve stocks state from the root state.
 * @param {RootState} state - The root state of the Redux store.
 * @returns {StocksState} - The stocks state.
 */
export const stockSelector = (state: RootState) => state.stocks;

export const { addToWatchlist, removeFromWatchlist } = stocksSlice.actions;
export default stocksSlice.reducer;
