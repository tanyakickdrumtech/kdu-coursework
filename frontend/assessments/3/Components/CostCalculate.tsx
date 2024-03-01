import React, { useState, useEffect } from "react";
import { costStyle, submitBtnStyle } from "../styles/styles";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";

const CostCalculate: React.FC = () => {
	const [totalCost, setTotalCost] = useState<number | null>(null);
	const selectedRoom = useSelector((state: RootState) =>
		state.room.roomDetails?.find((room) => room.id === 1)
	);
	const selectedAddOns = useSelector(
		(state: RootState) => state.addOns.selectedAddOns
	);
	const startDate = document.getElementById("start-date") as HTMLInputElement;
	const endDate = document.getElementById("end-date") as HTMLInputElement;

	useEffect(() => {
		if (selectedRoom && startDate && endDate) {
			const numberOfDays =
				(new Date(endDate.value).getTime() -
					new Date(startDate.value).getTime()) /
				(1000 * 3600 * 24);
			const roomCost = parseFloat(selectedRoom.costPerNight) * numberOfDays;
			const addOnCost = selectedAddOns.reduce((total, addOn) => {
				const addOnDetails = selectedRoom.addOns.find(
					(item) => item.name === addOn
				);
				return total + parseFloat(addOnDetails?.cost || "0");
			}, 0);
			const total = roomCost + addOnCost;
			setTotalCost(total);
		}
	}, [selectedRoom, selectedAddOns, startDate, endDate]);

	return (
		<div>
			{totalCost && <div style={costStyle}>Cost + 18% GST = {totalCost}</div>}
			<button
				style={submitBtnStyle}
				disabled={!selectedRoom || !startDate || !endDate || !totalCost}
			>
				Submit
			</button>
		</div>
	);
};

export default CostCalculate;
