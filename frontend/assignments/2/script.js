/**
 * Establishes a WebSocket connection to the server.
 * The socket connects to "http://localhost:3000".
 */
const socket = io("http://localhost:3000");
/**
 * Sends a login request to the server when the user opens the home page.
 * Retrieves the username and password from the input fields,
 * sends the login request to the server,
 * and redirects the user to the home page upon successful login.
 * Displays an error message if the login fails.
 */
async function openHomePage() {
	// Get the username and password from the input fields
	const username = document.getElementById("username").value;
	const password = document.getElementById("password").value;
	try {
		// Send a POST request to the server to log in the user
		const response = await fetch("http://localhost:3000/api/user/login", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({ username, password }),
		});

		const data = await response.json();
		if (response.ok) {
			window.location.href = "/index.html";
			socket.emit("login", { username });
		} else {
			document.getElementById("message").innerText = data.message;
		}
	} catch (error) {
		console.error("Error logging in:", error.message);
	}
}
/**
 * Loads additional posts from the server and appends them to the DOM.
 * This function is triggered when the user scrolls to the bottom of the page.
 * It sends a request to the server to fetch more posts based on the current page and page size.
 * The loaded posts are then appended to the "posts-container" element.
 */

let page = 1;
const pageSize = 5;
let isLoading = false;

window.onload = function () {
	loadPosts();
	window.addEventListener("scroll", handleScroll);
};

/**
 * Loads additional posts from the server and appends them to the DOM.
 * This function is triggered when the user scrolls to the bottom of the page.
 * It sends a request to the server to fetch more posts based on the current page and page size.
 * The loaded posts are then appended to the "posts-container" element.
 */
async function loadPosts() {
	if (isLoading) return;
	isLoading = true;
	const response = await fetch(
		`http://localhost:3000/api/posts?page=${page}&pageSize=${pageSize}`
	);
	const data = await response.json();
	// console.log(data);
	const postsContainer = document.getElementById("posts-container");

	data.forEach((post) => {
		const postElement = document.createElement("div");
		postElement.classList.add("post");
		if (post.content) {
			postElement.innerHTML = `
            <div class="post-text">
        <div class="post-header">
        <div class="profile-icon">
            <img src="images/profile pic.png" alt="profile icon" />
        </div>
        <div class="user">Nitesh Gupta</div>
        <div class="username">@nit_hck</div>
        <div class="dot">.</div>
        <div class="time">1s</div>
        <div>
            <img id="more-icon" src="images/more.png" alt="more" />
        </div>
    </div>
    <div class="post-tweet">
                            ${post.content}</span>
                        </div>
                        
                        <div class="post-images">
                            <img id="comment-icon" src="images/comment.png" alt="comment" />
                            <img id="retweet-icon" src="images/retweet.png" alt="retweet" />
                            <img id="like-icon" src="images/like.png" alt="like" />
                            <img id="poll-icon" src="images/poll.png" alt="poll" />
                            <img id="bookmark-icon" src="images/bookmark.png" alt="bookmark" />
                            <img id="upload-icon" src="images/upload.png" alt="upload" />
                        </div>
                        </div>
        `;
			postsContainer.appendChild(postElement);
		} else if (post.imageUrl) {
			postElement.innerHTML = `
            <div class="post-type-image">
                        <div class="post-header">
                            <div class="profile-icon">
                                <img src="images/profile pic.png" alt="profile icon" />
                            </div>
                            <div class="user">Nitesh Gupta</div>
                            <div class="username">@nit_hck</div>
                            <div class="dot">.</div>
                            <div class="time">25s</div>
                            <div>
                                <img id="more-icon" src="images/more.png" alt="more" />
                            </div>
                        </div>
                        <div class="post-tweet-image">
                            <img
                            class="tweet-image"
                            src="${post.imageUrl}"
                            alt="post-tweet-image"
                            />
                        </div>
                        
                        <div class="post-images">
                            <img id="comment-icon" src="images/comment.png" alt="comment" />
                            <img id="retweet-icon" src="images/retweet.png" alt="retweet" />
                            <img id="like-icon" src="images/like.png" alt="like"/>
                            <img id="poll-icon" src="images/poll.png" alt="poll" />
                            <img id="bookmark-icon" src="images/bookmark.png" alt="bookmark" />
                            <img id="upload-icon" src="images/upload.png" alt="upload" />
                        </div>
                    </div>
                    `;
			postsContainer.appendChild(postElement);
		} else if (post.videoUrl) {
			postElement.innerHTML = `
            <div class="post-type-video">
                        <div class="post-header">
                            <div class="profile-icon">
                                <img src="images/profile pic.png" alt="profile icon" />
                            </div>
                            <div class="user">Nitesh Gupta</div>
                            <div class="username">@nit_hck</div>
                            <div class="dot">.</div>
                            <div class="time">34s</div>
                            <div>
                                <img id="more-icon" src="images/more.png" alt="more" />
                            </div>
                        </div>
                        <div class="post-tweet-video">
                            <video width="530" height="220" controls>
                                <source src="movie.mp4" type="video/mp4" />
                                Your browser does not support the video tag.
                            </video>
                        </div>
                        
                        <div class="post-images">
                            <img id="comment-icon" src="images/comment.png" alt="comment" />
                            <img id="retweet-icon" src="images/retweet.png" alt="retweet" />
                            <img id="like-icon" src="images/like.png" alt="like" />
                            <img id="poll-icon" src="images/poll.png" alt="poll" />
                            <img id="bookmark-icon" src="images/bookmark.png" alt="bookmark" />
                            <img id="upload-icon" src="images/upload.png" alt="upload" />
                        </div>
                    </div>
            `;
			postsContainer.appendChild(postElement);
		}
	});

	page++;
	isLoading = false;
}

