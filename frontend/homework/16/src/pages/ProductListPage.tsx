/**
 * ProductListPage component displays a list of products with filtering and sorting options.
 * It fetches product data from the context and allows users to search, filter, and sort products.
 * @returns JSX element representing the ProductListPage component.
 */
import React, { useContext, useEffect, useRef, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { ProductCard } from "../components/ProductCard";
import { ProductContext } from "../contexts/ProductContextProvider";
import searchIcon from "../assets/search-icon.svg";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import CircularProgress from "@mui/material/CircularProgress";
import { useDispatch } from "react-redux";
import { clearError } from "../redux/SnackBarSlice";
import { getProducts } from "../thunk/getProducts";
import { AppDispatch } from "../redux/store";

/**
 * Interface for product object.
 */
export interface Product {
	id: number;
	title: string;
	category: string;
	price: number;
}

/**
 * ProductListPage component displays a list of products with filtering and sorting options.
 */
export const ProductListPage = () => {
	const [searchTerm, setSearchTerm] = useState<string>("");
	const [sortOrder, setSortOrder] = useState<string>("asc");
	const [categories, setCategories] = useState<string[]>([]);
	const [selectedCategory, setSelectedCategory] = useState<string>("all");
	const searchRef = useRef<HTMLInputElement>(null);
	const productContext = useContext(ProductContext);
	const location = useLocation();

	const [open, setOpen] = useState(false);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState<string | null>(null);

	const dispatch: AppDispatch = useDispatch();

	/**
	 * Function to handle closing of snackbar.
	 */
	const handleClose = () => {
		setOpen(false);
		dispatch(clearError());
	};

	useEffect(() => {
		const queryParams = new URLSearchParams(location.search);
		const category = queryParams.get("category") || "all";
		const searchTerm = queryParams.get("searchTerm") || "";
		const sortOrder = queryParams.get("sortOrder") || "asc";

		setSelectedCategory(category);
		setSearchTerm(searchTerm);
		setSortOrder(sortOrder);
	}, [location.search]);

	useEffect(() => {
		if (searchRef.current) {
			const timeoutId = setTimeout(() => {
				setSearchTerm(searchRef.current!.value);
			}, 500);
			return () => clearTimeout(timeoutId);
		}
	}, []);

	useEffect(() => {
		const getData = async () => {
			try {
				const action = await dispatch(getProducts());
				const products: Product[] = action.payload as Product[];
				const uniqueCategories = [
					...new Set(products.map((product: Product) => product.category)),
				];
				setOpen(true);
				setCategories(uniqueCategories);
				setLoading(false);
			} catch (error) {
				console.error("Error fetching products", error);
				setError("Error fetching products");
				setOpen(true);
				setLoading(false);
			}
		};
		getData();
	}, [dispatch, setCategories]);

	const products = productContext?.products || [];

	const filteredByCategory =
		selectedCategory === "all"
			? products
			: products.filter((product) => product.category === selectedCategory);

	const filteredBySearch = filteredByCategory.filter((product: Product) =>
		product.title.toLowerCase().includes(searchTerm.toLowerCase())
	);

	const sortedProducts = [...filteredBySearch].sort((a, b) => {
		if (sortOrder === "asc") {
			return a.price - b.price;
		} else {
			return b.price - a.price;
		}
	});

	/**
	 * Function to handle search button click.
	 */
	const handleSearchClick = () => {
		if (searchRef.current) {
			setSearchTerm(searchRef.current.value);
		}
	};

	const containerStyle = {
		backgroundColor: "#f3f3f3",
		position: "relative",
	};

	const snackbarStyle = {
		position: "fixed",
		bottom: "20px", // Adjust the bottom distance as needed
		left: "50%",
		transform: "translateX(-50%)",
	};

	const headerStyle = {
		display: "flex",
		alignItems: "center",
		backgroundColor: "#2a2a72",
		height: "35px",
	};

	const inputStyle = {
		marginTop: "0.3rem",
		marginLeft: "7rem",
	};

	const buttonStyle = {
		marginTop: "0.25rem",
		width: "28px",
		height: "20px",
	};

	const filterTextStyle = {
		color: "#ffffff",
		marginLeft: "40rem",
		marginRight: "1rem",
	};

	const selectStyle = {
		color: "#ffffff",
		marginLeft: "1.5rem",
		marginRight: "1rem",
	};

	const productListStyle = {
		display: "grid",
		gridTemplateColumns: "repeat(4, 1fr)",
		gap: "20px",
		padding: "20px",
		marginLeft: "5rem",
		marginTop: "0.5rem",
		marginRight: "5rem",
	};

	const loaderStyle = {
		position: "absolute",
		top: "50%",
		left: "50%",
		transform: "translate(-50%, -50%)",
	};

	return (
		<div style={containerStyle}>
			<div style={headerStyle}>
				<input
					style={inputStyle}
					type="text"
					ref={searchRef}
					placeholder="Search..."
					defaultValue={searchTerm}
				/>
				<button style={buttonStyle} onClick={handleSearchClick}>
					<img
						src={searchIcon}
						alt="Search"
						style={{ width: "15px", height: "15px", cursor: "pointer" }}
					/>
				</button>{" "}
				<p style={filterTextStyle}>Filter:</p>
				<select
					onChange={(e) => {
						setSelectedCategory(e.target.value);
					}}
					value={selectedCategory}
				>
					<option value="all">All</option>
					{categories.map((category) => (
						<option key={category} value={category}>
							{category}
						</option>
					))}
				</select>
				<p style={selectStyle}>Sort:</p>
				<select
					onChange={(e) => {
						setSortOrder(e.target.value);
					}}
					value={sortOrder}
				>
					<option value="asc">Price</option>
					<option value="asc">Ascending</option>
					<option value="desc">Descending</option>
				</select>
			</div>
			<h1
				style={{
					marginTop: "2rem",
					color: "#2a2a72",
					textAlign: "center",
					fontSize: "40px",
					fontWeight: 600,
				}}
			>
				KDU MARKETPLACE
			</h1>
			{loading ? (
				<div style={loaderStyle}>
					<CircularProgress size={100} />
				</div>
			) : error ? (
				<Snackbar
					open={open}
					autoHideDuration={6000}
					onClose={handleClose}
					style={snackbarStyle}
				>
					<Alert
						onClose={handleClose}
						severity="error"
						variant="filled"
						sx={{ width: "100%" }}
					>
						{error}
					</Alert>
				</Snackbar>
			) : (
				<div style={productListStyle}>
					{sortedProducts.slice(0, 8).map((product) => (
						<Link
							style={{ textDecoration: "none", color: "black" }}
							key={product.id}
							to={`/product/${product.id}`}
						>
							<ProductCard product={product} />
						</Link>
					))}
				</div>
			)}
			{!loading && !error && (
				<Snackbar
					open={open}
					autoHideDuration={6000}
					onClose={handleClose}
					style={snackbarStyle}
				>
					<Alert
						onClose={handleClose}
						severity="success"
						variant="filled"
						sx={{ width: "100%" }}
					>
						Data From the Api is loaded successfully
					</Alert>
				</Snackbar>
			)}
		</div>
	);
};
