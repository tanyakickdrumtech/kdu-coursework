/**
 * This file defines a Redux slice for managing portfolio-related state, including transactions, loading status, and errors.
 */

import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "./store";

/**
 * Interface representing a transaction in the portfolio.
 */
export interface Transaction {
	stock_name: string;
	stock_symbol: string;
	transaction_price: number;
	timestamp: string;
	status: string;
}

/**
 * Interface representing the state of the portfolio slice.
 */
interface PortfolioState {
	transactions: Transaction[];
	loading: boolean;
	error: string | null;
}

/**
 * Initial state for the portfolio slice.
 */
const initialState: PortfolioState = {
	transactions: [],
	loading: false,
	error: null,
};

/**
 * Redux slice for managing portfolio state.
 */
export const portfolioSlice = createSlice({
	name: "portfolio",
	initialState,
	reducers: {
		/**
		 * Reducer for setting loading state to true when starting to fetch transactions.
		 * @param {PortfolioState} state - The current portfolio state.
		 */
		fetchTransactionsStart: (state) => {
			state.loading = true;
			state.error = null;
		},
		/**
		 * Reducer for handling successful transaction fetching.
		 * @param {PortfolioState} state - The current portfolio state.
		 * @param {PayloadAction<Transaction[]>} action - Payload containing the fetched transactions.
		 */
		fetchTransactionsSuccess: (state, action: PayloadAction<Transaction[]>) => {
			state.loading = false;
			state.transactions = action.payload;
		},
		/**
		 * Reducer for handling transaction fetching failure.
		 * @param {PortfolioState} state - The current portfolio state.
		 * @param {PayloadAction<string>} action - Payload containing the error message.
		 */
		fetchTransactionsFailure: (state, action: PayloadAction<string>) => {
			state.loading = false;
			state.error = action.payload;
		},
	},
});

export const {
	fetchTransactionsStart,
	fetchTransactionsSuccess,
	fetchTransactionsFailure,
} = portfolioSlice.actions;

/**
 * Selector function to retrieve transactions from the portfolio state.
 * @param {RootState} state - The root state of the Redux store.
 * @returns {Transaction[]} - Array of transactions.
 */
export const selectTransactions = (state: RootState) =>
	state.portfolio.transactions;

/**
 * Selector function to retrieve loading state from the portfolio state.
 * @param {RootState} state - The root state of the Redux store.
 * @returns {boolean} - Loading state.
 */
export const selectLoading = (state: RootState) => state.portfolio.loading;

/**
 * Selector function to retrieve error message from the portfolio state.
 * @param {RootState} state - The root state of the Redux store.
 * @returns {string | null} - Error message or null if no error.
 */
export const selectError = (state: RootState) => state.portfolio.error;

export default portfolioSlice.reducer;