/**
 * Handles the scroll event and triggers the loading of more posts when the user scrolls to the bottom of the page.
 * This function is attached to the window's scroll event listener.
 */
function handleScroll() {
	if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
		loadPosts();
	}
}

/**
 * Creates a new post based on the user's input and sends it to the server.
 * This function is called when the user clicks the "Post" button.
 * It retrieves the content of the new post from the input field,
 * sends a POST request to the server to create the post,
 * and reloads the page to display the updated list of posts.
 */
async function createPost() {
	const newPostInput = document.getElementById("new-post-input").value;
	if (!newPostInput) {
		alert("Please enter something to post.");
		return;
	}

	try {
		const response = await fetch("http://localhost:3000/api/posts", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({ content: newPostInput }),
		});

		if (response.ok) {
			console.log("reloading");
			// If post creation is successful, reload the page to fetch updated posts
			location.reload();
		} else {
			const errorMessage = await response.text();
			alert("Error creating post: " + errorMessage);
		}
	} catch (error) {
		console.error("Error creating post:", error.message);
		alert("Error creating post. Please try again later.");
	}
}

/**
 * Toggles between the home page and the message page.
 * This function is called when the user clicks the "Message" button.
 * It hides the home page and displays the message page, or vice versa.
 */
function toggleMessagePage() {
	const homepage = document.querySelector(".tweet-box");
	const messagePage = document.querySelector(".message-page");

	if (homepage.style.display !== "none") {
		homepage.style.display = "none";

		messagePage.style.display = "flex";
		messagePage.style.flexDirection = "row";
		messagePage.style.justifyContent = "space-between";
		messagePage.style.color = "white";
	} else {
		homepage.style.display = "block";
		messagePage.style.display = "none";
	}
}

socket.on("message", (message) => {
	console.log(message);
	outputMsg(message);
});

function outputMsg(message) {
	console.log("appending message", message);
	const chatMessages = document.getElementById("chat-messages");
	const messageElement = document.createElement("div");
	messageElement.textContent = message;
	chatMessages.appendChild(messageElement);
}

document.getElementById("send-button").addEventListener("click", () => {
	const messageInput = document.getElementById("message-input");
	const message = messageInput.value.trim();

	socket.emit("chatMsg", message);
});

socket.on("receiveMessage", (data) => {
	console.log("Received message:", data);
	const chatMessages = document.getElementById("chat-messages");
	const messageElement = document.createElement("div");
	messageElement.textContent = data;
	chatMessages.appendChild(messageElement);
});

socket.on("activeUsersUpdate", (activeUsers) => {
	const activeUsersList = document.getElementById("active-users-list");
	activeUsersList.innerHTML = "";
	activeUsers.forEach((user) => {
		const userItem = document.createElement("ul");

		userItem.innerHTML = `<div style="display:flex;flex-direction:row;padding-left:-1rem">
			<div>
			<img style="width:40px; border-radius: 50%"  src="images/profile pic.png" alt="profile icon" />
			</div>
			<div style="display:flex;flex-direction:row; padding-left:1rem; padding-top:.7rem">
				<div class="user">${user.username}</div>
				<div style="padding-left:.5rem;color:gray">@${user.username}123</div>
			</div>
		</div>
		
	`;
		userItem.classList.add("active-user");
		activeUsersList.appendChild(userItem);
	});
});

socket.emit("getActiveUsers");
