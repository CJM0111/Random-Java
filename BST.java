/**
 * Class for a Binary Seach Tree
 * @author Chris McDonald
 */
public class BST
{
    private class treenode
    {
        public Comparable item;
        public treenode left, right;
    }
    treenode root;
    int count;
    
    public BST()
    { root=null; count=0; }
    public int size() { return count; }
    public boolean isEmpty()
    { return count==0; }
    public void makeEmpty()
    { root=null; count=0; }
	
    public void insert(Comparable x)
    {
        root=insert(x,root);
        count++;
    }
    private treenode insert(Comparable x,
                            treenode r)
    {
        // base case - empty tree
        if(r==null)
        {
            treenode tn=new treenode();
            tn.item=x;
            tn.left=tn.right=null;
            return tn;
        }
        // recursive cases
        //left side
        if(x.compareTo(r.item)<0)
        { r.left=insert(x,r.left); }
        // right side
        else
        { r.right=insert(x,r.right); }
        return r;
    }
    
    public void print() { print(root); }
    private void print(treenode r)
    {
        if(r==null) return;
        print(r.left);
        System.out.println(r.item);
        print(r.right);
    }
    
    public Comparable lookup(Comparable x)
    {
        return lookup(x,root);
    }
    private Comparable lookup(
                              Comparable x, treenode r)
    {
        // base case
        if(r==null) return null;
        if(x.compareTo(r.item)==0) // found it
        { return r.item; }
        // recursive cases
        else if(x.compareTo(r.item)<0)
        { return lookup(x,r.left); }
        // right side
        else
        { return lookup(x,r.right); }
    }
    
    public void delete(Comparable x)
    {
        root=delete(x,root);
    }
    private treenode delete(Comparable x,
                            treenode r)
    {
        // base case
        if(r==null) return null;
        if(x.compareTo(r.item)==0) // found it
        {
            // 3 cases
            // 0 children case
            if(r.left==null && r.right==null)
            { count--; return null; }
            // 1 child on the left
            else if(r.right==null)
            {
                count--;
                return r.left;
            }
            // 1 child on the right
            else if(r.left==null)
            {
                count--;
                return r.right;
            }
            // 2 children
            else
            {
                Comparable ins=findmin(r.right);
                r.item=ins;
                r.right=delete(ins,r.right);
                return r;
            }
        }
        // recursive cases
        else if(x.compareTo(r.item)<0)
        {
            r.left=delete(x,r.left);
            return r;
        }
        // right side
        else
        {
            r.right=delete(x,r.right);
            return r;
        }
    }
    private Comparable findmin(treenode r)
    {
        if(r==null) return null;
        if(r.left==null)
        { return r.item; }
        return findmin(r.left);
    }
    public Comparable getMin()
    {
        return findmin(root);
    }
    
    // iterator/traversal functions
    public static final int PREORDER=0;
    public static final int INORDER=1;
    public static final int POSTORDER=2;
    
    private Queue q;
    
    public void reset(int order)
    {
        q=new QueueLL();
        fillq(root,order);
    }
    
    private void fillq(treenode r, int order)
    {
        if(r==null) return;
        if(order==PREORDER)
            q.enqueue(r.item);
        fillq(r.left,order);
        if(order==INORDER)
            q.enqueue(r.item);
        fillq(r.right,order);
        if(order==POSTORDER)
            q.enqueue(r.item);
    }
    
    public Comparable getNext()
    {
        return (Comparable) q.dequeue();
    }
    
    public boolean hasNext()
    {
        return !q.isEmpty();
    }
}
