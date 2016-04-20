package com.capgemini.java;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for a single cell.
 * 
 * @author ADDZIEDZ
 *
 */
public class Cell {
	private Point cellPosition = new Point(0, 0);
	private boolean cellState = CellState.DEAD.getValue();
	private List<Point> neighboursPositions = new ArrayList<>();
	private int numberOfAliveNeighbors = 0;

	public Cell(int positionX, int positionY, boolean cellState) {
		this.cellPosition.x = positionX;
		this.cellPosition.y = positionY;
		this.cellState = cellState;
	}

	public Point getCellPosition() {
		return this.cellPosition;
	}

	public boolean getCellState() {
		return this.cellState;
	}

	public void setCellState(boolean state) {
		this.cellState = state;
	}

	public List<Point> getNeighborsPositionsList() {
		return this.neighboursPositions;
	}

	public void setNeighborsPositionsList(Point neighborPosition) {
		this.neighboursPositions.add(neighborPosition);
	}

	public int getNumberOfAliveNeighbors() {
		return this.numberOfAliveNeighbors;
	}

	public void setNumberOfAliveNeighbors(int numberOfAliveCells) {
		this.numberOfAliveNeighbors = numberOfAliveCells;
	}
}
