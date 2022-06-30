import java.util.Arrays;

class MyArrayListPractice {
    public static void main(String[] args) {
        // ArrayList Initialization
        MyArrayList myArrayList = new MyArrayList<String>();

        // ADD
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        // PRINT ARRAY
        myArrayList.printArray();

        // SIZE
        System.out.println(myArrayList.size());

        // ITEM AT
        System.out.println(myArrayList.at(0));

        // ADD AT INDEX
        myArrayList.addAtIndex(1, 9);
        myArrayList.printArray();

        // REMOVE AT INDEX
        myArrayList.removeAtIndex(1);
        myArrayList.printArray();

        myArrayList.add(5);
        myArrayList.add(7);
        myArrayList.printArray();

        // POP
        System.out.println(myArrayList.pop());
        myArrayList.printArray();

        myArrayList.add(9);
        myArrayList.add(9);
        myArrayList.add(9);
        myArrayList.printArray();

        // REMOVE ITEM
        myArrayList.remove(9);
        myArrayList.printArray();

        // PREPEND
        myArrayList.prepend(7);
        myArrayList.printArray();

        myArrayList.add(0);
        myArrayList.add(5);

        // FIND
        System.out.println(myArrayList.find(5));
    }
}

class MyArrayList<T> {
    private T[] asArray;

    public MyArrayList() {
        asArray = (T[]) new Object[0];
    }

    public void printArray() {
        for (T num: asArray) {
            System.out.print(num);
        }
        System.out.println("");
    }

    public int size() {
        return asArray.length;
    }

    public boolean isEmpty() {
        return asArray.length == 0;
    }

    public void add(T item) {
        int curr_length = asArray.length;
        T[] new_array = Arrays.copyOf(asArray, curr_length + 1);
        new_array[curr_length] = item;
        asArray = new_array;
    }

    public T at(int index) {
        if (index < 0 || index > size() || isEmpty()) {
            throw new IllegalArgumentException("Item Not Found!");
        }
        return asArray[index];
    }

    public T pop() {
        T lastElement = asArray[asArray.length - 1];
        T[] new_array = Arrays.copyOf(asArray, asArray.length - 1);
        asArray = new_array;
        return lastElement;
    }

    public void addAtIndex(int index, T number) {
        T[] array1 = Arrays.copyOfRange(asArray, 0, index);
        T[] array2 = Arrays.copyOfRange(asArray, index, asArray.length);
        T[] new_array = Arrays.copyOf(array1, array1.length + 1);
        new_array[index] = number;
        T[] merged_array = (T[]) new Object[new_array.length + array2.length];
        System.arraycopy(new_array, 0, merged_array, 0, new_array.length);
        System.arraycopy(array2, 0, merged_array, new_array.length, array2.length);
        asArray = merged_array;
    }

    public void prepend(T item) {
        addAtIndex(0, item);
    }

    public void removeAtIndex(int index) {
        T[] array1 = Arrays.copyOfRange(asArray, 0, index);
        T[] array2 = Arrays.copyOfRange(asArray, index + 1, asArray.length);
        T[] merged_array = (T[]) new Object[array1.length + array2.length];
        System.arraycopy(array1, 0, merged_array, 0, array1.length);
        System.arraycopy(array2, 0, merged_array, array1.length, array2.length);
        asArray = merged_array;
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
