import { createSlice } from "@reduxjs/toolkit";
import { FetchRoomDetails } from "../thunk/FetchRoomDetails";

interface AddOn {
	name: string;
	cost: string;
	currency: string;
}

interface RoomDetails {
	id: number;
	name: string;
	costPerNight: string;
	currency: string;
	addOns: AddOn[];
}

interface RoomState {
	roomDetails: RoomDetails | null;
	loading: boolean;
	error?: string | null;
}

const initialState: RoomState = {
	roomDetails: null,
	loading: false,
};

const roomSlice = createSlice({
	name: "room",
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder
			.addCase(FetchRoomDetails.pending, (state) => {
				state.loading = true;
				state.error = null;
			})
			.addCase(FetchRoomDetails.fulfilled, (state, action) => {
				state.loading = false;
				state.roomDetails = action.payload;
			})
			.addCase(FetchRoomDetails.rejected, (state, action) => {
				state.loading = false;
				state.error = action.error.message || "Failed to fetch room details";
			});
	},
});

export default roomSlice.reducer;
