package risk.util;

/**
 * Pair allows for a C++ style pair.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	2017-02-11
 */
public class Pair<Left, Right> {
	protected final Left left;
	protected final Right right;
	
	/**
	 * 
	 * @param left Left hand of pair
	 * @param right Right hand of pair
	 */
	public Pair(Left left, Right right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Retrieves the left hand.
	 * @return Retrieves the left hand.
	 */
	public Left getLeft() { return left; }
	
	/**
	 * Retrieves the right hand.
	 * @return Retrieves the right hand.
	 */
	public Right getRight() { return right; }
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() { return left.hashCode() ^ right.hashCode(); }
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Pair)) return false;
		@SuppressWarnings("unchecked")
		Pair<Left, Right> pairo = (Pair<Left, Right>) o;
		return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
	}
}
