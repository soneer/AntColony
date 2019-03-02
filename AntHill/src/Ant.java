
/*
 * 
 */
import java.awt.Point;
import java.util.Stack;


/**
 * The Class Ant.
 */
public class Ant {
	
	/** Represents an Ants Attack Power. */
	int attack = 0;
	
	/** Represents current location on the Matrix(world grid). */
	Cell current;
	
	/** Represents health of Ant. */
	int health = 0;
	
	
	/** Represents Unique id of an Ant using object HashCode. */
	int id;
	
	/** Represents the amount of Resources an Ant can carry. */
	int resourceCapacity = 0;
	
	/** Represents the amount of resources currently carried by an Ant. */
	int resourcesCarried = 0;
	
	/** Represents a speed of an Ant. Currently not used.*/
	int speed = 0;
	
	
	/** Represents the type of Ant. */
	String typeOfAnt;
	
	/** Represents what direction the Ant was heading. */
	int xDirection, yDirection = 0;
	
	/** Represents the current position of the Ant. */
	int xPos, yPos = 0;

	/** Common data network between Ants. */
	PheromoneNeuralNet pNN;
	
	/**
	 * Instantiates a new ant.
	 * Currently, there are no default Ant objects in the Ant Colony. 
	 */
	public Ant() {
		//No Default Ant For Now.
	}

	/**
	 * Act. This method dictates what an Ant will be doing every time it is the Ants turn to act.
	 *
	 * @param worldGrid The current world grid.
	 */
	public void act(Cell[][] worldGrid) {
		
	}

	//TODO: Implement Attack
	/**
	 * Attack.
	 *
	 * @param enemy the enemy
	 */
	public void attack(Predator enemy) {
		enemy.setHealth(enemy.getHealth() - attack);
	}

	/**
	 * Drop off resources. Ant drops whatever the Ant is holding.This can occur upon death, reaching the hive or needed to defend Hive.
	 *	The resources are then bound to the Cell.
	 */
	public void dropResources() {
		current.setResources(this.getResourcesCarried() + current.getResources());
		this.setSuppliesCarried(0);
	}

	/**
	 * Gets the attack power.
	 *
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Gets the coordinates.
	 *
	 * @return the coordinates
	 */
	public Point getCoordinates() {
		return new Point(xPos, yPos);
	}

	/**
	 * Gets the current cell.
	 *
	 * @return the current
	 */
	public Cell getCurrent() {
		return current;
	}

	/**
	 * Gets the Ant's health.
	 *
	 * @return the Ant's health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gets the Ant's health status.
	 * Returns true if Ant is alive. Returns False if Ant is dead.
	 * @return the Ant's health status
	 */
	public boolean getHealthStatus() {
		if (health <= 0) {
			return false;
		}
		return true;
	}


	/**
	 * Gets the Ant's unique id.
	 *
	 * @return the Ant's unique id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the speed.
	 *TODO: Implement Speed Logic
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Gets the resources carried by the Ant.
	 *
	 * @return the resources carried by the Ant
	 */
	public int getResourcesCarried() {
		return resourcesCarried;
	}

	/**
	 * Gets the supply capacity of the Ant.
	 *
	 * @return the supply capacity of the Ant.
	 */
	public int getResourceCapacity() {
		return resourceCapacity;
	}



	/**
	 * Gets the type of the Ant.
	 *
	 * @return the type of the Ant
	 */
	public String getTypeOfAnt() {
		return typeOfAnt;
	}

	/**
	 * Gets the x direction of the Ant, the horizontal direction.
	 *
	 * @return the x direction of the Ant, the horizontal direction
	 */
	public int getxDirection() {
		return xDirection;
	}

	/**
	 * Gets the x position of the Ant.
	 *
	 * @return the x position of the Ant
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Gets the y direction of the Ant, the vertical direction.
	 *
	 * @return the y direction of the Ant, the vertical direction
	 */
	public int getyDirection() {
		return yDirection;
	}

