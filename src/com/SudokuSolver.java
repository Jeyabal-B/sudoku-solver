package com;

public class SudokuSolver {

	private static final int GRID_SIZE = 9;

	private static final int BOX_SIZE = 3;

	public static void main(String[] args) {

		int[][] board = { { 7, 0, 2, 0, 5, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 3, 0, 0, 0 }, { 1, 0, 0, 0, 0, 9, 5, 0, 0 },
				{ 8, 0, 0, 0, 0, 0, 0, 9, 0 }, { 0, 4, 3, 0, 0, 0, 7, 5, 0 }, { 0, 9, 0, 0, 0, 0, 0, 0, 8 },
				{ 0, 0, 9, 7, 0, 0, 0, 0, 5 }, { 0, 0, 0, 2, 0, 0, 0, 0, 0 }, { 0, 0, 7, 0, 4, 0, 2, 0, 3 } };

		System.out.println("Input Board : ");
		printBoard(board);

		if (solveBoard(board)) {
			System.out.println("\nBoard solved successfully!");
		} else {
			System.out.println("Unsolvable Board..");
		}

		printBoard(board);

	}

	private static void printBoard(int[][] board) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (i != 0 && i % BOX_SIZE == 0) {
				System.out.println("-------------------");
			}
			for (int j = 0; j < GRID_SIZE; j++) {
				if (j != 0 && j % BOX_SIZE == 0) {
					System.out.print("|");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// check row
	private static boolean isNumberInRow(int[][] board, int number, int row) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	// check column
	private static boolean isNumberInColumn(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	// check the local sqaure
	private static boolean isNumberInBox(int[][] board, int number, int row, int column) {

		int firstRow = row - row % BOX_SIZE;
		int firstColumn = column - column % BOX_SIZE;
		for (int i = firstRow; i < firstRow; i++) {
			for (int j = firstColumn; j < firstColumn; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isValidPlacement(int[][] board, int number, int row, int column) {

		return !isNumberInBox(board, number, row, column) && !isNumberInRow(board, number, row)
				&& !isNumberInColumn(board, number, column);
	}

	// go one by one and solve, backtrack if it fails
	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
}
