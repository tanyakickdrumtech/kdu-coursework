addEventListener("install", (event) => {
	console.log("Service worker installed");
	event.waitUntil(self.skipWaiting());
});

addEventListener("activate", (event) => {
	console.log("Service worker activated");
	event.waitUntil(self.clients.claim());
});

const calculateMaximumProfit = (prices) => {
	let minPrice = prices[0];
	let maxProfit = 0;

	for (let i = 1; i < prices.length; i++) {
		const currentPrice = prices[i];
		const potentialProfit = currentPrice - minPrice;
		maxProfit = Math.max(maxProfit, potentialProfit);
		minPrice = Math.min(minPrice, currentPrice);
	}

	return maxProfit;
};

const findBestBuySellDates = (stockData) => {
	let maxProfit = 0;
	let bestBuyDate = "";
	let bestSellDate = "";

	for (let i = 0; i < stockData.length; i++) {
		for (let j = i + 1; j < stockData.length; j++) {
			const buyPrice = stockData[i];
			const sellPrice = stockData[j];
			const profit = sellPrice - buyPrice;

			if (profit > maxProfit) {
				maxProfit = profit;
				bestBuyDate = stockData[i].date;
				bestSellDate = stockData[j].date;
			}
		}
	}

	return { buyDate: bestBuyDate, sellDate: bestSellDate };
};

addEventListener("message", (event) => {
	if (event.data.type === "getStockSummaries") {
		const stocksData = event.data.stocksData;

		const stockSummaries = stocksData.map((stock) => {
			const maximumProfit = calculateMaximumProfit(stock.prices);
			const { buyDate, sellDate } = findBestBuySellDates(stock.prices);
			const buyPrice = stock.prices.find(
				(price) => price.date === buyDate
			)?.price;
			const sellPrice = stock.prices.find(
				(price) => price.date === sellDate
			)?.price;

			return {
				company: stock.company,
				bestBuyDate: buyDate,
				bestSellDate: sellDate,
				buyPrice,
				sellPrice,
				maximumProfit,
			};
		});

		// eslint-disable-next-line no-undef
		clients.get(event.source.id).then((client) => {
			client.postMessage({ type: "stockSummaries", stockSummaries });
		});
	}
});
