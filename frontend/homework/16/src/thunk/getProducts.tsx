import { createAsyncThunk } from "@reduxjs/toolkit";
import { Product } from "../pages/ProductListPage";

/**
 * Async thunk action creator to fetch products from an API.
 * @returns Promise containing an array of Product objects.
 */
export const getProducts = createAsyncThunk<Product[]>(
	"getProducts",
	async () => {
		try {
			const response = await fetch("https://fakestoreapi.com/products");
			const data = await response.json();
			return data;
		} catch {
			return "Error while making API Call";
		}
	}
);
