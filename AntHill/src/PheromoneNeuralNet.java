
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class PheromoneNeuralNet {
HashSet<Point> dangerousPoints;
HashSet<Point> dirtPoints;
HashSet<Point> visitedPoints;
LinkedList<ResourcePoint> resourcePoints;
HashMap<String, Integer> numberOfAntTypes = new HashMap<String, Integer>();
HashMap<Integer,Ant> antsInTheColony = new HashMap<Integer,Ant>();

Cell destinationCell;
Cell hive;
	public PheromoneNeuralNet() {
		this.dangerousPoints = new HashSet<Point>();
		this.dirtPoints = new HashSet<Point>();
		this.visitedPoints = new HashSet<Point>();
		this.resourcePoints = new LinkedList<ResourcePoint>();
		this.destinationCell = null;
		this.hive = null;
		this.numberOfAntTypes.put("Worker", 0);
		this.numberOfAntTypes.put("Drone", 0);
		this.numberOfAntTypes.put("Scout", 0);
	}

	public Cell getDestinationCell(){
		if(resourcePoints.isEmpty()){
			return this.hive;
		}
		else{
			return resourcePoints.getFirst().getResourceCell();
		}
	}
	
	public Cell getHive(){
		return this.hive;
	}
	public void setHive(Cell hive){
		 this.hive = hive;
	}
	
	public HashMap<Integer,Ant> getAntsInTheColony(){
		return this.antsInTheColony;
	}

	public void addAntToTheColony(Ant newAnt){
		this.antsInTheColony.put(newAnt.getId(), newAnt);
	}
	
	public void addResourcePoint(ResourcePoint newPoint){
		this.resourcePoints.addLast(newPoint);
		this.destinationCell = this.resourcePoints.getFirst().getResourceCell();
	}
	
	public void addDangerousPoint(Point newPoint){
		this.dangerousPoints.add(newPoint);
	}
	
	public boolean isDangerousPoint(Point newPoint){
		if(this.dangerousPoints.contains(newPoint)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public void clearNumberOfAntTypes(){
		this.numberOfAntTypes.replace("Worker", 0);
		this.numberOfAntTypes.replace("Drone", 0);
		this.numberOfAntTypes.replace("Scout", 0);
	}
	
	public void updateNumberOfAntTypes(String type){
		this.numberOfAntTypes.replace(type, this.numberOfAntTypes.get(type) + 1);
	}
}




class ResourcePoint{
	int resources;
	Cell resourceCell;

	public ResourcePoint(int resources, Cell resourceCell) {
		this.resources = resources;
		this.resourceCell = resourceCell;
	}
	
	public int getResources(){
		return this.resources;
	}
	
	public Cell getResourceCell(){
		return this.resourceCell;
	}
}