package data.node;

public class Test1 {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(55);
        singleLinkedList.add(11);
        singleLinkedList.add(44);
        singleLinkedList.add(22);
        singleLinkedList.add(33);
        //singleLinkedList.remove(2);
        singleLinkedList.add(5,"99");
        //System.out.println(singleLinkedList.get(3));
        for (int i = 0; i < singleLinkedList.size(); i++) {
            System.out.println(singleLinkedList.get(i));
        }
    }
}
