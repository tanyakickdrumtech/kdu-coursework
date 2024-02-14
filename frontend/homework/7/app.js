//npm init -y
//npm install express cors
//npm install -D nodemon
//npm install socket.io

const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
	cors: {
		origin: "http://127.0.0.1:5500",
	},
});

app.use(cors());

app.use(express.json());

app.get("/", (req, res) => {
	res.json({
		msg: "Hello World",
	});
});

io.on("connection", (socket) => {
	console.log("Connection created");

	socket.on("message", (payload) => {
		console.log("Payload", payload);
		io.except(socket.id).emit("new-message", payload);
	});
});

server.listen(3000, () => {
	console.log("Listening on port 3000");
});
