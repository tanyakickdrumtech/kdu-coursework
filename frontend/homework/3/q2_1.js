// Q2_1->triming to string and changing some part to uppercase
const days = [
	"Sunday   ",
	"   Monday  ",

	"  Tuesday",
	"Wednesday  ",
	"  Thursday   ",
	"   Friday",
	"Saturday    ",
];
function trimDays(days) {
	let trimmedDays = [];
	for (let i = 0; i < days.length; i++) {
		trimmedDays.push(days[i].trim().slice(0, 3).toUpperCase());
	}

	return trimmedDays;
}
const trimmedDays = trimDays(days);
console.log("The trimmed , abbreviated string array is", trimmedDays);
