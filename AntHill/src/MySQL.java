import java.sql.*;
public class MySQL { 
	static final String DB_URL = "jdbc:mysql://192.168.86.102:3306/AntColony";
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "1Qw71p31ty!";

	static Connection conn = null;
	static Statement stmt = null;

	public MySQL() {
		try
		{

			conn = DriverManager.getConnection (DB_URL,USER,PASS);
			System.out.println ("Database connection established");
			stmt = conn.createStatement();
			clearDB();

		}
		catch (Exception e)
		{
			e.printStackTrace();

		}



	}

	public void clearDB(){
		sendQuery("DELETE FROM CurrentColony");
	}
	public boolean containsEntry(int id){
		 try {
			ResultSet result = stmt.executeQuery("SELECT id FROM CurrentColony WHERE id="+id+";");
			if(!result.isBeforeFirst()){
                return false;
            }
           else{
        	   return true;
           }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		return false;
	}
	public void addEntry(int id, String type,int resources, int health,  int health_status )
	{
		sendQuery("INSERT INTO CurrentColony (id, AntType, Resources, Health, health_status) VALUES ("+id+",'"+type+"',"+resources+","+health+","+health_status+")");
	}
	public void updateEntry(int id, String type,int resources, int health,  int health_status )
	{
		//UPDATE `CurrentColony` SET `id`=[value-1],`AntType`=[value-2],`Resources`=[value-3],`Health`=[value-4],`health_status`=[value-5] WHERE CustomerID = 1;
		sendQuery("UPDATE CurrentColony SET AntType='"+type+"', Resources="+resources+", Health="+health+", health_status="+health_status+ " WHERE id ="+id);
	}
	public void sendQuery(String record){
		try {
			stmt.executeUpdate(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}