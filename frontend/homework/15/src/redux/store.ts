import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage";

import todoReducer from "./todoSlice";

/**
 * Configuration object for Redux persist.
 */
const persistConfig = {
	key: "root", // Key for the persist configuration
	storage, // Storage engine to be used for persisting data
};

/**
 * Root reducer combining all reducers.
 */
const reducer = combineReducers({
	todos: todoReducer, // Reducer for todo slice
});

/**
 * Persisted root reducer.
 */
const persistedReducer = persistReducer(persistConfig, reducer);

/**
 * Redux store instance with persisted reducer.
 */
const store = configureStore({
	reducer: persistedReducer, // Root reducer for the store
});

/**
 * Persistor instance for persisting the Redux store.
 */
export const persistor = persistStore(store);

/**
 * Type representing the root state of the Redux store.
 */
export type RootState = ReturnType<typeof store.getState>;

export default store;
