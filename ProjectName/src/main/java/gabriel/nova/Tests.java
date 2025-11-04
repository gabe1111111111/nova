package gabriel.nova;
/**
 * this will hold test cases for various parts of the project
 * @author Gabriel Lacey
 */

import java.util.Iterator;

public class Tests {

        public static void main(String[] args) {
        testAddAndGet();
        testGetOutOfBounds();
        testEnsureCapacity();
        testCleanRemovesNulls();
        testIterator();
        testIteratorStopsAtNull();
        testEmptyIterator();
        testCleanOnEmpty();
        System.out.println("\nAll manual tests finished.");
    }

    static void testAddAndGet() {
        System.out.println("testAddAndGet ");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);

        list.add(20);
        list.add(30);
        System.out.println("Size: " + list.size());
        System.out.println("Elements:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + list.get(i));
        }
    }

    static void testGetOutOfBounds() {
        System.out.println("\n testGetOutOfBounds ");
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        try {
            list.get(5);
            System.out.println("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught expected exception: " + e);
        }
    }

    static void testEnsureCapacity() {
        System.out.println("\n testEnsureCapacity");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println("Size after adding 20 elements: " + list.size());
        System.out.println("First: " + list.get(0) + ", Last: " + list.get(19));
    }

    static void testCleanRemovesNulls() {
        System.out.println("\n testCleanRemovesNulls");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add(null);
        list.add("b");
        list.add(null);
        System.out.println("Before clean: size=" + list.size());
        list.clean();
        System.out.println("After clean: size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + list.get(i));
        }
    }

    static void testIterator() {
        System.out.println("\n testIterator ");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("Iterated: " + it.next());
        }
    }

    static void testIteratorStopsAtNull() {
        System.out.println("\n testIteratorStopsAtNull ");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(null);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("Iterated: " + it.next());
        }
        System.out.println("Iterator stopped at null as expected.");
    }

    static void testEmptyIterator() {
        System.out.println("\n testEmptyIterator ");
        ArrayList<Double> list = new ArrayList<>();
        Iterator<Double> it = list.iterator();
        System.out.println("Has next? " + it.hasNext());
    }

    static void testCleanOnEmpty() {
        System.out.println("\n testCleanOnEmpty ");
        ArrayList<Integer> list = new ArrayList<>();
        list.clean();
        System.out.println("Cleaned empty list, size=" + list.size());
    }

}
