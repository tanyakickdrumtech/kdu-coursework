import React from "react";
import "./Skills.css";

/**
 * Interface representing the props for the Skills component.
 */
interface SkillsProps {
	/** An array of skills objects containing id and skill. */
	skills: { id: number; skill: string }[];
}

/**
 * Functional component representing the list of skills.
 *
 * @param {SkillsProps} props - The props for the Skills component.
 * @returns {JSX.Element} - The rendered list of skills.
 */
export function Skills({ skills }: SkillsProps): JSX.Element {
	return (
		<ul className="skills">
			<h3 className="info-type-skill">Skills</h3>
			{skills.map((skill) => {
				return <li key={skill.id}>{skill.skill}</li>;
			})}
		</ul>
	);
}
