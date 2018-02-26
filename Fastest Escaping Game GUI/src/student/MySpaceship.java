package student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import controllers.Driver;
import models.Node;
import models.NodeStatus;
import models.RescueStage;
import models.ReturnStage;
import models.Spaceship;

/** An instance implements the methods needed to complete the mission */
public class MySpaceship extends Spaceship{
	private HashMap<Long,Boolean> visited1=new HashMap<Long,Boolean>();
	private HashMap<Node, Boolean> visited2=new HashMap<Node, Boolean>();
	private int count=0;//# of times passing through hostile planets
	//private int total=3;
	/**
	 * Explore the galaxy, trying to find the missing spaceship that has crashed
	 * on Planet X in as little time as possible. Once you find the missing
	 * spaceship, you must return from the function in order to symbolize that
	 * you've rescued it. If you continue to move after finding the spaceship
	 * rather than returning, it will not count. If you return from this
	 * function while not on Planet X, it will count as a failure.
	 * 
	 * At every step, you only know your current planet's ID and the ID of all
	 * neighboring planets, as well as the ping from the missing spaceship.
	 * 
	 * In order to get information about the current state, use functions
	 * currentLocation(), neighbors(), and getPing() in RescueStage. You know
	 * you are standing on Planet X when foundSpaceship() is true.
	 * 
	 * Use function moveTo(long id) in RescueStage to move to a neighboring
	 * planet by its ID. Doing this will change state to reflect your new
	 * position.
	 */
	
	@Override
	public void rescue(RescueStage state) {
		// TODO : Find the missing spaceship
		// base case
		if(state.foundSpaceship()==true) return;
		
		//record every visited planet
		
		if(!visited1.containsKey(state.currentLocation()))
			visited1.put(state.currentLocation(), true);
		
		double p=0;
		
		
		//find the neighbor that's nearest to Planet X
		long nearest=-1;
		Collection<NodeStatus> neigh=state.neighbors();
		for(NodeStatus n: neigh){
			if (!visited1.containsKey(n.getId())){
				//if(Driver.shouldPrint==true) Driver.outPrintln("here!"+n.getId());
				if(n.getPingToTarget()>p){
					
				   p=n.getPingToTarget();
				   nearest=n.getId();}
				
			}
		}
		//if there's no neighbor closer to Planet X than current location
		//if(nearest==-1){
			
		//}
		//if there's neighbor closer to Planet X than current location
		//move on recursively
		//else 
		if(nearest!=-1){
			state.moveTo(nearest);
			visited1.put(nearest, true);
			rescue(state);
		}
		
		
		
	}
	
    
	/**
	 * Get back to Earth, avoiding hostile troops and searching for speed
	 * upgrades on the way. Traveling through 3 or more planets that are hostile
	 * will prevent you from ever returning to Earth.
	 *
	 * You now have access to the entire underlying graph, which can be accessed
	 * through ScramState. currentNode() and getEarth() will return Node objects
	 * of interest, and getNodes() will return a collection of all nodes in the
	 * graph.
	 *
	 * You may use state.grabSpeedUpgrade() to get a speed upgrade if there is
	 * one, and can check whether a planet is hostile using the isHostile
	 * function in the Node class.
	 *
	 * You must return from this function while on Earth. Returning from the
	 * wrong location will be considered a failed run.
	 *
	 * You will always be able to return to Earth without passing through three
	 * hostile planets. However, returning to Earth faster will result in a
	 * better score, so you should look for ways to optimize your return.
	 */
	@Override
	public void returnToEarth(ReturnStage state) {
		// TODO: Return to Earth
		
		Node ear=state.getEarth();
		//Collection<Node> all=state.allNodes();
		
		//base case
		Node curr=state.currentNode();
		if(Driver.shouldPrint==true) Driver.outPrintln("here!"+curr.name);
		if(curr==ear) return;
		
		//record every visited planet
		
		if(!visited2.containsKey(curr.getId()))
			visited2.put(curr, true);
		
		//if exists, grab speed upgrade
		if(curr.hasSpeedUpgrade()==true) state.grabSpeedUpgrade();
		
		
		
		
		Node nextPlanet=nextNode(curr,ear);
		if(curr.isConnectedTo(nextPlanet)==true){
		   state.moveTo(nextPlanet); 
		   returnToEarth(state);
		}
	
	}
	
	
	
