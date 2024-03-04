import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { FetchRoomDetails } from "../thunk/FetchRoomDetails";
import { buttonStyle2, headerStyles1 } from "../styles/styles";

const RoomSelection: React.FC = () => {
	const dispatch = useDispatch();
	const [selectedRoom, setSelectedRoom] = useState<number | null>(null);
	const roomDetails = useSelector((state: RootState) => state.room.roomDetails);

	React.useEffect(() => {
		dispatch(FetchRoomDetails());
	}, [dispatch]);

	const handleRoomSelection = (roomId: number) => {
		setSelectedRoom(roomId);
	};

	return (
		<div>
			<h2 style={headerStyles1}>Select Room Type</h2>
			{roomDetails && Array.isArray(roomDetails) && (
				<div>
					{roomDetails.map((room: any) => (
						<button
							style={buttonStyle2}
							key={room.id}
							value={room.id}
							onClick={() => handleRoomSelection(room.id)}
							className={selectedRoom === room.id ? "selected" : ""}
						>
							{room.name}
						</button>
					))}
				</div>
			)}
		</div>
	);
};

export default RoomSelection;
