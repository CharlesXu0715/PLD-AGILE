package tsp;

import model.Path;
import model.VisitPoint;

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
	 * @return the VisitPoint at vertex <code>i</code>
	 */
	
	public abstract VisitPoint getVertex(int i);
	
	/**
	 * @param i
	 * @return the vertex index of vertex <code>i</code> in the complete map
	 */
	
	public abstract int getVertexIndex(int i);
	
	/**
	 * @param i: the intersection to find index
	 * @return the index of intersection <code>i</code> as stored in the graph
	 */
	
	public abstract int getGraphVertexIndex(VisitPoint i);
	
	/**
	 * @param i 
	 * @param j 
	 * @return a list of edges that go from vertex <code>i</code> to vertex <code>j</code>
	 */
	
	public abstract Path getPath(int i, int j);
	
	/**
	 * @param pickup: the VisitPoint corresponding to the pickup address and time
	 * @param delivery: the VisitPoint corresponding to the delivery address and time
	 */
	
	public abstract void addVisitPoints(VisitPoint pickup, VisitPoint delivery);
	
	/**
	 * remove the VisitPoints from the graph, also deleting the paths to and from said VisitPoints
	 * @param pickup: the VisitPoint corresponding to the pickup address and time
	 * @param delivery: the VisitPoint corresponding to the delivery address and time
	 */
	
	public abstract void removeVisitPoints(VisitPoint pickup, VisitPoint delivery);

}
