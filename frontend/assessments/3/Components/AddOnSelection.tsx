import React, { useState, useEffect } from "react";
import { buttonStyle1, buttonStyle2, headerStyles3 } from "../styles/styles";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";

const AddOnSelection: React.FC = () => {
	const [selectedAddOns, setSelectedAddOns] = useState<string[]>([]);
	const selectedRoom = useSelector((state: RootState) =>
		state.room.roomDetails?.find((room) => room.id === 1)
	);
	const dispatch = useDispatch();

	useEffect(() => {
		if (!selectedRoom) {
			setSelectedAddOns([]);
		}
	}, [selectedRoom]);

	const handleAddOnSelection = (addOnName: string) => {
		setSelectedAddOns((prevState) => {
			if (prevState.includes(addOnName)) {
				return prevState.filter((item) => item !== addOnName);
			} else {
				return [...prevState, addOnName];
			}
		});
	};

	return (
		<div>
			<h2 style={headerStyles3}>Select Additional Add Ons/Preferences</h2>
			{selectedRoom ? (
				selectedRoom.addOns.map((addOn, index) => (
					<button
						key={index}
						style={buttonStyle2}
						onClick={() => handleAddOnSelection(addOn.name)}
						className={selectedAddOns.includes(addOn.name) ? "selected" : ""}
					>
						{addOn.name}
					</button>
				))
			) : (
				<p>Please select a room type first</p>
			)}
		</div>
	);
};

export default AddOnSelection;
