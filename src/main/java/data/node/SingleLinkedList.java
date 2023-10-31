package data.node;

public class SingleLinkedList {

    //头节点
    private Node headNode;
    //尾节点
    private Node lastNode;
    //长度
    private int size;

    public int size(){
        return this.size;
    }

    /**
     * 添加元素
     * @param data
     */
    public void add(Object data){
        //把需要的数据封装为节点对象
        Node node = new Node(data);
        //判断节点是否为空节点
        //如果是则设置为头节点和尾节点
        if(headNode == null){
            headNode = node;
            lastNode = node;
            size++;
        }else {
            //如果不是则让尾节点指向它
            lastNode.next = node;
            lastNode = node;
            size++;
        }
    }

    /**
     * 根据索引获取元素
     * @param index
     * @return
     */
    public Object get(int index){
        //判断索引是否合法
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("索引异常,index:"+index);
        }
        Node node = node(index);
        return node.data;
    }

    /**
     * 创建一个临时节点辅助节点查询，根据序号获取对应的节点对象
     * @param index 序号
     * @return
     */
    private Node node(int index){
        Node tempNode = headNode;
        for(int i = 0;i < index;i++){
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    /**
     * 删除元素
     * @param index
     */
    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("索引异常，index:"+index);
        }
        //如果是头节点
        if(index == 0){
            Node nextNode = headNode.next;
            headNode.next = null;
            headNode = nextNode;

        }else if(index == size - 1){
            Node preNode = node(index -1);
            preNode.next = null;
            lastNode = preNode;
        }
        //1.找到索引前一个位置和后一个位置，并将他们与索引元素的连接断开，并将他们相连接
        //找到前一个位置
        Node preNode = node(index - 1);
        Node nextNode = preNode.next.next;
        preNode.next.next = null;
        preNode.next = nextNode;
        size--;
    }

    /**
     * 根据索引插入元素
     * @param index
     * @param element
     */
    public void add(int index,Object element){
        //1.判断索引是否合法
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("索引异常,index:"+index);
        }
        //2.把需要的数据封装成节点对象
        Node node = new Node(element);
        //3.索引是首节点的情况
        if(index == 0){
            //3.1设置索引指向首节点
            node.next = headNode;
            //3.2将首节点指向索引
            headNode = node;
        }
        //4.索引是尾节点的情况
        else if(index == size){
            lastNode.next = node;
            lastNode = node;
        }
        //5.索引是中间的情况
            //5.1获取索引前一个节点
            Node preNode = node(index -1);
            //5.2获取index所对应的节点
            Node curNode = preNode.next;
            //5.3前一个节点指向索引
            preNode.next = node;
            //5.4索引指向后一个节点
            node.next = curNode;
            //更新size值
            size++;
    }

    /**
     * 节点类
     */
    public static class Node{
        //传入的数据
        private Object data;
        //用于保存下一节点的地址值
        private Node next;
        public Node(Object data){
            this.data = data;
        }
    }
}
