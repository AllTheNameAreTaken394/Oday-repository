
public class PhotoManager {
	  private LinkedList<Photo> photos;
	  
	  
	  
	  public PhotoManager() {
	        this.photos = new LinkedList<>();
	    }
	  
	  public LinkedList<Photo> getPhotos() {
		  return photos;
		}

	        
	  
	  public void addPhoto(Photo p) {
	        if (p != null && !containsPhoto(p.getPath())) {
	            photos.insert(p);  
	        }
	    }
	  
	  public void deletePhoto(String path) {
	        LinkedList<Photo> tempPhotos = new LinkedList<>();
	        photos.findFirst();
	        while (photos.current != null) {
	            Photo photo = photos.retrieve();
	            if (!photo.getPath().equals(path)) {
	                tempPhotos.insert(photo);
	            }
	            photos.findNext();
	        }
	        photos = tempPhotos; 
	    }
	  
	  private boolean containsPhoto(String path) {
	        photos.findFirst();
	        while (photos.current != null) {
	            if (photos.retrieve().getPath().equals(path)) {
	                return true;
	            }
	            photos.findNext();
	        }
	        return false;
	    }
	  
	  
	  public void printAllPhotos() {
	        photos.findFirst();
	        while (photos.current != null) {
	            photos.retrieve().printPhotoDetails();
	            photos.findNext();
	        }
	    }
}
