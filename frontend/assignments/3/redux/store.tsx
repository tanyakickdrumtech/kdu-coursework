/**
 * This file initializes the Redux store and exports it along with type definitions for RootState and AppDispatch.
 */

import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "./StocksSlice";
import portfolioReducer from "./PortfolioSlice";

/**
 * Configure and create the Redux store.
 * It combines multiple reducers into a single root reducer.
 */
const store = configureStore({
	reducer: {
		stocks: stocksReducer,
		portfolio: portfolioReducer,
	},
});

/**
 * Type definition representing the RootState of the Redux store.
 */
export type RootState = ReturnType<typeof store.getState>;

/**
 * Type definition representing the AppDispatch function for dispatching actions to the Redux store.
 */
export type AppDispatch = typeof store.dispatch;

export default store;
