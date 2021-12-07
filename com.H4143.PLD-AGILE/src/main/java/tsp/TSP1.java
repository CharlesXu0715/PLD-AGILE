package tsp;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {
	
	@Override
	protected double bound(Integer currentVertex, Collection<Integer> unvisited) {
		double lowerBound=0;
		double lowestValue=Double.MAX_VALUE;
		for (int i : unvisited) {
			if (g.getCost(currentVertex, i)<lowestValue) {
				lowestValue=g.getCost(currentVertex, i);
			}
		}
		lowerBound+=lowestValue;
		for (int i : unvisited) {
			lowestValue=Double.MAX_VALUE;
			for (int j : unvisited) {
				if (g.getCost(i, j)<lowestValue) {
					lowestValue = g.getCost(i, j);
				}
			}
			if (g.getCost(i, 0)<lowestValue) {
				lowestValue = g.getCost(i, 0);
			}
			lowerBound+=lowestValue;
		}
		return lowerBound;
	}

	@Override
	protected Iterator<Integer> iterator(Integer currentVertex, Collection<Integer> unvisited, Graph g) {
		return new TreeMapIter(unvisited, currentVertex, g);
	}

}
