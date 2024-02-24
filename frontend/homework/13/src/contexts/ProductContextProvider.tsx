import React, { createContext, useEffect, useState, ReactNode } from "react";

/**
 * Interface defining the structure of a product.
 */
interface Product {
	category: string;
	brand: string;
	description: ReactNode;
	id: number;
	title: string;
	image: string;
	price: number;
}

/**
 * Interface defining the context type for the ProductContext.
 */
interface ProductContextType {
	/** An array of products. */
	products: Product[];
}

/**
 * Context for managing product data.
 */
export const ProductContext = createContext<ProductContextType | undefined>(
	undefined
);

/**
 * Props interface for the ProductContextProvider component.
 */
interface ProductContextProviderProps {
	/** The child components to be wrapped by the provider. */
	children: ReactNode;
}

/**
 * Provider component for the ProductContext.
 * @param {ProductContextProviderProps} props - The props object containing children.
 * @returns JSX element representing the product context provider.
 */
export const ProductContextProvider: React.FC<ProductContextProviderProps> = ({
	children,
}) => {
	const [products, setProducts] = useState<Product[]>([]);

	useEffect(() => {
		fetch("https://fakestoreapi.com/products")
			.then((response) => response.json())
			.then((data: Product[]) => setProducts(data))
			.catch((error) => console.error("Error fetching products", error));
	}, []);

	return (
		<ProductContext.Provider value={{ products }}>
			{children}
		</ProductContext.Provider>
	);
};
