import React, { useEffect, useState } from "react";
import {
	CircularProgress,
	Grid,
	Card,
	CardContent,
	Typography,
} from "@mui/material";

const SummarizerPage = () => {
	const [loading, setLoading] = useState<boolean>(true);
	const [summaryData, setSummaryData] = useState<any[]>([]);

	useEffect(() => {
		fetchSummaryData();
	}, []);

	const fetchSummaryData = async () => {
		try {
			const response = await fetch("/api/summary");
			const data = await response.json();
			setSummaryData(data);
			setLoading(false);
		} catch (error) {
			console.error("Error fetching summary data:", error);
		}
	};

	return (
		<div style={{ padding: "20px" }}>
			<h1>Stock Summarizer</h1>
			{loading ? (
				<CircularProgress />
			) : (
				<Grid container spacing={3}>
					{summaryData.map((stock, index) => (
						<Grid item xs={12} sm={6} md={4} key={index}>
							<Card>
								<CardContent>
									<Typography variant="h5" component="div">
										{stock.company}
									</Typography>
									<Typography variant="body2" color="text.secondary">
										Best Buy Date: {stock.bestBuyDate}, Buy Price:{" "}
										{stock.buyPrice}
									</Typography>
									<Typography variant="body2" color="text.secondary">
										Best Sell Date: {stock.bestSellDate}, Sell Price:{" "}
										{stock.sellPrice}
									</Typography>
									<Typography variant="body2" color="text.secondary">
										Maximum Profit: {stock.maxProfit}
									</Typography>
								</CardContent>
							</Card>
						</Grid>
					))}
				</Grid>
			)}
		</div>
	);
};

export default SummarizerPage;
