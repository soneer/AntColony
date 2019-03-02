
public class GenerateCellGrid {
	public Cell[][] worldGrid;
	public GenerateCellGrid(int windowX, int windowY) {
		worldGrid = new Cell[windowX][windowY];
		for(int i = 0; i < windowX; i++){
			for(int j = 0; j < windowY; j++){
				worldGrid[i][j] = new Cell(i, j);
				System.out.print("|"+worldGrid[i][j].getCellType());
			}

			System.out.println();
		}
	}
	
	public Cell[][] getCellGrid(){
		return worldGrid;
	}

}
