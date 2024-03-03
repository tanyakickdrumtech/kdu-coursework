import { AppDispatch } from "../redux/store";
import {
	fetchTransactionsStart,
	fetchTransactionsSuccess,
	fetchTransactionsFailure,
} from "../redux/PortfolioSlice";

/**
 * Asynchronous action creator for fetching transactions from an external API.
 * It dispatches actions to start, successfully fetch, or handle failure of fetching transactions.
 * @returns A function that dispatches actions based on the fetch result.
 */
export const fetchTransactions = () => async (dispatch: AppDispatch) => {
	dispatch(fetchTransactionsStart());
	try {
		const response = await fetch(
			"https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
		);
		if (!response.ok) {
			throw new Error("Failed to fetch transactions");
		}
		const data = await response.json();
		dispatch(fetchTransactionsSuccess(data));
	} catch (error) {
		dispatch(fetchTransactionsFailure(error.message));
	}
};
