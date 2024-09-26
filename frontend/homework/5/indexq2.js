const path = require("path");

/**
 * function extract the file information
 * @param {*} filePath
 * @returns
 */
function extractFileInfo(filePath) {
	var extension = path.extname(filePath);
	var baseName = path.basename(filePath, extension);
	var directory = path.dirname(filePath);

	return { extension, baseName, directory };
}

/**
 * function to return an array of objects , representing the information about a file
 * @param {*} filePaths
 * @returns
 */
function processFilePaths(filePaths) {
	return filePaths.map(extractFileInfo);
}
/**
 *
 */
const filePaths = [
	"dir1/dir2/file1.txt",
	"dir1/dir3/file2.txt",
	"dir1/dir3/file3.md",
	"dir4/file4.jpg",
	"dir4/file5.pdf",
];

console.log(processFilePaths(filePaths));
