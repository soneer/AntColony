import java.awt.Point;
import java.util.Random;

public class Cell {
int resources, xPos, yPos = 0;
String cellType = "";
	public Cell(int x, int y, int r) {
		xPos = x;
		yPos = y;
		resources = r;
		cellType = generateCellType();
	}
	
	public Cell(int x, int y) {
		xPos = x;
		yPos = y;	
		cellType = generateCellType();
	}

	public int getResources() {
	return resources;
}

	public String generateCellType(){
		int randomCellGenerate = (new Random()).nextInt(100 - 0 + 1) + 0;
		int randomCellResource = (new Random()).nextInt(50 - 0 + 1) + 0;
		resources = 0;
		if( randomCellGenerate%5 ==0){
			return "W";
		}
		else if( randomCellGenerate%8 ==0){
			resources=	randomCellResource + 10;
			return "V";
		}
		else if( randomCellGenerate%11 ==0){
			return "R";
		}
		else{
			return "D";
		}
	}
	
	public String getCellType(){
		return cellType;
	}
public void setCellType(String cellType) {
		this.cellType = cellType;
	}

public void setResources(int resources) {
	this.resources = resources;
}

public Point getCoordinates(){

	return new Point(xPos, yPos);
}
public String printPoint(){
    return "("+getCoordinates().x+", "+getCoordinates().y+")";
}

public boolean compareCellTo(Cell b){
	if(this.getCoordinates().equals(b.getCoordinates())){
		return true;
	}
	else{
		return false;
	}
}
}
