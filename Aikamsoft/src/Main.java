import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(Integer.valueOf(4));

        List untypedList = integerList;
        untypedList.add("1234");

        print(untypedList);

        Integer x = (Integer) untypedList.get(1);
    }

    public static void print(List<Integer> list){
        System.out.println(list);
    }
}