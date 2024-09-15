import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { Header } from "./user-details/Header";
import { Skills } from "./user-details/Skills";
import { Hobbies } from "./user-details/Hobbies";

/**
 * Functional component representing the main application.
 *
 * @returns {JSX.Element} - The rendered application.
 */
function App(): JSX.Element {
	const user = {
		name: "Amey",
		fullName: "Amey Aditya",
		qualification: "SSE",
		skills: [
			{
				id: 1,
				skill: "Python",
			},
			{
				id: 2,
				skill: "React",
			},
		],
		hobbies: [
			{
				id: 1,
				hobby: "Cricket",
			},
		],
	};

	return (
		<div className="details">
			<Header
				name={user.name}
				fullName={user.fullName}
				qualification={user.qualification}
			/>
			<div className="skills-hobbies">
				<Skills skills={user.skills} />
				<Hobbies hobbies={user.hobbies} />
			</div>
		</div>
	);
}

export default App;
