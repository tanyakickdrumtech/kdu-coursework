//Q3-perform operations on object
const shoes = [
	{ type: "sneakers", color: "white", size: 6, price: 6000 },
	{ type: "boots", color: "brown", size: 10, price: 15000 },
];

const shirts = [
	{ type: "polo", color: "blue", size: "M", price: 1500 },
	{ type: "denim", color: "blue", size: "S", price: 1800 },
	{ type: "half-sleeves", color: "wine", size: "L", price: 1200 },
];

const warehouse = [...shoes, ...shirts];

function toCalculateTotalWorth(warehouseProducts) {
	return warehouseProducts.reduce(
		(total, warehouseProducts) => total + warehouseProducts.price,
		0
	);
}

const totalWorth = toCalculateTotalWorth(warehouse);
console.log(
	"Total worth of the products stored in the warehouse is",
	totalWorth
);

warehouse.sort((a, b) => b.price - a.price);

const blueProduct = warehouse.filter(
	(warehouseProducts) => warehouseProducts.color === "blue"
);
console.log("Warehouse products which are blue in color:", blueProduct);
