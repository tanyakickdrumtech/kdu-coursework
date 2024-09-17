const express = require("express");
const uuid = require("uuid");
const posts = require("../../data/Posts");

const app = express.Router();

app.post("/post", (req, res) => {
	const { name, postData } = req.body;
	const id = uuid.v4();

	const newPost = {
		id,
		name,
		postData,
	};
	console.log(newPost);
	posts.push(newPost);

	res.status(201).json(newPost);
});

app.get("/allposts", (req, res) => {
	res.json(posts);
});

app.get("/post/:id", (req, res) => {
	const postId = req.params.id;
	const post = posts.find((post) => post.id === postId);
	console.log(postId);
	if (post) {
		console.log(post);
		res.json(post);
	} else {
		res.status(404).send("Post not found.");
	}
});

module.exports = app;
