import java.util.Iterator;
/**
 * Chengxiang Xiong V00838781
 * Assignment 5
 * Program name: BSTRefBased.java
 */
public class BSTRefBased extends AbstractBinaryTree 
    implements Iterable<WordRefs>
{
    private TreeNode root;

	
    public BSTRefBased() {
        root = null;
    }


    public BSTRefBased(WordRefs item,
        AbstractBinaryTree left,
        AbstractBinaryTree right)
    {
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }


    public boolean isEmpty() {
        return (root == null);
    }

	
    public void makeEmpty() {
        root = null;
    }


    protected TreeNode getRoot() {
        return root;
    }


    protected void setRoot(TreeNode r) {
        this.root = r;
    }


    public WordRefs getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("getRootItem() on empty tree");
        }

        return root.item;
    }
    

    public void setRootItem(WordRefs item) {
        if (root == null) {
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }


    public void attachLeft(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachLeft to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new TreeException("attachLeft to occupied left child");
        }

        root.left = new TreeNode(item, null, null);

        return;
    } 


    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) {
            throw new TreeException("attachLeftSubtree to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }

        root.left = left.getRoot();
        left.makeEmpty();

        return;    
    }


    public void attachRight(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachRight to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new TreeException("attachRight to occupied right child");
        }

        root.right = new TreeNode(item, null, null);

        return;
    } 

    
    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) {
            throw new TreeException("attachRightSubtree to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }

        root.right = right.getRoot();
        right.makeEmpty();

        return;
    }

	
    public AbstractBinaryTree detachLeftSubtree()
        throws TreeException 
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.left);
        root.left = null;

        return result;
    }


    public AbstractBinaryTree detachRightSubtree()
        throws TreeException
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.right);
        root.right = null;

        return result;
    }
    

    public void insert(String word) {
    	root = insertItem(root,word);
    }

	
    protected TreeNode insertItem(TreeNode r, String word) {
    	if(r == null){
    		return new TreeNode(new WordRefs(word));
    	}
    	if(word.compareTo(r.item.getWord())<0){
    		r.left = insertItem(r.left,word);
    	}else{
    		r.right= insertItem(r.right,word);
    	}
    	return r;
    }
    

    public WordRefs retrieve(String word) {
    	TreeNode t = retrieveItem(root,word);
    	if(t == null){
    		return null;
    	}else{
    		return t.item;
    	}
    }
    

    protected TreeNode retrieveItem(TreeNode r, String word) {
    	if(r == null){
    		return null;
    	}
    	
		String nodeItem = r.item.getWord();
    	if(word.compareTo(nodeItem) == 0){
    		return r;
    	}
    	else if(word.compareTo(nodeItem) < 0){
    		return retrieveItem(r.left,word);
    	}else{
    		return retrieveItem(r.right,word);
    	}
    	
    }
    

    public void delete(String word) {
    	root = deleteItem(root,word);
    }


    protected TreeNode deleteItem(TreeNode r, String word) {
    	if(r == null){
    		return r;
    	}
    	
		String nodeItem = r.item.getWord();
    	if(word.compareTo(nodeItem) == 0 ){
    		r = deleteNode(r);
        }else if(word.compareTo(nodeItem) < 0){
            r.left = deleteItem(r.left,word);
        }else{
        	r.right = deleteItem(r.right,word);
        	}
        	
    	return r;
    }


    protected TreeNode deleteNode(TreeNode node) {
    	if(node.left == null && node.right == null){
    		return null;
    	}
    	else if(node.left == null){
    		return node.right;
    	}
    	else if(node.right == null){
    		return node.left;
    	}else{
    		WordRefs replacement = findLeftMost(node.right).item;
    		node.item = replacement;
    		node.right = deleteLeftMost(node.right);
    		return node;
    	}
    }


    protected TreeNode findLeftMost(TreeNode node) {
    	if(node.left == null){
    		return node;
    	}
    		return findLeftMost(node.left);
    }	


    protected TreeNode deleteLeftMost(TreeNode node) {
    	if(node.left == null){
    		return node.right;
    	}
    		node.left = deleteLeftMost(node.left);
    		return node;
    }

    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
    }

     public static void main(String args[]) {
        BSTRefBased t;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;

        message = "Test 1: inserting 'humpty' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 2: inserting 'humpty', 'dumpty', 'sat' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
    }
} 
