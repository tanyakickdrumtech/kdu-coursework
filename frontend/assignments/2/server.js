const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const path = require("path");
const socketIo = require("socket.io");
const http = require("http");
const app = express();
const PORT = process.env.PORT || 3000;
const HTTP_PORT = process.env.HTTP_PORT || 3000;
const WS_PORT = process.env.WS_PORT || 3001;

const server = http.createServer(app);
/**
 * WebSocket server instance.
 *
 * Establishes WebSocket connections for real-time communication.
 */
const io = new socketIo.Server(server, {
	cors: {
		origin: "http://127.0.0.1:5502",
	},
});
app.use(bodyParser.json());
app.use(cors());

/**
 * Array of user objects containing user data.
 */
const users = [
	{
		id: "1",
		user_name: "Sagun Sangwaan",
		user_email_id: "sagun.sangwan@kickdrumtech.com",
		password: "sagun123",
		profile_url: "",
	},
	{
		id: "2",
		user_name: "aakashKDU",
		user_email_id: "aakash.tyagi@kickdrumtech.com",
		password: "aakash",
		profile_url: "",
	},
	{
		id: "3",
		user_name: "Nitesh Gupta",
		user_email_id: "nitesh.gupta@kickdrumtech.com",
		password: "nitesh123",
		profile_url: "",
	},
	{
		id: "4",
		user_name: "anupamKDU",
		user_email_id: "anupam.srivastava@kickdrumtech.com",
		password: "anupam",
		profile_url: "",
	},
	{
		id: "5",
		user_name: "rishavKDU",
		user_email_id: "rishav.chakroborty@kickdrumtech.com",
		password: "rishav",
		profile_url: "",
	},
];

/**
 * Array of active users currently connected to the WebSocket server.
 */
let activeUsers = [];
/**
 * Array of objects containing usernames and corresponding socket IDs.
 */
let recipientSocketIds = [];

try {
	/**
	 * Event listener for WebSocket connections.
	 */
	io.on("connection", (socket) => {
		console.log("New client connected");

		console.log("message", "Welcome!!");

		console.log("message", "A user has joined!");

		/**
		 * Event listener for receiving chat messages.
		 */
		socket.on("chatMsg", (message) => {
			console.log(message);
			io.emit("message", message);
		});
		/**
		 * Event listener for retrieving active users.
		 */

		socket.on("getActiveUsers", () => {
			io.emit("activeUsersUpdate", activeUsers);
		});
		/**
		 * Event listener for user login.
		 */
		socket.on("login", (userData) => {
			console.log("inside login");
			console.log(userData);
			activeUsers.push(userData);
			recipientSocketIds.push({
				username: userData.username,
				socketId: socket.id,
			});
			console.log("calling activeUsersUpdate");
			io.emit("activeUsersUpdate", activeUsers);
		});
	});
} catch (error) {
	console.error("Error here:", error.message);
}
/**
 * Endpoint for user login.
 *
 * Validates user credentials and returns success message upon successful login.
 */

app.post("/api/user/login", (req, res) => {
	const { username, password } = req.body;
	const user = users.find(
		(user) => user.user_name === username && user.password === password
	);
	if (user) {
		res.json({ success: true, message: "Login successful" });
	} else {
		res
			.status(401)
			.json({ success: false, message: "Invalid username or password" });
	}
});

/**
 * Array of post objects containing post data.
 */
const posts = [];
posts.push({
	id: 1,
	title: `Post 1`,
	content: `Coffee in hand,bugs beware.Time to crush some code.<span
    style="color: rgb(30, 94, 197)"
    >#DeveloperLife <br />
    #Coding</span
    >`,
});
posts.push({
	id: 2,
	title: `Post 2`,
	imageUrl: "images/image-tweet.png",
});
posts.push({
	id: 3,
	title: `Post 3`,
	videoUrl: `nkjkj`,
});
for (let i = 1; i <= 10; i++) {
	posts.push({
		id: i,
		title: `Post ${i}`,
		imageUrl: `https://via.placeholder.com/300x200?text=Post+${i}`,
	});
}
/**
 * Endpoint for retrieving paginated posts.
 *
 * Returns a subset of posts based on the specified page and page size.
 */
app.get("/api/posts", (req, res) => {
	const page = parseInt(req.query.page) || 1;
	const pageSize = parseInt(req.query.pageSize) || 5;

	const startIndex = (page - 1) * pageSize;
	const endIndex = startIndex + pageSize;

	const paginatedPosts = posts.slice(startIndex, endIndex);

	res.json(paginatedPosts);
});

/**
 * Endpoint for creating a new post.
 *
 * Adds a new post to the list of posts and returns the newly created post.
 */

app.post("/api/posts", (req, res) => {
	const { content } = req.body;
	if (!content) {
		return res.status(400).send("Post content is required.");
	}
	const newPost = {
		id: posts.length + 1,
		content,
	};
	posts.unshift(newPost);

	res.json(newPost);
	console.log(posts);
});
/**
 * Starts the HTTP server.
 *
 * Listens for HTTP requests on the specified port.
 */

server.listen(HTTP_PORT, () => {
	console.log(`HTTP Server is running on port ${HTTP_PORT}`);
	console.log(`WebSocket Server is running on port ${WS_PORT}`);
});
