const express = require("express");
const http = require("http");
const WebSocket = require("ws");

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

const PORT = 3000;

let stockData = {
	name: "XYZ",
	price: 100,
};

let history = [];

function generatePriceChange() {
	return Math.floor(Math.random() * 501); 
}

wss.on("connection", function connection(ws) {
	console.log("Client connected");
	
	ws.send(JSON.stringify(stockData));
	setInterval(() => {
		const change = generatePriceChange();
		stockData.price += Math.random() < 0.5 ? -change : change;
		const update = { price: stockData.price, change: change };
		ws.send(JSON.stringify(update));
		history.push(update);
	}, 5000);
});

app.post("/buy", (req, res) => {
	const quantity = req.body.quantity;
	if (quantity > 0 && quantity <= 10) {
		history.push({ action: "buy", quantity: quantity });
		res.json({
			success: true,
			message: `Successfully bought ${quantity} stocks`,
		});
	} else {
		res.status(400).json({ success: false, message: "Invalid quantity" });
	}
});

app.post("/sell", (req, res) => {
	const quantity = req.body.quantity;
	if (quantity > 0 && quantity <= 10) {
		history.push({ action: "sell", quantity: quantity });
		res.json({
			success: true,
			message: `Successfully sold ${quantity} stocks`,
		});
	} else {
		res.status(400).json({ success: false, message: "Invalid quantity" });
	}
});
app.get("/history", (req, res) => {
	res.json(history);
});

app.get("/basedata", (req, res) => {
	res.json(stockData);
});

app.use(express.static("public"));

server.listen(PORT, function () {
	console.log(`Server is running on port ${PORT}`);
});
