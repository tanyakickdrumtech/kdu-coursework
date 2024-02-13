const express = require("express");
const app = express();
const post = require("./routes/api/posts");
const PORT = process.env.PORT || 3000;

app.use(express.json());
app.use("/", post);

app.listen(PORT, () => {
	console.log(`Server is running on http://localhost:${PORT}`);
});
