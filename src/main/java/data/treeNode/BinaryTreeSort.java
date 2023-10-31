package data.treeNode;


public class BinaryTreeSort<E extends Integer>{

    /**
     * 定义节点类
     * @param <E>
     */
    class Node<E extends Integer>{
        private E item; //存放元素
        private Node left; //存放左子树节点
        private Node right; //存放右子树节点

        Node(E item) {
            this.item = item;
        }
        /**
         * 添加节点
         * @param node
         */
        public void addNode(Node node){
            //如果新元素小于当前节点元素，则添加为当前节点左子节点
            if (node.item.intValue() < this.item.intValue()) {
                if(this.left == null)
                this.left = node;
                else
                    this.left.addNode(node);
            }else {
                if(this.right == null)
                    this.right = node;
                else
                    this.right.addNode(node);
            }
        }

        /**
         * 使用中序遍历
         */
        public void inorderTraversal(){
            if(this.left != null)this.left.inorderTraversal();
            System.out.println(this.item);
            if(this.right != null)this.right.inorderTraversal();
        }
    }
    private Node root;

    public void add(E element){
        Node<E> node = new Node<>(element);
        if(this.root == null)
            this.root = node;
        else
            this.root.addNode(node);
    }

    public void sort(){
    if(this.root == null)return ;
    this.root.inorderTraversal();
    }

    public static void main(String[] args) {
        BinaryTreeSort<Integer> nb = new BinaryTreeSort<>();
        nb.add(1);
        nb.add(2);
        nb.add(5);
        nb.add(4);
        nb.add(6);
        nb.add(3);
        nb.add(9);
        nb.sort();
    }



}
