
public class Photo {
	private String path;
    private LinkedList<String> tags;

	
    public Photo(String path, LinkedList<String> tags) {
        this.path = path;
        this.tags = new LinkedList<>();
        
        if (tags != null && !tags.empty()) {
            tags.findFirst();
            while (tags.current != null) {
                this.tags.insert(tags.retrieve());
                tags.findNext();
            }
        }
    }
	 

	public String getPath() {
	        return path;
	    }
	 
	  public LinkedList<String> getTags() {
	        return tags;
	    }
	  public void printPhotoDetails() {
	        System.out.println("Photo Path: " + path);
	        System.out.println("Tags: " + tags);
	    }
	  public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Photo Path: ").append(path + ",");
	        return sb.toString().trim();
	  }

}
