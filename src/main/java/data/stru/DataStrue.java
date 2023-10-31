package data.stru;

public class DataStrue {

    public static void main(String[] args) {
        Node lastNode = new Node(10);
        Node node9 = new Node(9, lastNode);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node6 = new Node(6, node7);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node headNode = new Node(1, node2);
        lastNode.next = headNode;
    }

    /**
     *  约瑟夫问题
     * @param headNode 首节点
     * @param lastNode  尾节点
     * @param size      节点长度
     * @param start     报数位置
     * @param count     报数个数
     */
    public static void josephus(Node headNode,Node lastNode,int size,int start,int count){
        //1.判断空表情况
        if(headNode == null){
            throw new NullPointerException("空指针异常");
        }
        //2.处理start和count不合法的情况
        if(start < 1 || start > size || count < 1){
            throw new IllegalArgumentException("参数不合法");
        }
        //3.把设置为start的小孩开始报数，并将headNode移向该位置
        for(int i = 0;i < start - 1;i++){
                headNode = headNode.next;
                lastNode = lastNode.next;
        }

        while(size != 0){
            for(int i = 0;i < count - 1;i++){
                headNode = headNode.next;
                lastNode = lastNode.next;
            }
        }
    }

    public static class Node{
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
