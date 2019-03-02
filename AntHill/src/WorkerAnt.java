
import java.awt.Point;
import java.util.Random;

public class WorkerAnt extends Ant{

	public WorkerAnt(int x, int y, PheromoneNeuralNet pNN) {
		super.xPos = x;
		super.yPos = y;
		super.health = (new Random()).nextInt(25 - 15 + 1) + 15;
		super.speed = 0;
		super.attack = 2;
		super.resourceCapacity = 5;
		super.id = this.hashCode();
		super.typeOfAnt = "Worker";
		super.pNN = pNN;
	}

	
	@Override
	public void act(Cell[][] worldGrid){
		this.health--;
		
		//Set the current Cell to the cell at the Ants location in the world grid.
		this.current = worldGrid[this.xPos][this.yPos];
		
		//If the Ant is dead, drop whatever resources the Ant was holding. Resources are now bound to Cell.
		if (!getHealthStatus()) {
			this.dropResources();
		} 
		
		//If the Ant is full of resources, the Ant will return to the hive to drop Resources
		else if (this.getResourcesCarried() == this.resourceCapacity) {
			//Return to hive
			this.returnToHive();		
			//If Ant is at the hive, drop resources. Small health boost for the Ant.
			if(this.current.compareCellTo(this.pNN.getHive())){
				this.dropResources();
			}

		} 
		
		//If current Cell is a "V" cell (Vegetation Cell), then the Ant will mine the Cell's resources.
		else if (current == this.pNN.getDestinationCell()) {
			this.mine();
		} 
		
		//Ant will move forward in the world grid.
		else {
			this.move(worldGrid);
		}
		
	}
	@Override
	public void move(Cell[][] worldGrid){
		if(this.xPos > this.pNN.destinationCell.xPos){
			this.xPos = this.xPos - 1;
		}
		else if(this.xPos < this.pNN.destinationCell.xPos){
			this.xPos = this.xPos + 1;
		}
		else{}
		
		if(this.yPos > this.pNN.destinationCell.yPos){
			this.yPos = this.yPos - 1;
		}
		else if(this.yPos < this.pNN.destinationCell.yPos){
			this.yPos = this.yPos + 1;
		}
		else{}
	}
	
	public void returnToHive(){
		if(this.xPos > this.pNN.hive.xPos){
			this.xPos = this.xPos - 1;
		}
		else if(this.xPos < this.pNN.hive.xPos){
			this.xPos = this.xPos + 1;
		}
		else{}
		
		if(this.yPos > this.pNN.hive.yPos){
			this.yPos = this.yPos - 1;
		}
		else if(this.yPos < this.pNN.hive.yPos){
			this.yPos = this.yPos + 1;
		}
		else{}
	}
}
