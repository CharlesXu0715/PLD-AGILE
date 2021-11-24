package tsp;

import java.util.List;

import model.Road;

public interface Graph {
	/**
	 * @return the number of vertices in <code>this</code>
	 */
	public abstract int getNbVertices();

	/**
	 * @param i 
	 * @param j 
	 * @return the cost of arc (i,j) if (i,j) is an arc; -1 otherwise
	 */
	public abstract double getCost(int i, int j);

	/**
	 * @param i 
	 * @param j 
	 * @return true if <code>(i,j)</code> is an arc of <code>this</code>
	 */
	public abstract boolean isArc(int i, int j);
	
	/**
	 * @param i
	 * @return the vertex index of vertex <code>i</code> in the complete map
	 */
	
	public abstract int getVertexIndex(int i);
	
	/**
	 * @param i 
	 * @param j 
	 * @return a list of edges that go from vertex <code>i</code> to vertex <code>j</code>
	 */
	
	public abstract List<Integer> getPath(int i, int j);

}
