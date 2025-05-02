
public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public PhotoManager getManager() {
        return manager;
    }

    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> filteredPhotos = new LinkedList<>();
        LinkedList<Photo> allPhotos = manager.getPhotos();

        allPhotos.findFirst(); 
        while (allPhotos.current != null) {
            Photo photo = allPhotos.retrieve();
            if (matchesCondition(photo)) {
                filteredPhotos.insert(photo); 
            }
            allPhotos.findNext(); 
        }

        return filteredPhotos;
    }


    public int getNbComps() {
        int comparisons = 0;
        String[] conditionTags = splitTags(condition);
        LinkedList<Photo> allPhotos = manager.getPhotos();

        allPhotos.findFirst();
        while (allPhotos.current != null) {
            LinkedList<String> photoTags = allPhotos.retrieve().getTags();
            comparisons += countMatches(photoTags, conditionTags);
            allPhotos.findNext();
        }

        return comparisons;
    }


    private boolean matchesCondition(Photo photo) {
        String[] conditionTags = splitTags(condition);
        LinkedList<String> photoTags = photo.getTags();

        for (String conditionTag : conditionTags) {
            boolean found = false;
            photoTags.findFirst();
            while (photoTags.current != null) {
                if (conditionTag.equals(photoTags.retrieve())) {
                    found = true;
                    break;
                }
                photoTags.findNext();
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private String[] splitTags(String condition) {
        return condition.split("\\s*AND\\s*");
    }

    private int countMatches(LinkedList<String> photoTags, String[] conditionTags) {
        int count = 0;
        for (String conditionTag : conditionTags) {
            boolean matched = false;
            photoTags.findFirst();
            while (photoTags.current != null) {
                if (conditionTag.equals(photoTags.retrieve())) {
                    matched = true;
                    break;
                }
                photoTags.findNext();
            }
            if (matched) {
                count++;
            }
        }
        return count;
    }
}
