package tsp;

import java.util.Collection;
import java.util.Iterator;

public class SeqIter implements Iterator<Integer> {

	private Integer[] candidates;
	private int nbCandidates;

	/**
	 * Create an iterator to traverse the set of vertices in <code>unvisited</code> 
	 * which are successors of <code>currentVertex</code> in <code>g</code>
	 * Vertices are traversed in the same order as in <code>unvisited</code>
	 * @param unvisited
	 * @param currentVertex
	 * @param g
	 */
	public SeqIter(Collection<Integer> unvisited, int currentVertex, Graph g){
		this.candidates = new Integer[unvisited.size()];
		for (Integer s : unvisited){
			if (g.isArc(currentVertex, s))
				candidates[nbCandidates++] = s;
		}
	}
	
	/**
	 * find if there is next ones
	 * @return the existence of next candidates
	 */
	@Override
	public boolean hasNext() {
		return nbCandidates > 0;
	}

	/**
	 * find the next one
	 * @return the next candidate
	 */
	@Override
	public Integer next() {
		nbCandidates--;
		return candidates[nbCandidates];
	}

	@Override
	public void remove() {}


}
