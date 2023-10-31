package data.node;

public class DoubleLinkedList {

    private Node headNode;
    private Node lastNode;
    private int size;

    public int size(){
        return this.size = size;
    }

    /**
     * 添加元素
     * @param element
     */
    public void add(Object element){
        Node node = new Node(element);
        //处理空表的情况
        if(headNode == null){
            headNode = node;
            lastNode = node;
        }
        else {
            lastNode.next = node;
            node.pre = lastNode;
            lastNode = node;
        }
        size++;
    }

    /**
     * 根据索引获取元素
     * @param index
     */
    public Object get(int index){
        if(index < 0 || index > size){
            throw  new IndexOutOfBoundsException("索引异常,index:"+index);
        }
        Node node = node(index);
        return node.data;
    }

    /**
     * 获取当前索引节点对象
     * @param index
     * @return
     */
    private Node node(int index){
        //如果索引在前半区从前往后索引

        if(index < size / 2){
            //建一个临时节点辅助查询
            Node nextNode = headNode;
            for(int i = 0;i < index;i++){
                nextNode = nextNode.next;
            }
            return nextNode;
        }
        //如果索引在后半区从后往前索引
        else {
            Node preNode = lastNode;
            for(int i = size -1;i > index;i--){
                preNode = preNode.pre;
            }
            return preNode;
        }
    }

    /**
     * 定义节点类
     */
    public static class Node{
        private Object data;
        private Node next;
        private Node pre;
        public Node(Object data) {
            this.data = data;
        }
    }
}
