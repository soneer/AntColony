
import java.util.Random;

public class DroneAnt extends Ant {



	@Override
	public void setSuppliesCarried(int suppliesCarried) {
		// Drones Don't Carry Resources
		//super.setSuppliesCarried(suppliesCarried);
	}





	@Override
	public void setSpeed(int speed) {
		// Drones Stay In Hive
		//super.setSpeed(speed);
	}

	@Override
	public void act(Cell[][] worldGrid){
		this.health --;
		
	}


	public DroneAnt(int x, int y, PheromoneNeuralNet pNN) {
		super.health = (new Random()).nextInt(12 - 8 + 1) + 8;
		super.speed = 0;
		super.attack = 1;
		super.xPos = x;
		super.yPos = y;
		super.id = this.hashCode();
		super.typeOfAnt = "Drone";
		super.pNN = pNN;
	}

}
