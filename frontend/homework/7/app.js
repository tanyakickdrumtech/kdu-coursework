const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");

/**
 * Express application
 * @type {express.Application}
 */
const app = express();
const server = http.createServer(app);

/**
 * Socket.io server
 * @type {socketIo.Server}
 */
const io = new socketIo.Server(server, {
	cors: {
		origin: "http://127.0.0.1:5500",
	},
});

// Allow cross-origin requests
app.use(cors());

// Parse JSON request bodies
app.use(express.json());

/**
 * Default route handler
 * @param {express.Request} req - Express request object
 * @param {express.Response} res - Express response object
 */
app.get("/", (req, res) => {
	res.json({
		msg: "Hello World",
	});
});

/**
 * Event handler for new socket connections
 * @param {socketIo.Socket} socket - Socket.io socket object
 */
io.on("connection", (socket) => {
	console.log("Connection created");

	/**
	 * Event handler for incoming messages
	 * @param {Object} payload - The message payload
	 * @param {string} payload.message - The message content
	 * @param {string} payload.sender - The sender of the message
	 */
	socket.on("message", (payload) => {
		console.log("Payload", payload);
		io.except(socket.id).emit("new-message", payload);
	});
});

/**
 * Start the server
 * @param {number} port - The port number to listen on
 * @param {function} callback - Optional callback function to execute once the server starts
 */
server.listen(3000, () => {
	console.log("Listening on port 3000");
});
