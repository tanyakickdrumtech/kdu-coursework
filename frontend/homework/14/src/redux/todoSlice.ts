import { createSlice, PayloadAction } from "@reduxjs/toolkit";

/**
 * Represents a single todo item.
 */
interface Todo {
	id: number;
	text: string;
	completed: boolean;
}

/**
 * Represents the state of the todo list.
 */
interface TodoState {
	todos: Todo[];
}

/**
 * The initial state for the todo list.
 */
const initialState: TodoState = {
	todos: [],
};

/**
 * The todo slice containing reducers for managing todo items.
 */
const todoSlice = createSlice({
	name: "todos",
	initialState,
	reducers: {
		/**
		 * Adds a new todo item to the list.
		 * @param {TodoState} state - The current state of the todo list.
		 * @param {PayloadAction<string>} action - The action containing the todo text.
		 */
		addTodo: (state, action: PayloadAction<string>) => {
			const newTodo: Todo = {
				id: state.todos.length + 1,
				text: action.payload,
				completed: false,
			};
			state.todos.push(newTodo);
		},
		/**
		 * Toggles the completion status of a todo item.
		 * @param {TodoState} state - The current state of the todo list.
		 * @param {PayloadAction<number>} action - The action containing the todo id.
		 */
		toggleTodo: (state, action: PayloadAction<number>) => {
			const todo = state.todos.find((todo) => todo.id === action.payload);
			if (todo) {
				todo.completed = !todo.completed;
			}
		},
		/**
		 * Deletes a todo item from the list.
		 * @param {TodoState} state - The current state of the todo list.
		 * @param {PayloadAction<number>} action - The action containing the todo id.
		 */
		deleteTodo: (state, action: PayloadAction<number>) => {
			state.todos = state.todos.filter((todo) => todo.id !== action.payload);
		},
		/**
		 * Clears all completed todo items from the list.
		 * @param {TodoState} state - The current state of the todo list.
		 */
		clearCompleted: (state) => {
			state.todos = state.todos.filter((todo) => !todo.completed);
		},
	},
});

export const { addTodo, toggleTodo, deleteTodo, clearCompleted } =
	todoSlice.actions;
export default todoSlice.reducer;
