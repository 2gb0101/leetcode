//public class DomHelper {
//    public static void save(List<Person> persons, OutputStream out) throws Exception {
//        Integer[] integers = new Integer[]{1, 2, 3};
//        List<Integer> integerList = Arrays.asList(integers);
//        for (int i : integerList) {
//            System.out.print(i + " "); //输出：1 2 3
//        }
//        integerList.set(0, 5);
//        for (Object o : integerList) { //通过Arrays.asList()生成的List
//            System.out.print(o + " "); //输出：5 2 3
//        }
//        for (Object o : integers) {   //原本的数组
//            System.out.print(o + " "); //输出：5 2 3
//        }
//    }
//}