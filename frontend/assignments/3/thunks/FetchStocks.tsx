import { createAsyncThunk } from "@reduxjs/toolkit";

/**
 * Interface representing the structure of a stock object.
 */
export interface Stock {
	stock_name: string;
	stock_symbol: string;
	base_price: number;
}

/**
 * Asynchronous thunk action creator for fetching stocks from an external API.
 * It makes an HTTP GET request to the specified URL and retrieves stock data.
 * @returns A promise containing an array of stock objects upon successful fetch.
 * @throws An error if the fetch operation fails.
 */
export const fetchStocks = createAsyncThunk("fetchStocks", async () => {
	try {
		const response = await fetch(
			"https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"
		);
		if (!response.ok) {
			throw new Error("Failed to fetch stocks");
		}
		const data = await response.json();
		return data;
	} catch (error) {
		throw new Error("Failed to fetch stocks");
	}
});
