import java.util.Arrays;

class MyArrayListPractice {
    public static void main(String[] args) {
        // ArrayList Initialization
        MyArrayList myArrayList = new MyArrayList<Integer>();

        // ADD
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        // PRINT ARRAY
        System.out.print("current array: ");
        myArrayList.printArray();

        myArrayList.add(4);
        System.out.println("Add 4");
        myArrayList.printArray();

        // POP
        System.out.println("POP: " + myArrayList.pop());
        myArrayList.printArray();

        // SIZE
        System.out.println("Current Size: " + myArrayList.size());

        // ITEM AT
        System.out.println("GET ITEM AT INDEX 0: " + myArrayList.at(0));

        // ADD AT INDEX
        System.out.println("ADD 4 AT INDEX 2");
        myArrayList.addAtIndex(2, 4);
        myArrayList.printArray();

        System.out.print("ADD 5, 6, AND 7: ");
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.printArray();

        System.out.println("current capacity: " + myArrayList.capacity());

        // REMOVE AT INDEX
        myArrayList.removeAtIndex(6);
        myArrayList.printArray();
        System.out.println("current capacity after tail removal: " + myArrayList.capacity());

        System.out.println("ADD TRIPLE 9 AT THE END: ");
        myArrayList.add(9);
        myArrayList.add(9);
        myArrayList.add(9);
        myArrayList.printArray();
        System.out.println("current capacity: " + myArrayList.capacity());

        // REMOVE ITEM
        myArrayList.remove(9);
        myArrayList.printArray();
        System.out.println("current capacity: " + myArrayList.capacity());

        // PREPEND
        myArrayList.prepend(7);
        myArrayList.printArray();
        System.out.println("current capacity: " + myArrayList.capacity());

        // FIND
        System.out.println("NUMBER 5 IS AT INDEX: " + myArrayList.find(5));
        myArrayList.printArray();
    }
}

class MyArrayList<T> {
    private T[] asArray;
    private static final int DEFAULT_CAPACITY = 3;

    public MyArrayList() {
        asArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void printArray() {
        for (int i = 0; i < size(); i++) {
            System.out.print(asArray[i]);
        }
        System.out.println("");
    }

    public int size() {
        int count = 0;
        for (T item : asArray) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }

    public int capacity() {
        return asArray.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void extendCapacity(T[] arr) {
        int oldCapacity = asArray.length;
        int newCapacity = oldCapacity * 2;
        T[] new_array = Arrays.copyOf(arr, newCapacity);
        asArray = new_array;
    }

    public void reduceCapacity(T[] arr) {
        T[] new_array = Arrays.copyOf(arr, capacity() / 2);
        asArray = new_array;
    }

    public T at(int index) {
        if (index < 0 || index > size() || isEmpty()) {
            throw new IllegalArgumentException("Item Not Found!");
        }
        return asArray[index];
    }

    public void add(T item) {
        if (size() == capacity()) {
            extendCapacity(asArray);
        }
        asArray[size()] = item;
    }

    public void addAtIndex(int index, T number) {
        if (index >= 0 && index < size()) {
            if (size() + 1 > capacity()) extendCapacity(asArray);

            T[] array1 = Arrays.copyOfRange(asArray, 0, index);
            T[] array2 = Arrays.copyOfRange(asArray, index, asArray.length);

            T[] merged_array = (T[]) new Object[array1.length + array2.length];
            System.arraycopy(array1, 0, merged_array, 0, array1.length);
            merged_array[index] = number;
            System.arraycopy(array2, 0, merged_array, array1.length + 1, array2.length - 1);
            asArray = merged_array;
        } else {
            throw new IllegalArgumentException("Index is out of bound!");
        }
    }

    public T pop() {
        T lastElement = asArray[size() - 1];
        asArray[size() - 1] = null;
        if (size() == capacity() / 2) {
            reduceCapacity(asArray);
        }
        return lastElement;
    }

    public void removeAtIndex(int index) {
        if (index >= 0 && index < size()) {
            T[] array1 = Arrays.copyOfRange(asArray, 0, index);
            T[] array2 = Arrays.copyOfRange(asArray, index + 1, asArray.length);
            T[] merged_array = (T[]) new Object[array1.length + array2.length + 1]; // extra null at the end
            System.arraycopy(array1, 0, merged_array, 0, array1.length);
            System.arraycopy(array2, 0, merged_array, array1.length, array2.length);
            asArray = merged_array;
            if (size() == capacity() / 2) {
                reduceCapacity(asArray);
            }
        } else {
            throw new IllegalArgumentException("Index is out of bound!");
        }
    }

    public void prepend(T item) {
        addAtIndex(0, item);
    }

    public void remove(T item) {
        int index = 0;
        while (index < asArray.length) {
            if (asArray[index] == item) {
                removeAtIndex(index);
            } else {
                index++;
            }
        }
    }

    public int find(T item) {
        for (int i = 0; i < asArray.length; i++) {
            if (asArray[i] == item) return i;
        }
        return -1;
    }

}
