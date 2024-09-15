import React, { createContext, useContext, useState } from "react";

interface IList {
	id: number;
	text: string;
}

interface IListContext {
	list: IList[];
	setList: React.Dispatch<React.SetStateAction<IList[]>>;
	searchQuery: string;
	setSearchQuery: React.Dispatch<React.SetStateAction<string>>;
}
interface ThemeProviderProps {
	children: React.ReactNode;
}

/**
 *  Create a context object for managing the list state
 */
const ListContext = createContext<IListContext>({
	list: [],
	setList: () => {},
	searchQuery: "",
	setSearchQuery: () => {},
});

export const useListContext = () => useContext(ListContext);

/**
 * Provider component to wrap the application and provide list context
 * @param param0
 * @returns
 */

export const ListProvider = ({ children }: ThemeProviderProps) => {
	const [list, setList] = useState<IList[]>([]);
	const [searchQuery, setSearchQuery] = useState<string>("");

	return (
		<ListContext.Provider
			value={{ list, setList, searchQuery, setSearchQuery }}
		>
			{children}
		</ListContext.Provider>
	);
};
