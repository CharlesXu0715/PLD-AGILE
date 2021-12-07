package tsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeIter implements Iterator<Integer> {

	private TreeSet<Double> sortingTree;
	private HashMap<Double,Integer> indexTranslateMap;
	private int nbCandidates=0;

	/**
	 * Create an iterator to traverse the set of vertices in <code>unvisited</code> 
	 * which are successors of <code>currentVertex</code> in <code>g</code>
	 * Vertices are traversed in the order of lowest cost to highest as in <code>unvisited</code>
	 * @param unvisited
	 * @param currentVertex
	 * @param g
	 */
	public TreeIter(Collection<Integer> unvisited, int currentVertex, Graph g){
		this.sortingTree = new TreeSet<Double>();
		this.indexTranslateMap = new HashMap<Double,Integer>();
		double cost;
		for (Integer s : unvisited){
			if (g.isArc(currentVertex, s)) {
				cost = g.getCost(currentVertex, s);
				sortingTree.add(cost);
				indexTranslateMap.put(cost, s);
				nbCandidates++;
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return nbCandidates > 0;
	}

	@Override
	public Integer next() {
		nbCandidates--;
		return indexTranslateMap.get(sortingTree.pollFirst());
	}

	@Override
	public void remove() {}


}