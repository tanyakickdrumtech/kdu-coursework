/**
 * ProductListPage component displays a list of products with filtering and sorting options.
 */
import React, { useContext, useEffect, useRef, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { ProductCard } from "../components/ProductCard";
import { ProductContext } from "../contexts/ProductContextProvider";
import searchIcon from "../assets/search-icon.svg";

interface Product {
	id: number;
	title: string;
	category: string;
	price: number;
}

/**
 * ProductListPage component displays a list of products with filtering and sorting options.
 * It fetches product data from the context and allows users to search, filter, and sort products.
 * @returns JSX element representing the ProductListPage component.
 */
export const ProductListPage = () => {
	const [searchTerm, setSearchTerm] = useState<string>("");
	const [sortOrder, setSortOrder] = useState<string>("asc");
	const [categories, setCategories] = useState<string[]>([]);
	const [selectedCategory, setSelectedCategory] = useState<string>("all");
	const searchRef = useRef<HTMLInputElement>(null);
	const productContext = useContext(ProductContext);
	const location = useLocation();

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
		fetch("https://fakestoreapi.com/products")
			.then((response) => response.json())
			.then((data: Product[]) => {
				const uniqueCategories = [
					...new Set(data.map((product) => product.category)),
				];
				setCategories(uniqueCategories);
			})
			.catch((error) => console.error("Error fetching categories", error));
	}, []);

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

	const handleSearchClick = () => {
		if (searchRef.current) {
			setSearchTerm(searchRef.current.value);
		}
	};

	const containerStyle = {
		backgroundColor: "#f3f3f3",
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
		</div>
	);
};
