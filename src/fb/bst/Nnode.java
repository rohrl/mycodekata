package fb.bst;

import java.util.ArrayList;
import java.util.List;

public class Nnode<T> {
	public T val;
	public List<Nnode<T>> children = new ArrayList<Nnode<T>>(0);

	public boolean isLeaf() {
		return children.isEmpty();
	}
}
