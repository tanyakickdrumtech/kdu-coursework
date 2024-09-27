/**
 * Establishes a WebSocket connection upon user login.
 * If the login is successful, redirects the user to the home page.
 * If the login fails, displays an error message.
 */
const socket = io("http://localhost:3000");

/**
 * Handles the login process when the user submits the login form.
 * Retrieves the username and password from the form,
 * sends a login request to the server, and processes the response.
 */
async function openHomePage() {
	/** @type {HTMLInputElement} */
	const usernameInput = document.getElementById("username");
	/** @type {HTMLInputElement} */
	const passwordInput = document.getElementById("password");

	const username = usernameInput.value;
	const password = passwordInput.value;

	try {
		const response = await fetch("http://localhost:3000/api/user/login", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({ username, password }),
		});

		const data = await response.json();
		console.log(data);

		if (response.ok) {
			// Store username in session storage
			sessionStorage.setItem("username", username);

			// Redirect user to index.html
			window.location.href = "/index.html";

			// Emit "login" event to the WebSocket server
			socket.emit("login", { username });
		} else {
			// Display error message
			document.getElementById("message").innerText = data.message;
		}
	} catch (error) {
		console.error("Error logging in:", error.message);
	}
}

/**
 * Retrieves the stored username from session storage,
 * emits a "login" event with the username to establish a WebSocket connection.
 */
const storedUsername = sessionStorage.getItem("username");
if (storedUsername) {
	// Emit "login" event to the WebSocket server
	socket.emit("login", { username: storedUsername });

	// Optional: Remove stored username after emitting "login" event
	sessionStorage.removeItem("username");
}
