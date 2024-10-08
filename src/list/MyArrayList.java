package list;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] array;
    private int size;

    /* конструктор без параметров */
    public MyArrayList() {
        array =  new Object[DEFAULT_CAPACITY];
    }

    /* конструктор с размером дин.массива */
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Неправильный размер: " + capacity);
        else if (capacity == 0) {
            array = EMPTY_ARRAY;
        }
        else {
            array = new Object[capacity];
        }

    }

    /* конструктор с коллекцией */
    public MyArrayList(Collection<? extends T> coll) {
        Object[] arr = coll.toArray();
        if ((size = arr.length) != 0) {
            array = Arrays.copyOf(arr, size, Object[].class);
        }
        else {
            array = EMPTY_ARRAY;
        }
    }

    public MyArrayList(MyArrayList<? extends T> coll) {
        Object[] arr = coll.toArray();
        if ((size = arr.length) != 0) {
            array = Arrays.copyOf(arr, size, Object[].class);
        }
        else {
            array = EMPTY_ARRAY;
        }
    }

    /* добавить элемент */
    public boolean add(T elem) {
        if (size + 1 >= array.length) {
            array = createNewArray();
        }
        array[size] = elem;
        growSize();

        return true;
    }
    /* добавить элемент в определенный индекс */
    public boolean add(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Неверный индекс: " + index + ", size = " + size);
        }
        else if (index == size) {
            array = createNewArray();
        }

        addElementToArray(index, elem);
        growSize();

        return true;
    }

    /* создать новый масиив большей вместимости */
    private Object[] createNewArray() {
        Object[] newArr = new Object[(int) (array.length * 1.5 + 1)];

        for (int i = 0; i < size; i++)
            newArr[i] = array[i];

        return newArr;
    }

    /* private добавление элемента в массив */
    private void addElementToArray(int index, T elem) {
        if (index == size) {
            array[size] = elem;
            return;
        }
        for (int i = size; i >= index; --i) {
            array[i + 1] = array[i];
        }
        array[index] = elem;
    }

    /* получить элемент */
    public T get(int index) {
        checkIndex(index);

        return (T) array[index];
    }

    /* удалить элемент по индексу */
    public T remove(int index) {
        checkIndex(index);
        T temp = (T) array[index];

        removeElem(index);

        return temp;
    }

    /* проверка индекса для удаления и получения элемента */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Неверный индекс: " + index + ", size = " + size);

    }

    /* удалить элемент по ссылке на объект */
    public boolean remove(T elem) {
        if (elem == null)
            return false;
        else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(elem)) {
                    removeElem(i);
                    return true;
                }
            }
        }

        return false;
    }

    /* private удаление элемента */
    private void removeElem(int index) {
        if (index < size - 1) array[index] = null;

        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[size - 1] = null;

        downSize();
    }

    /* добавить элементы коллекции в мою реализацию */
    public boolean addAll(Collection<? extends T> coll) {
        if (coll.isEmpty())
            return false;
        Object[] a = coll.toArray();
        for (Object elem : a)
            this.add((T) elem);

        return true;
    }

    /* увелить size */
    private void growSize() { ++size; }

    /* понизить size */
    private void downSize() { --size; }

    /* получить размер коллекции */
    public int size() {
        return size;
    }
    /* получить массив из коллекции */
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    /* сортировка пузырьком с флагом */
    public void sort() {
        final Object[] copy = array;
        int copySize = size;
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < copySize; i++) {

                if (copy[i - 1] instanceof Comparable && copy[i] instanceof Comparable) {
                    Comparable obj1 = (Comparable) copy[i - 1];
                    Comparable obj2 = (Comparable) copy[i];
                    if (obj1.compareTo(obj2) > 0) {
                        isSorted = false;
                        Comparable temp = obj1;
                        obj1 = obj2;
                        obj2 = temp;
                        copy[i - 1] = obj1;
                        copy[i] = obj2;
                    }
                }
                else {
                    throw new ClassCastException("Data in the list is not comparable");
                }
            }
        }
        array = copy;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    /* static сортировка коллекции */
    public static void sort(MyArrayList<?> coll) {
        final Object[] copy = coll.array;
        int copySize = coll.size;
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < copySize; i++) {

                if (copy[i - 1] instanceof Comparable && copy[i] instanceof Comparable) {
                    Comparable obj1 = (Comparable) copy[i - 1];
                    Comparable obj2 = (Comparable) copy[i];
                    if (obj1.compareTo(obj2) > 0) {
                        isSorted = false;
                        Comparable temp = obj1;
                        obj1 = obj2;
                        obj2 = temp;
                        copy[i - 1] = obj1;
                        copy[i] = obj2;
                    }
                }
                else {
                    throw new ClassCastException("Data in the list is not comparable");
                }
            }
        }
        coll.array = copy;
    }
}
