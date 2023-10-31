package data.arrayList;

public class TestO1 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("ss");
        arrayList.add("aa");
        arrayList.add("dd");
        arrayList.add("cc");
        arrayList.add("ff");
        arrayList.add(2,"mm");
        System.out.println(arrayList.toString());
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.hashCode());//460141958
    }
}
