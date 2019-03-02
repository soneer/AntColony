import java.awt.Point;
import java.util.Random;

public class ScoutAnt extends Ant {

	public ScoutAnt(int x, int y, PheromoneNeuralNet pNN) {
		super.xPos = x;
		super.yPos = y;
		super.health = (new Random()).nextInt(35 - 25 + 1) + 25;
		super.speed = 2;
		super.attack = 2;
		super.resourceCapacity = 0;
		super.id = this.hashCode();
		super.typeOfAnt = "Scout";
		super.pNN = pNN;
	}
	
	public void findResources(Cell[][] worldGrid){
		try{
		this.current = worldGrid[this.xPos][this.yPos];
		if(current.getCellType()=="V"){
			System.out.println("FOUND SOME MOTHERFUCKEN RESOURCES:"+current.getCellType());
			
			this.pNN.addResourcePoint(new ResourcePoint(worldGrid[this.xPos][this.yPos].getResources(), worldGrid[this.xPos][this.yPos]));
		}
		else{
			System.out.println("WHOOOPS:"+current.getCellType());
		}
		}
		catch(Exception e){
			this.pNN.addDangerousPoint(this.getCoordinates());
			this.setHealth(0);
			System.out.println(this.printPoint());
			System.out.println(e);
		}
	
	}
	
	@Override
	public void act(Cell[][] worldGrid){
		if(this.getHealthStatus()){
		move(worldGrid);
		findResources(worldGrid);
		}
	}
	
	@Override
	public void move(Cell[][] worldGrid){

		// Move TOP_RIGHT
	 if(calculateMove( this.xPos +1,  this.yPos - 1)){}

		// Move TOP
	 else if(calculateMove( this.xPos, this.yPos - 1)){}

		// Move TOP_LEFT
	 else if(calculateMove(this.xPos - 1, this.yPos - 1)){}
	
		// Move LEFT
	 else if(calculateMove( this.xPos -1,this.yPos)){}

		// Move BOTTOM_LEFT
	 else if(calculateMove( this.xPos -1, this.yPos+1)){}
	 
		// Move BOTTOM
	 else if(calculateMove( this.xPos , this.yPos+1)){}

		// Move BOTTOM_RIGHT
	 else if(calculateMove( this.xPos +1,this.yPos +1)){}
	 
		// Move RIGHT
	 else if(calculateMove( this.xPos +1, this.yPos)){}

	 else{}
	}
	
	public boolean calculateMove(int x, int y){
		Point currentPoint = new Point(x, y);
	 if (!this.pNN.visitedPoints.contains(currentPoint) && !this.pNN.isDangerousPoint(currentPoint)) {
			this.pNN.visitedPoints.add(currentPoint);
			this.xPos = x;
			this.yPos = y;
			return true;
		}
	 else{
		 return false;
	 }
	}
}
