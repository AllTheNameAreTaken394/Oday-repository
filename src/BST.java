public class BST{
    private BSTNode root;

    public BST() {
        root = null;
    }

    public void insert(String tag, Photo p) {
        root = insert(root, tag, p);
    }

    private BSTNode insert(BSTNode node, String tag, Photo p) {
        if (node == null) {
            node = new BSTNode(tag);
            node.photos.insert(p); 
            return node;
        }

        if (tag.compareTo(node.tag) < 0) {
            node.left = insert(node.left, tag, p);  
        } else if (tag.compareTo(node.tag) > 0) {
            node.right = insert(node.right, tag, p);  
        } else {
            node.photos.insert(p);  
        }
        return node;
    }

    public void delete(String tag, String photoPath) {
        root = delete(root, tag, photoPath); 
    }

    private BSTNode delete(BSTNode node, String tag, String photoPath) {
        if (node == null) {
            return null; 
        }

        if (tag.compareTo(node.tag) < 0) {
            node.left = delete(node.left, tag, photoPath);  
        } else if (tag.compareTo(node.tag) > 0) {
            node.right = delete(node.right, tag, photoPath); 
        } else {
            node.photos.findFirst();
            while (node.photos.current != null) {
                if (node.photos.retrieve().getPath().equals(photoPath)) {
                    node.photos.remove(); 
                    break;
                }
                node.photos.findNext();
            }

            if (node.photos.empty()) {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                BSTNode successor = findMin(node.right);  
                node.tag = successor.tag;  
                node.photos = successor.photos; 
                node.right = delete(node.right, successor.tag, photoPath); 
            }
        }

        return node; 
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public LinkedList<Photo> getAllPhotos() {
        LinkedList<Photo> allPhotos = new LinkedList<>();
        inOrderTraversal(root, allPhotos);
        return allPhotos;
    }

    private void inOrderTraversal(BSTNode node, LinkedList<Photo> allPhotos) {
        if (node != null) {
            inOrderTraversal(node.left, allPhotos); 
            node.photos.findFirst();
            while (node.photos.current != null) {
                allPhotos.insert(node.photos.retrieve()); 
                node.photos.findNext();
            }
            inOrderTraversal(node.right, allPhotos);  
        }
    }
}