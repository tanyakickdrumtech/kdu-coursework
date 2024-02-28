/**
 * Redux slice responsible for managing error-related state.
 */
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

/**
 * Interface representing the error state.
 */
interface ErrorType {
	message: string | null;
}

/**
 * Initial state for the error slice.
 */
const initialState: ErrorType = {
	message: null,
};

/**
 * ErrorSlice represents the Redux slice for managing error state.
 */
const errorSlice = createSlice({
	name: "error",
	initialState,
	/**
	 * Reducers for handling error-related actions.
	 */
	reducers: {
		/**
		 * Action creator for setting an error message.
		 * @param state - Current state of the error slice.
		 * @param action - Payload action containing the error message.
		 */
		setError(state, action: PayloadAction<string>) {
			state.message = action.payload;
		},
		/**
		 * Action creator for clearing the error message.
		 * @param state - Current state of the error slice.
		 */
		clearError(state) {
			state.message = null;
		},
	},
});

/**
 * Exporting action creators for setting and clearing error messages.
 */
export const { setError, clearError } = errorSlice.actions;

/**
 * Exporting the reducer function for the error slice.
 */
export default errorSlice.reducer;
