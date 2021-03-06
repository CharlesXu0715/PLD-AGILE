package tsp;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class TreeMapIter implements Iterator<Integer> {

	private TreeMap<Double,Integer> sortingTree;

	/**
	 * Create an iterator to traverse the set of vertices in <code>unvisited</code> 
	 * which are successors of <code>currentVertex</code> in <code>g</code>
	 * Vertices are traversed in the order of lowest cost to highest as in <code>unvisited</code>
	 * @param unvisited
	 * @param currentVertex
	 * @param g
	 */
	public TreeMapIter(Collection<Integer> unvisited, int currentVertex, Graph g){
		this.sortingTree = new TreeMap<Double,Integer>();
		double cost;
		for (Integer s : unvisited){
			if (g.isArc(currentVertex, s)) {
				cost = g.getCost(currentVertex, s);
				sortingTree.put(cost,s);
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return !sortingTree.isEmpty();
	}

	@Override
	public Integer next() {
		return sortingTree.pollFirstEntry().getValue();
	}

	@Override
	public void remove() {}
}
