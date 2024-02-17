//Q4_2->take the generated object and convert it back into a string without the “email” key-value pair.
const generatedObject = {
	firstName: "ALEX",
	lastName: "HUNTER",
	age: 24,
	city: "LONDON",
	country: "ENGLAND",
};
const originalStringWithoutEmail = JSON.stringify(generatedObject);

console.log(originalStringWithoutEmail);
