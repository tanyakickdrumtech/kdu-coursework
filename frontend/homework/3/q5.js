// give all the keys and values pair
const player = {
	firstName: "Leo",

	lastName: "Messi",

	address: {
		country: "Spain",

		city: "Barcelona",
	},

	careerInfo: {
		fcBarcelona: {
			appearances: 780,

			goals: {
				premierLeagueGoals: 590,

				championsLeagueGoals: 50,
			},
		},
	},
};

function displayKeysAndValues(player) {
	let keys = [];
	let values = [];
	function iterateObject(player) {
		for (let key in player) {
			if (typeof player[key] === "object" && player[key] !== null) {
				iterateObject(player[key]);
			} else {
				keys.push(key);
				values.push(player[key]);
			}
		}
	}

	iterateObject(player);

	console.log("All the keys:", keys);
	console.log("All the values:", values);
}
displayKeysAndValues(player);
