package data.node;

public class Test2 {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(55);
        doubleLinkedList.add(22);
        doubleLinkedList.add(44);
        doubleLinkedList.add(33);
        doubleLinkedList.add(11);
        System.out.println("index:"+doubleLinkedList.get(3));
        for(int i = 0;i < doubleLinkedList.size();i++){
            System.out.println(doubleLinkedList.get(i));
        }
        System.out.println("result:"+4 / 2);
    }
}
