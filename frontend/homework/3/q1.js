// Q1-To calculate tips based ont the bill
function calculateTip(bills) {
	let tips = [];
	let finalAmounts = [];

	for (let i = 0; i < bills.length; i++) {
		let bill = bills[i];
		let tip;
		if (bill < 50) {
			tip = 0.2 * bill;
		} else if (bill >= 50 && bill <= 200) {
			tip = 0.15 * bill;
		} else if (bill > 200) {
			tip = 0.1 * bill;
		}
		let finalAmount = bill + tip;
		tips.push(tip);
		finalAmounts.push(finalAmount);
	}
	return [tips, finalAmounts];
}

const bills = [140, 45, 280];

const [tips, finalAmounts] = calculateTip(bills);
console.log("All the Three tips are:", tips);
console.log("All the three final paid bills are:", finalAmounts);
