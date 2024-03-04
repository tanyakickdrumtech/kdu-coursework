import RoomSelection from "./Components/RoomSelection";
import DateSelection from "./Components/DateSelection";
import AddOnSelection from "./Components/AddOnSelection";
import CostCalculate from "./Components/CostCalculate";
import { headingStyle } from "./styles/styles";

function App() {
	return (
		<div>
			<div style={headingStyle}>Hotel Booking</div>
			<RoomSelection />
			<DateSelection />
			<AddOnSelection />
			<CostCalculate />
		</div>
	);
}

export default App;
