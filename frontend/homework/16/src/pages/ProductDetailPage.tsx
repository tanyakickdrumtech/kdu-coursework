import { useContext } from "react";
import { useParams, Link } from "react-router-dom";
import { ProductContext } from "../contexts/ProductContextProvider";
import { Header } from "../components/Header";

/**
 * Interface for the parameters extracted from the URL.
 */
interface ParamTypes {
	id: string;
	[key: string]: string | undefined;
}

/**
 * Component for rendering the details of a product.
 * @returns JSX element representing the product detail page.
 */
export const ProductDetailPage = () => {
	const { id } = useParams<ParamTypes>(); // Get the product id from the URL params
	const productContext = useContext(ProductContext); // Get the product context
	const products = productContext?.products || []; // Retrieve the products from the context
	const product = products.find((prod) => prod.id === parseInt(id!, 10)); // Find the product by id

	// If product not found, display error message
	if (!product) {
		return <div>Product not found</div>;
	}

	// Styles for the component
	const styles = {
		container: {
			marginLeft: "10rem",
			display: "flex",
			alignItems: "center",
			justifyContent: "center",
			minHeight: "calc(100vh - 70px)",
			backgroundColor: "#f3f3f3",
			padding: "20px",
		},
		imageContainer: {
			marginRight: "20px",
		},
		image: {
			maxWidth: "300px",
			backgroundColor: "#f3f3f3",
			padding: "10px",
		},
		contentContainer: {
			marginLeft: "12rem",
		},
		priceContainer: {
			display: "flex",
			alignItems: "center",
			marginTop: "0.8rem",
			marginBottom: "0.8rem",
			color: "#272261",
			fontWeight: 600,
			fontSize: "20px",
		},
		titleStyle: {
			marginLeft: "0",
			fontWeight: 600,
			fontSize: "35px",
			color: "#2a2a72",
		},
		productDescription: {
			fontWeight: 600,
			marginTop: "0.7rem",
			marginRight: "8rem",
		},
		productDescriptionSub: {
			marginTop: "0.7rem",
			marginRight: "8rem",
			marginBottom: "0.8rem",
			color: "gray",
		},
		backButton: {
			marginTop: "1rem",
			padding: "2px 8px",
			cursor: "pointer",
			color: "#66b2e4",
			textDecoration: "none",
			fontWeight: 600,
			border: "1px solid #66b2e4",
			borderRadius: "5px",
		},
	};

	// JSX to render the product detail page
	return (
		<>
			<Header onSearch={() => {}} />
			<div style={{ backgroundColor: "#f3f3f3" }}>
				<div style={styles.container}>
					<div style={styles.imageContainer}>
						<img src={product.image} alt={product.title} style={styles.image} />
					</div>
					<div style={styles.contentContainer}>
						<h1 style={styles.titleStyle}>{product.title}</h1>
						<div style={styles.priceContainer}>
							<h1>Price:</h1>
							<h2>${product.price}</h2>
						</div>
						<h1 style={styles.productDescription}>Product Description:</h1>{" "}
						<p style={styles.productDescriptionSub}>{product.description}</p>
						<Link to="/" style={styles.backButton}>
							Back to Products
						</Link>
					</div>
				</div>
			</div>
		</>
	);
};
