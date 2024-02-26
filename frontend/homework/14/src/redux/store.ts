import { configureStore } from "@reduxjs/toolkit";
import todoReducer from "./todoSlice";

/**
 * Configure and create the Redux store.
 *
 */
const store = configureStore({
	reducer: {
		todos: todoReducer,
	},
});

/**
 * The root state type derived from the Redux store.
 * @typedef {ReturnType<typeof store.getState>} RootState
 */
export type RootState = ReturnType<typeof store.getState>;

/**
 * The type representing the dispatch function of the Redux store.
 * @typedef {typeof store.dispatch} AppDispatch
 */
export type AppDispatch = typeof store.dispatch;

export default store;