	/**
	 * Gets the y position of the Ant.
	 *
	 * @return the y position of the Ant
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * Mine. Mine a resource from current cell. Can only mine 1 resource per act.
	 */
	public void mine() {
		if (this.resourcesCarried < this.resourceCapacity && current.getResources() > 0) {
			this.resourcesCarried += 1;
			current.setResources(current.getResources() - 1);
		}
	}

	
	//TODO: Implement logical movement from Scout Ant data.
	/**
	 * Move. Decide where the Ant will move.
	 * @param worldGrid the world grid
	 */
	public void move(Cell[][] worldGrid) {

		int xMove = 0;
		int yMove = 0;
		// Move STAY
		if (worldGrid[this.xPos][this.yPos].getResources() > 0 && current.getCellType() != "H") {
			// Stay put
		} else if (validMoveCheck(worldGrid, this.xPos + xDirection, this.yPos + yDirection)
				&& (this.xPos + xDirection != this.xPos && this.yPos + yDirection != this.yPos)) {
			xMove = this.xPos + xDirection;
			yMove = this.yPos + yDirection;
		}
		// Move TOP_LEFT
		else if (validMoveCheck(worldGrid, this.xPos - 1, this.yPos - 1)) {
			xMove = this.xPos - 1;
			yMove = this.yPos - 1;
			xDirection = -1;
			yDirection = -1;
		}
		// Move TOP
		else if (validMoveCheck(worldGrid, this.xPos, this.yPos - 1)) {
			xMove = this.xPos;
			yMove = this.yPos - 1;
			xDirection = 0;
			yDirection = -1;
		}
		// Move TOP_RGHT
		else if (validMoveCheck(worldGrid, this.xPos + 1, this.yPos - 1)) {
			xMove = this.xPos + 1;
			yMove = this.yPos - 1;
			xDirection = 1;
			yDirection = -1;
		}
		// Move LEFT
		else if (validMoveCheck(worldGrid, this.xPos - 1, this.yPos)) {
			xMove = this.xPos - 1;
			yMove = this.yPos;
			xDirection = -1;
			yDirection = 0;
		}
		// Move RIGHT
		else if (validMoveCheck(worldGrid, this.xPos + 1, this.yPos)) {
			xMove = this.xPos + 1;
			yMove = this.yPos;
			xDirection = 1;
			yDirection = 0;
		}
		// Move BOTTOM_LEFT
		else if (validMoveCheck(worldGrid, this.xPos - 1, this.yPos + 1)) {
			xMove = this.xPos - 1;
			yMove = this.yPos + 1;
			xDirection = -1;
			yDirection = 1;
		}
		// Move BOTTOM
		else if (validMoveCheck(worldGrid, this.xPos, this.yPos + 1)) {
			xMove = this.xPos;
			yMove = this.yPos + 1;
			xDirection = 0;
			yDirection = 1;
		}
		// Move BOTTOM_RIGHT
		else if (validMoveCheck(worldGrid, this.xPos + 1, this.yPos + 1)) {
			xMove = this.xPos + 1;
			yMove = this.yPos + 1;
			xDirection = 1;
			yDirection = 1;
		} else {
			xMove = 0;
			yMove = 0;
		}
		this.xPos = xMove;
		this.yPos = yMove;
	}

	/**
	 * Prints the point.
	 *
	 * @return the string
	 */
	public String printPoint() {
		return "(" + getCoordinates().x + ", " + getCoordinates().y + ")";
	}

	/**
	 * Sets the attack power.
	 *
	 * @param attack the new attack power
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Sets the current cell.
	 *
	 * @param current the new current cell
	 */
	public void setCurrent(Cell current) {
		this.current = current;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the new health
	 */
	public void setHealth(int health) {
		this.health = health;
	}



	/**
	 * Sets the id for the Ant.
	 *
	 * @param id the new id for the Ant.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the resource capacity.
	 *
	 * @param resourceCapacity the new resource capacity
	 */
	public void setResourceCapacity(int resourceCapacity) {
		this.resourceCapacity = resourceCapacity;
	}

	//TODO: Implement speed
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Sets the resources carried.
	 *
	 * @param resourcesCarried the new resources carried by the Ant
	 */
	public void setSuppliesCarried(int resourcesCarried) {
		this.resourcesCarried = resourcesCarried;
	}



	/**
	 * Sets the type of ant.
	 *
	 * @param type the new type of ant
	 */
	public void setTypeOfAnt(String type) {
		this.typeOfAnt = type;
	}

	/**
	 * Sets the x direction.
	 *
	 * @param xDirection the new x direction
	 */
	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	/**
	 * Sets the x pos.
	 *
	 * @param xPos the new x pos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Sets the y direction.
	 *
	 * @param yDirection the new y direction
	 */
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	/**
	 * Sets the y pos.
	 *
	 * @param yPos the new y pos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Valid move check.
	 *
	 * @param worldGrid the world grid
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean validMoveCheck(Cell[][] worldGrid, int x, int y) {
		if (x >= 0 && y >= 0 && x < worldGrid.length && y < worldGrid[0].length) {
			return true;
		} else {
			return false;
		}
	}
}
