import React from "react";

/**
 * Interface defining the properties of a product.
 */
interface Product {
	id: number;
	title: string;
	image: string;
	price: number;
}

/**
 * Props interface for the ProductCard component.
 */
interface ProductCardProps {
	/** The product to display in the card. */
	product: Product;
}

/**
 * Functional component representing a product card.
 * @param {ProductCardProps} props - The props object containing the product data.
 * @returns JSX elements representing the product card.
 */
export const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
	// Define CSS styles for the product card
	const cardStyle: React.CSSProperties = {
		width: "17rem",
		height: "20rem",
		cursor: "pointer",
		transition: "transform 0.3s ease",
		display: "inline-block",
		backgroundColor: "#ffffff",
	};

	const imageStyle: React.CSSProperties = {
		width: "12rem",
		height: "12rem",
		margin: "2rem",
	};

	const infoStyle: React.CSSProperties = {
		display: "flex",
		flexDirection: "row",
		justifyContent: "space-around",
	};

	const priceFont: React.CSSProperties = {
		color: "#2a2a72",
		fontWeight: 600,
	};

	return (
		<div style={cardStyle}>
			<img src={product.image} alt={product.title} style={imageStyle} />
			<div style={infoStyle}>
				<h3>{product.title}</h3>
				<p style={priceFont}>${product.price}</p>
			</div>
		</div>
	);
};
