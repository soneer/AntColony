import java.util.Scanner;

public class RunSimulation {

	public RunSimulation() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {
		MySQL test= new MySQL();
		GenerateCellGrid worldGridGen = new GenerateCellGrid(10, 10) ;
		Cell[][] worldGrid = worldGridGen.getCellGrid();
		QueenAnt queen = new QueenAnt(worldGrid);

		Cell currentCell = worldGrid[queen.getCoordinates().x][queen.getCoordinates().y];
		while(currentCell.getCellType()!="H")
		{
			queen.act(worldGrid);
			currentCell = worldGrid[queen.getCoordinates().x][queen.getCoordinates().y];
			Thread.sleep(1000);
		}

		int n; 
		Scanner input = new Scanner(System.in);

		while (queen.getColonyStatus()!="Dead") {
			Thread.sleep(1000);
			queen.act(worldGrid);
			System.out.println("--------------------Report--------------------");
			System.out.println("Hive Location:"+queen.pNN.hive.printPoint()+" Resources:"+queen.pNN.hive.getResources());
			System.out.println("Queen Status-> Health:"+queen.getHealth()+" Colony Size::"+queen.pNN.getAntsInTheColony().size());

			System.out.println("Destination Cell:"+queen.pNN.getDestinationCell().xPos+","+queen.pNN.getDestinationCell().yPos+" Type:"+queen.pNN.getDestinationCell().getCellType());
			//	for(int i = 0; i < queen.getChildrenDroneSize(); i++){
		//		System.out.println("Drone"+i+" Status: Health"+queen.getChildrenDrones().get(i).getHealth()+" Location:"+queen.getChildrenDrones().get(i).printPoint());

		//	}
			Ant currentAnt;
			for(int currentAntID: queen.pNN.getAntsInTheColony().keySet()){
				 currentAnt =  (queen.pNN.getAntsInTheColony()).get(currentAntID);
				 if(currentAnt.getHealthStatus()){
				System.out.println(currentAnt.getTypeOfAnt()+currentAnt.getId()+" Status: Health:"+currentAnt.getHealth()+" Resources:"+currentAnt.getResourcesCarried()+" Location:"+currentAnt.printPoint());
				 }
				 else
				 {
					 System.out.println("DEAD"+currentAnt.getTypeOfAnt()+currentAnt.getId()+" Status: Health:"+currentAnt.getHealth()+" Resources:"+currentAnt.getResourcesCarried()+" Location:"+currentAnt.printPoint());
						
				 }
			//	for(ResourcePoint x : queen.pNN.resourcePoints){
				//	System.out.println("      Resource Point"+ x.resourceCell.xPos+","+x.resourceCell.yPos+ "  Resources"+x.resources);
			//	}
				
				if(!test.containsEntry(currentAnt.getId())){
					test.addEntry(currentAnt.getId(),currentAnt.getTypeOfAnt(), currentAnt.getResourcesCarried(), currentAnt.getHealth(), currentAnt.getAttack());
				}
				else{
					test.updateEntry(currentAnt.getId(),currentAnt.getTypeOfAnt(), currentAnt.getResourcesCarried(), currentAnt.getHealth(), currentAnt.getAttack());
					
				}
			}

		}

	}

}
