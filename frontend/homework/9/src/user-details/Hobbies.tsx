import React from "react";
import "./Hobbies.css";

/**
 * Interface representing the props for the Hobbies component.
 */
interface HobbiesProps {
	/** An array of hobbies objects containing id and hobby. */
	hobbies: { id: number; hobby: string }[];
}

/**
 * Functional component representing the list of hobbies.
 *
 * @param {HobbiesProps} props - The props for the Hobbies component.
 * @returns {JSX.Element} - The rendered list of hobbies.
 */
export function Hobbies({ hobbies }: HobbiesProps): JSX.Element {
	return (
		<ul className="hobbies">
			<h3 className="info-type-hobby">Hobbies</h3>
			{hobbies.map((hobby) => {
				return <li key={hobby.id}>{hobby.hobby}</li>;
			})}
		</ul>
	);
}
