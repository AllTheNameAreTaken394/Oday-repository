public class BSTNode {
	    String tag; 
	    LinkedList<Photo> photos; 
	    BSTNode left, right; 

	    
	    public BSTNode(String tag) {
	        this.tag = tag;
	        this.photos = new LinkedList<>();
	        this.left = this.right = null;

}
}