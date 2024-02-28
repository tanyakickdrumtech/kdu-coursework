/**
 * Redux slice responsible for managing product-related state.
 */
import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getProducts } from "../thunk/getProducts";
import { Product } from "../pages/ProductListPage";

/**
 * Interface representing the product state.
 */
interface ProductType {
	products: Product[];
	loading: boolean;
	error?: string;
}

/**
 * Initial state for the product slice.
 */
const initialState: ProductType = {
	products: [],
	loading: false,
};

/**
 * ProductSlice represents the Redux slice for managing product state.
 */
// eslint-disable-next-line react-refresh/only-export-components
const ProductSlice = createSlice({
	name: "products",
	initialState,
	reducers: {},
	extraReducers(builder) {
		builder
			.addCase(getProducts.pending, (state) => {
				state.loading = true;
				state.error = "";
			})
			.addCase(
				getProducts.fulfilled,
				(state, action: PayloadAction<Product[]>) => {
					state.loading = false;
					state.products = action.payload;
					state.error = "";
				}
			);
	},
});

export default ProductSlice.reducer;
