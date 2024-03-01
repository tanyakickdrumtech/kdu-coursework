import React from "react";
import { dateStyle1, dateStyle2, headerStyles2 } from "../styles/styles";

const DateSelection: React.FC = () => {
	return (
		<div>
			<h2 style={headerStyles2}>Select Dates</h2>
			<input
				style={dateStyle1}
				type="date"
				id="birthday"
				name="start date"
			></input>
			<input
				style={dateStyle2}
				type="date"
				id="birthday"
				name="start end"
			></input>
		</div>
	);
};

export default DateSelection;
