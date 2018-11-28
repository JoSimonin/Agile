package optimodlyon.agile.algorithmic;

import java.awt.Paint;
import java.io.Console;
import java.util.*;
import javafx.util.*;


import org.springframework.context.support.StaticApplicationContext;

//TODO : Commenter et mettre en forme cette merde parce que c'est vraiment pas beau;

public class TSP {

	public static void main(String[] args) {
		HashMap<Long, Float> successors1 = new HashMap<Long, Float>();
		successors1.put((long)2, (float)8);
		successors1.put((long)3, (float)7);
		successors1.put((long)4, (float)6);

		HashMap<Long, Float> successors2 = new HashMap<Long, Float>();
		successors2.put((long)1, (float)2);
		successors2.put((long)3, (float)3);
		successors2.put((long)4, (float)1);

		HashMap<Long, Float> successors3 = new HashMap<Long, Float>();
		successors3.put((long)1, (float)2);
		successors3.put((long)2, (float)3);
		successors3.put((long)4, (float)1);

		HashMap<Long, Float> successors4 = new HashMap<Long, Float>();
		successors4.put((long)1, (float)8);
		successors4.put((long)2, (float)4);
		successors4.put((long)3, (float)5);

		HashMap<Long, HashMap<Long, Float>> map = new HashMap<Long, HashMap<Long, Float>>();
		map.put((long)1, successors1);
		map.put((long)2, successors2);
		map.put((long)3, successors3);
		map.put((long)4, successors4);
		doTSP(map, (long)1);
		
	}
	
	/**
     * 
     * @param map a map containing idNodes as Keys and a map of (idDestinations, distance) as Values
     * @param idWarehouse id of Entrepot in map
     * @return PathLength, which is a class created for TSP which is a pair of an ordonned array symbolising the path and its length
     */
	public static PathLength doTSP(HashMap<Long, HashMap<Long, Float>> map, Long idWarehouse){
		ArrayList<PathLength> possiblePaths = startTSP(map, idWarehouse);
		System.out.println("Liste des chemins possibles : " + possiblePaths);
		PathLength shortestPath = findShortestPath(possiblePaths);
		System.out.println("Chemin le plus court trouvé : " + shortestPath.getPath() + "de longueur : " + shortestPath.getLength());
		return shortestPath;
	}
	
	public static ArrayList<PathLength> startTSP(HashMap<Long, HashMap<Long, Float>> unordoredMap, Long idWarehouse) {
		ArrayList<PathLength> finalResults = new ArrayList<PathLength>(); //This list will contain all the resulting pair of (path, length) possible.
		HashMap<Long, Float> successors = new HashMap<Long, Float>(unordoredMap.get(idWarehouse));
		unordoredMap.remove(idWarehouse);
		ArrayList<Long> currentPath = new ArrayList();
    	Float currentLength;
    	Iterator it = successors.entrySet().iterator();
    	while (it.hasNext()) {
	    	currentPath.clear();
	    	currentPath.add(idWarehouse);
	    	currentLength =(float) 0;
	        Map.Entry currentPair = (Map.Entry)it.next();
	        it.remove(); // avoids a ConcurrentModificationException
	        HashMap<Long, Float> currentSuccessors = new HashMap<Long, Float>(unordoredMap.get(currentPair.getKey()));
	        HashMap<Long, HashMap<Long, Float>> newUnordoredMap = copyMap(unordoredMap);
	        newUnordoredMap.remove(currentPair.getKey());
        	currentLength+=(float)currentPair.getValue();
        	currentPath.add((long)currentPair.getKey());
	        finalResults = nextNode(newUnordoredMap, currentSuccessors, currentPath, currentLength, finalResults);
    	}
    	return(finalResults);
	}
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<PathLength> nextNode(HashMap<Long, HashMap<Long, Float>> unordoredMap, HashMap<Long, Float> currentSuccessors, ArrayList<Long> currentPath, Float currentLength, ArrayList<PathLength> finalResults) {
//		System.out.println(currentPath);
//		System.out.println(currentLength);
//		System.out.println(unordoredMap);
//		System.out.println(currentSuccessors);
    	if(unordoredMap.isEmpty()) {
			PathLength pathFound = new PathLength(currentPath, currentLength);
			finalResults.add(pathFound);
			return finalResults;
		}
		else {
			Iterator it = currentSuccessors.entrySet().iterator();
			while (it.hasNext()) {
		        Map.Entry newPair = (Map.Entry)it.next();
		        it.remove();
		        if(unordoredMap.containsKey(newPair.getKey())) {
		        	ArrayList<Long> newPath = (ArrayList) currentPath.clone();
		        	Float newLength = currentLength;
		        	newLength+=(float)newPair.getValue();
		        	newPath.add((long)newPair.getKey());
			        HashMap<Long, Float> newSuccessors = new HashMap<Long, Float>(unordoredMap.get(newPair.getKey()));
			        HashMap<Long, HashMap<Long, Float>> newUnordoredMap =copyMap(unordoredMap);
			        newUnordoredMap.remove(newPair.getKey());
			        finalResults = nextNode(newUnordoredMap, newSuccessors, newPath, newLength, finalResults);
				}
			}
			return finalResults;
		}
	}
	
	public static HashMap<Long, HashMap<Long, Float>> copyMap(HashMap<Long, HashMap<Long, Float>> map){
		HashMap<Long, HashMap<Long, Float>> newMap = new HashMap<Long, HashMap<Long, Float>>();
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	    	HashMap<Long, Float> insideMap = new HashMap<Long, Float>(map.get(pair.getKey()));
	        newMap.put((long)pair.getKey(), insideMap);
		}
		return newMap;
	}
	
	public static PathLength findShortestPath(ArrayList<PathLength> possiblePaths) {
		if(possiblePaths.size()==0) return null;
		else {
			PathLength bestPath = possiblePaths.get(0);
			for(PathLength path : possiblePaths) {
				if(path.getLength()<bestPath.getLength()) bestPath = path;
			}
			return bestPath;
		}
	}
	
}
