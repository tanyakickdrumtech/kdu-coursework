/**
 * Redux store configuration.
 */
import { configureStore } from "@reduxjs/toolkit";
import ProductSlice from "./ProductSlice";
import SnackBarSlice from "./SnackBarSlice";

/**
 * Configure Redux store with combined reducers.
 */
export const store = configureStore({
	reducer: {
		products: ProductSlice,
		snackbar: SnackBarSlice,
	},
});

/**
 * Define the type for the root state of the Redux store.
 */
export type RootState = ReturnType<typeof store.getState>;

/**
 * Define the type for the dispatch function of the Redux store.
 */
export type AppDispatch = typeof store.dispatch;
