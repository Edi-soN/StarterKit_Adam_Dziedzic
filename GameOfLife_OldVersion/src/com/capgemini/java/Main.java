package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;


public class Main {	

	//private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {

		new GameOfLife(3, 3);

		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, false));
		cellList.add(new Cell(0, 1, true));
		cellList.add(new Cell(0, 2, false));
		cellList.add(new Cell(1, 0, false));
		cellList.add(new Cell(1, 1, true));
		cellList.add(new Cell(1, 2, true));
		cellList.add(new Cell(2, 0, false));
		cellList.add(new Cell(2, 1, true));
		cellList.add(new Cell(2, 2, false));

		new GameOfLife(cellList);
		
	}
}
