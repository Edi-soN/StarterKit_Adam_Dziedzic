package com.capgemini.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

import com.capgemini.java.Cell;
import com.capgemini.java.CellPoint;
import com.capgemini.java.CellState;
import com.capgemini.java.Game;

public class GameTest {
	private CellPoint cellPosition = new CellPoint(1, 1);
	private Cell[][] cellGrid = new Cell[][] {
			// shouldReturnFalseForAliveCellWithLessThanTwoNeighbors
			{ new Cell(0, 0, CellState.DEAD.getValue()), new Cell(0, 1, CellState.DEAD.getValue()),
					new Cell(0, 2, CellState.DEAD.getValue()), new Cell(1, 0, CellState.DEAD.getValue()),
					new Cell(1, 1, CellState.ALIVE.getValue()), new Cell(1, 2, CellState.ALIVE.getValue()),
					new Cell(2, 0, CellState.DEAD.getValue()), new Cell(2, 1, CellState.DEAD.getValue()),
					new Cell(2, 2, CellState.DEAD.getValue()) },
			// shouldReturnFalseForAliveCellWithLessThanTwoNeighbors
			{ new Cell(0, 0, CellState.ALIVE.getValue()), new Cell(0, 1, CellState.ALIVE.getValue()),
					new Cell(0, 2, CellState.ALIVE.getValue()), new Cell(1, 0, CellState.ALIVE.getValue()),
					new Cell(1, 1, CellState.ALIVE.getValue()), new Cell(1, 2, CellState.ALIVE.getValue()),
					new Cell(2, 0, CellState.ALIVE.getValue()), new Cell(2, 1, CellState.ALIVE.getValue()),
					new Cell(2, 2, CellState.ALIVE.getValue()) },
			// shouldReturnTrueForAliveCellWithTwoNeighbors
			{ new Cell(0, 0, CellState.DEAD.getValue()), new Cell(0, 1, CellState.DEAD.getValue()),
					new Cell(0, 2, CellState.DEAD.getValue()), new Cell(1, 0, CellState.DEAD.getValue()),
					new Cell(1, 1, CellState.ALIVE.getValue()), new Cell(1, 2, CellState.ALIVE.getValue()),
					new Cell(2, 0, CellState.DEAD.getValue()), new Cell(2, 1, CellState.DEAD.getValue()),
					new Cell(2, 2, CellState.ALIVE.getValue()) },
			// shouldReturnTrueForAliveCellWithThreeNeighbors
			{ new Cell(0, 0, CellState.DEAD.getValue()), new Cell(0, 1, CellState.DEAD.getValue()),
					new Cell(0, 2, CellState.ALIVE.getValue()), new Cell(1, 0, CellState.DEAD.getValue()),
					new Cell(1, 1, CellState.ALIVE.getValue()), new Cell(1, 2, CellState.ALIVE.getValue()),
					new Cell(2, 0, CellState.DEAD.getValue()), new Cell(2, 1, CellState.DEAD.getValue()),
					new Cell(2, 2, CellState.ALIVE.getValue()) },
			// shouldReturnTrueForDeadCellWithThreeNeighbors
			{ new Cell(0, 0, CellState.DEAD.getValue()), new Cell(0, 1, CellState.DEAD.getValue()),
					new Cell(0, 2, CellState.ALIVE.getValue()), new Cell(1, 0, CellState.DEAD.getValue()),
					new Cell(1, 1, CellState.DEAD.getValue()), new Cell(1, 2, CellState.ALIVE.getValue()),
					new Cell(2, 0, CellState.DEAD.getValue()), new Cell(2, 1, CellState.DEAD.getValue()),
					new Cell(2, 2, CellState.ALIVE.getValue()) } };

	private boolean calculateCellStateInNextGeneration(int indexOfCellGrid) {
		Game game = new Game(new ArrayList<>(Arrays.asList(cellGrid[indexOfCellGrid])));
		game.calculateNextGeneration();
		return game.getCellMap().get(cellPosition).getCellState();
	}

	@Test
	public void shouldReturnFalseForAliveCellWithLessThanTwoNeighbors() {
		assertFalse(calculateCellStateInNextGeneration(0));
	}

	@Test
	public void shouldReturnFalseForAliveCellWithMoreThanThreeNeighbors() {
		assertFalse(calculateCellStateInNextGeneration(1));
	}

	@Test
	public void shouldReturnTrueForAliveCellWithTwoNeighbors() {
		assertTrue(calculateCellStateInNextGeneration(2));
	}

	@Test
	public void shouldReturnTrueForAliveCellWithThreeNeighbors() {
		assertTrue(calculateCellStateInNextGeneration(3));
	}

	@Test
	public void shouldReturnTrueForDeadCellWithThreeNeighbors() {
		assertTrue(calculateCellStateInNextGeneration(4));
	}
}
