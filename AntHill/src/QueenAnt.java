
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class QueenAnt extends Ant{

	String colonyStatus = "Alive";
	public String getColonyStatus() {
		return colonyStatus;
	}


	public void setColonyStatus(String colonyStatus) {
		this.colonyStatus = colonyStatus;
	}


	public QueenAnt(Cell[][] worldGrid) {
		int x =(new Random()).nextInt((worldGrid.length-1) - 0 + 1) + 0;
		int y =(new Random()).nextInt((worldGrid[0].length-1) - 0 + 1) + 0;
		super.xPos = x;
		super.yPos = y;
		super.health = 1000;
		super.resourcesCarried = 10;
		super.current = worldGrid[x][y];
		super.typeOfAnt = "Queen";
		super.id = this.hashCode();
		super.pNN = new PheromoneNeuralNet();
		super.pNN.addAntToTheColony(new DroneAnt(x, y, pNN));
	}


	@Override
	public void act(Cell[][] worldGrid){
		this.health--;
		this.current = worldGrid[xPos][yPos];
		if(this.current.getCellType() == "H"){
			directChildren( worldGrid);
			makeChild();
		}
		else if(this.current.getCellType()=="D" || this.current.getCellType()=="V"){
			this.mine();
			this.createHive();
		}
		else{
			this.move(worldGrid);
		}
		//return current;
	}


	public void directChildren( Cell[][] worldGrid){
		this.pNN.clearNumberOfAntTypes();
		if(!pNN.antsInTheColony.isEmpty()){
		ArrayList<Integer> removeDeadAnts = new ArrayList<Integer>();
				for(int currentAntID: pNN.antsInTheColony.keySet()){
						if(pNN.antsInTheColony.get(currentAntID).getHealthStatus()){
						this.pNN.updateNumberOfAntTypes(pNN.antsInTheColony.get(currentAntID).getTypeOfAnt());
						pNN.antsInTheColony.get(currentAntID).act(worldGrid);
						}
						else{
						removeDeadAnts.add(currentAntID);
						}
					}
				for(int antToRemove :removeDeadAnts){
					this.pNN.antsInTheColony.remove(antToRemove);
				}
		}
	}
	@Override
	public void mine(){
		this.setResourceCapacity(this.getResourcesCarried()+current.getResources());
		this.setSuppliesCarried(this.resourcesCarried+current.getResources());
		current.setResources(0);

	}

	public void createHive(){
		current.setCellType("H");
		pNN.setHive(current);
		this.dropResources();
	}


	public void makeChild(){
		if(this.pNN.getHive().getResources()>0){
			if( (current.getResources() > 5) && (this.pNN.numberOfAntTypes.get("Drone") < 2)){

				this.pNN.addAntToTheColony((new DroneAnt(this.xPos, this.yPos, this.pNN)));
				this.pNN.getHive().setResources(this.pNN.getHive().getResources()-1);	
			}
			else if((current.getResources() > 1) && (this.pNN.numberOfAntTypes.get("Scout") < 5)){
				this.pNN.addAntToTheColony((new ScoutAnt(this.xPos, this.yPos, this.pNN)));
				this.pNN.getHive().setResources(this.pNN.getHive().getResources()-1);	
			}
			else if((current.getResources() >= 1)){
				this.pNN.addAntToTheColony((new WorkerAnt(this.xPos, this.yPos, this.pNN)));
				this.pNN.getHive().setResources(this.pNN.getHive().getResources()-1);	
			}
			else{
				//Wait for more resources
			}
		}
		else{
			this.setColonyStatus("Dead");
		}
	}
}
