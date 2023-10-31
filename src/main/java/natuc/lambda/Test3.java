package natuc.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("oldec");
        list.add("oldecn");
        list.add("oldeb");
        list.add("olpden");
        list.stream().filter(data -> data.startsWith("o")).filter(data -> data.endsWith("n")).collect(Collectors.toList()).forEach(System.out::println);
    }
}