	/**nextNode: find the best path to earth**/
	public Node nextNode(Node current,Node earth){
		HashMap<Node, Integer> neighbor=current.getNeighbors();
		Node next=null;
		Node best=null;
		Node fair=null;
		Node normal=null;
		Node worst=null;
		List<Node> path = Paths.shortestPath(current, earth);
		if(Driver.shouldPrint==true) Driver.outPrintln("path is ok!");
		for(Node n: neighbor.keySet()){
			if(!visited2.containsKey(n) && n.isConnectedTo(current)){
				if(Driver.shouldPrint==true) Driver.outPrintln(n.name);
				if(path.contains(n)){
					if(n.isHostile()==false) return n;
					else if(count<2) {count++;return n;}					
				}
				
				List<Node> path1=Paths.shortestPath(n, earth);
				int dist1=Paths.pathDistance(path1);
				//"best" planet has speed upgrade and isn't hostile
				// find the nearest one
				if(n.isHostile()==false && n.hasSpeedUpgrade()==true){
					if(best==null) 
						{
						  best=n;
						  if(Driver.shouldPrint==true) Driver.outPrintln("1st best!");
						}
						
					    
					else{
						List<Node> path2=Paths.shortestPath(best, earth);
						int dis2=Paths.pathDistance(path2);
						if(Driver.shouldPrint==true) Driver.outPrintln("path2 is ok!");
						if(dis2>dist1) {best=n;}
					}
				}
				//"fair" planet has speed upgrade but is also hostile
				//we can move to it if "count < 2"
				else if(n.isHostile()==true && n.hasSpeedUpgrade()==true){
					if(fair==null) 
						{
						  fair=n;
						  if(Driver.shouldPrint==true) Driver.outPrintln("1st fair!");
						 
						}	
					else{
						List<Node> path3=Paths.shortestPath(fair, earth);
						int dis3=Paths.pathDistance(path3);
						if(Driver.shouldPrint==true) Driver.outPrintln("path3 is ok!");
						if(dis3>dist1) {fair=n;dis3=dist1;}
					}							
				}
				else if(n.isHostile()==false && n.hasSpeedUpgrade()==false){
					if(normal==null) 
						{normal=n;	
						if(Driver.shouldPrint==true) Driver.outPrintln("1st normal!");
						}
					else{
						List<Node> path4=Paths.shortestPath(normal, earth);
						int dis4=Paths.pathDistance(path4);
						if(Driver.shouldPrint==true) Driver.outPrintln("path4 is ok!");
						if(dis4>dist1) {
							if(Driver.shouldPrint==true) Driver.outPrintln("where's the problem?");
							normal=n;
						    if(Driver.shouldPrint==true) Driver.outPrintln("where's the problem?");
						}
						else{if(Driver.shouldPrint==true) Driver.outPrintln("where's the problem?");}
						
					}							
				}
				else if(n.isHostile()==true && n.hasSpeedUpgrade()==false){
					if(worst==null) 
						{worst=n;
						if(Driver.shouldPrint==true) Driver.outPrintln("1st worst!");}	
					else{
						List<Node> path5=Paths.shortestPath(worst, earth);
						int dis5=Paths.pathDistance(path5);
						if(Driver.shouldPrint==true) Driver.outPrintln("path5 is ok!");
						if(dis5>dist1) {worst=n;}
					}							
				}
				
			}
		}
		if(Driver.shouldPrint==true && next==null) Driver.outPrintln("next is null now!");
		if(best!=null){
			if(best.getDistance(earth)<1.5*current.getDistance(earth) || count>=2) 
				return best;			
		}
		if(normal!=null && next==null){
			if(Driver.shouldPrint==true) Driver.outPrintln("where's the problem?");
			if(normal.getDistance(earth)<current.getDistance(earth) || count>=2)
				next=normal;		
		}
		if(fair!=null){
			if(next!=null){
				if(count<2 && next.getDistance(earth)>fair.getDistance(earth))
					{next=fair;}		
			}
			else if(count<2) next=fair;
		}
		if(worst!=null){
			if(next!=null){
				if(count<2 && next.getDistance(earth)>worst.getDistance(earth)){
					next=worst;
				}
				//else next=worst;
			}
			else if(count<2) next=worst;
		}
		
		if(next.isHostile()==true) count++;
		if(next==null && Driver.shouldPrint==true) Driver.outPrintln("null next!!!");
		return next;
	}
	

	
	
	

}