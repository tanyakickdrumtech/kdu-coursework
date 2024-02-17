//Q4_1->Convert the input string into a JSON object where all the values are converted to uppercase except the email.

const stringObject =
	'{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

const jsonObject = JSON.parse(stringObject);
for (let key in jsonObject) {
	if (key !== "email") {
		if (typeof jsonObject[key] === "string")
			jsonObject[key] = jsonObject[key].toUpperCase();
	}
}

console.log(jsonObject);
