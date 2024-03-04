import { configureStore } from "@reduxjs/toolkit";
import roomReducer from "./roomSlice";

const store = configureStore({
	reducer: {
		room: roomReducer,
	},
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
