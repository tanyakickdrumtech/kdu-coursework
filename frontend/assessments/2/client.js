document.addEventListener("DOMContentLoaded", function () {
	fetch("/basedata")
		.then((response) => response.json())
		.then((data) => {
			const { name, price } = data;
			document.getElementById("stockName").innerText = name;
			document.getElementById("stockPrice").innerText = price;
		})
		.catch((error) => console.error("Error fetching base data:", error));

	const socket = new WebSocket("ws://localhost:3000");

	socket.addEventListener("message", function (event) {
		const data = JSON.parse(event.data);
		updateGraph(data.price);
	});

	fetch("/history")
		.then((response) => response.json())
		.then((history) => {
			const sideContainer = document.getElementById("sideContainer");
			history.forEach((item) => {
				const listItem = document.createElement("div");
				listItem.textContent = `${item.action}: ${item.quantity}`;
				sideContainer.appendChild(listItem);
			});
		})
		.catch((error) => console.error("Error fetching history:", error));

	function updateGraph(price) {
		console.log("Updating graph with price:", price);
	}
});
