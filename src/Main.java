import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
//        RBTree<Integer> tree = new RBTree<>();
//        System.out.println(tree.size());
//        tree.add(50);
//        System.out.println(tree.size());
//        tree.add(5);
//        System.out.println(tree.size());
//        tree.add(30);
//        System.out.println(tree.size());
//        tree.add(34);
//        System.out.println(tree.size());
//        tree.add(56);
//        System.out.println(tree.size());
//        tree.add(12);
//        System.out.println(tree.size());
//        tree.add(25);
//        System.out.println(tree.size());
//        tree.add(78);
//        System.out.println(tree.size());
//        tree.add(1);
//        System.out.println(tree.size());
//        tree.add(4);
//       // tree.remove(30);
//        //tree.remove(333);
//        LinkedList list = new LinkedList<Integer>();
//        list.add(1);
//        list.add(4);
//        list.add(5);
//        list.add(12);
//        list.add(25);
//        list.add(30);
//        list.add(34);
//        list.add(50);
//        list.add(56);
//        list.add(78);
//        tree.removeAll(list);
//   //     tree.remove(1);
////        tree.remove(4);
//        if(tree.isEmpty())
//            System.out.println("blalba");
//        //for (Integer aTree : tree) System.out.println(aTree);
        RBTree<Integer> rbTreeForRetain = new RBTree<>();
        rbTreeForRetain.add(1);
        rbTreeForRetain.add(4);
        rbTreeForRetain.add(5);
        rbTreeForRetain.add(12);
        rbTreeForRetain.add(20);
        rbTreeForRetain.add(25);
        rbTreeForRetain.add(27);
        rbTreeForRetain.add(30);
        rbTreeForRetain.add(34);
        rbTreeForRetain.add(56);

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(20);
        list1.add(27);
        list1.add(56);
        rbTreeForRetain.retainAll(list1);
    }
}
