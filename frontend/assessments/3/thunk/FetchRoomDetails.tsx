import { createAsyncThunk } from "@reduxjs/toolkit";

/**
 * Async thunk action creator to fetch products from an API.
 * @returns Promise containing an array of Product objects.
 */
export const FetchRoomDetails = createAsyncThunk(
	"FetchRoomDetails",
	async () => {
		try {
			const response = await fetch(
				"https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
			);
			const data = await response.json();
			return data.roomTypes;
		} catch {
			return "Error while making API Call";
		}
	}
);
