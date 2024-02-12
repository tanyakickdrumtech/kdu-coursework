const http = require("http");
const os = require("os");
const fs = require("fs");

/**
 * this is a os method to to return json information with given details
 * @returns
 */
function createOSInfo() {
	return {
		HostName: os.hostname(),
		OperatingSystem: os.platform(),
		Architecture: os.arch(),
		OSRelease: os.release(),
		Uptime: os.uptime(),
		NumberOfCPUCores: os.cpus().length,
		TotalMemory: os.totalmem(),
		FreeMemory: os.freemem(),
		CurrentWorkingDirectory: process.cwd(),
	};
}

//writing OS information JSON to a local file
const osInfo = createOSInfo();
fs.writeFileSync("osInfo.json", JSON.stringify(osInfo, null, 2));


/**
 * function to serve the os info json
 * @param {*} req
 * @param {*} res
 */
function serveOSInfo(req, res) {
	fs.readFile("osInfo.json", (err, data) => {
		if (err) {
			res.writeHead(500);
			res.end("Internal Server Error");
			return;
		}
		res.writeHead(200, { "Content-Type": "application/json" });
		res.end(
			"Hello, my name is Tanya\n" + "Here is my system information:" + data
		);
	});
}

//creating the http server
const server = http.createServer(serveOSInfo);

//starting the server
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
	console.log(`Server is running on port ${PORT}`);
});
