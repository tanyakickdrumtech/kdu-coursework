//Q2_2->to code the string
function encryptyString(strCode) {
	strCode = strCode.trim();
	strCode = strCode.replace(/a/g, "4");
	strCode = strCode.replace(/e/g, "3");
	strCode = strCode.replace(/i/g, "1");
	strCode = strCode.replace(/o/g, "0");
	strCode = strCode.replace(/s/g, "5");
	return strCode;
}

console.log(encryptyString("javascript is cool"));
console.log(encryptyString("programming is fun"));
console.log(encryptyString("  become a coder"));
