public class InvIndexPhotoManager {
    private BST invertedIndex;

    public InvIndexPhotoManager() {
        this.invertedIndex = new BST(); 
    }

    public void addPhoto(Photo p) {
        p.getTags().findFirst();
        while (p.getTags().current != null) {
            String tag = p.getTags().retrieve();
            invertedIndex.insert(tag, p); 
            p.getTags().findNext();
        }
    }

    public void deletePhoto(String path) {
       
        LinkedList<Photo> allPhotos = invertedIndex.getAllPhotos(); 
        allPhotos.findFirst();
        while (allPhotos.current != null) {
            Photo photo = allPhotos.retrieve();
            if (photo.getPath().equals(path)) {
                photo.getTags().findFirst();
                while (photo.getTags().current != null) {
                    String tag = photo.getTags().retrieve();
                    invertedIndex.delete(tag, path);  
                    photo.getTags().findNext();
                }
                break;  
            }
            allPhotos.findNext();
        }
    }

    public LinkedList<Photo> getAllPhotos() {
        return invertedIndex.getAllPhotos();
    }
}
