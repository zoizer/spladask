package risk.util;

public class Pair<Left, Right> {
	protected final Left left;
	protected final Right right;
	
	public Pair(Left left, Right right) {
		this.left = left;
		this.right = right;
	}
	
	public Left getLeft() { return left; }
	public Right getRight() { return right; }
	
	@Override
	public int hashCode() { return left.hashCode() ^ right.hashCode(); }
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Pair)) return false;
		@SuppressWarnings("unchecked")
		Pair<Left, Right> pairo = (Pair<Left, Right>) o;
		return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
	}
}
