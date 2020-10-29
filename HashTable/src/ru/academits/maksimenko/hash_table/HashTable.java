package ru.academits.maksimenko.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] listsArray;
    private int size;
    private int currentModification;

    /**
     * @noinspection unused
     */
    public HashTable() {
        //noinspection unchecked
        listsArray = new ArrayList[10];
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The table size must be greater than 0, entered: " + size);
        }

        //noinspection unchecked
        listsArray = new ArrayList[size];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % listsArray.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        if (listsArray[index] != null) {
            return listsArray[index].contains(o);
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final private int initialModification = currentModification;
            private int tableIndex = 0;
            private int listIndex = 0;
            private int currentElementIndex = 0;

            @Override
            public boolean hasNext() {
                return currentElementIndex < size;
            }

            @Override
            public T next() {
                if (initialModification != currentModification) {
                    throw new ConcurrentModificationException("The list was changed during the execution of the pass");
                }

                if (!hasNext()) {
                    throw new NoSuchElementException("Going beyond the boundaries of the list, the element is not found");
                }

                while (true) {
                    if (listsArray[tableIndex] == null) {
                        ++tableIndex;

                        continue;
                    }

                    T value = listsArray[tableIndex].get(listIndex);

                    ++listIndex;
                    ++currentElementIndex;

                    if (listIndex == listsArray[tableIndex].size()) {
                        listIndex = 0;

                        ++tableIndex;
                    }

                    return value;
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];

        int i = 0;

        for (T list : this) {
            if (list == null) {
                continue;
            }

            for (T element : this) {
                objects[i] = element;

                ++i;
            }

            if (i == size) {
                break;
            }
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T1[]) Arrays.copyOf(a, size, a.getClass());
        }

        int index = 0;

        for (T list : this) {
            if (list != null) {
                for (T element : this) {
                    //noinspection unchecked
                    a[index] = (T1) element;

                    ++index;
                }
            }

            if (index == size) {
                break;
            }
        }

        if (size < a.length) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        if (listsArray[index] == null) {
            listsArray[index] = new ArrayList<>();
        }

        listsArray[index].add(t);

        ++size;
        ++currentModification;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (listsArray[index] != null) {
            if (listsArray[index].remove(o)) {
                --size;
                ++currentModification;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for (T element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        final int initialSize = size;
        int newSize = 0;

        for (int i = 0; i < listsArray.length; i++) {
            if (listsArray[i] == null) {
                continue;
            }

            int listLength = listsArray[i].size();

            if (listsArray[i].removeAll(c)) {
                ++currentModification;
            }

            if (listLength != listsArray[i].size()) {
                newSize += listsArray[i].size();
            }

            if (listsArray[i].size() == 0) {
                listsArray[i] = null;
            }
        }

        if (size != newSize) {
            size = newSize;
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        final int initialSize = size;
        int newSize = 0;

        for (int i = 0; i < listsArray.length; i++) {
            if (listsArray[i] == null) {
                continue;
            }

            int listLength = listsArray[i].size();

            if (listsArray[i].retainAll(c)) {
                ++currentModification;
            }

            if (listLength != listsArray[i].size()) {
                newSize += listsArray[i].size();
            }

            if (listsArray[i].size() == 0) {
                listsArray[i] = null;
            }
        }

        if (size != newSize) {
            size = newSize;
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        Arrays.fill(listsArray, null);

        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (ArrayList<T> list : listsArray) {
            if (list == null) {
                continue;
            }

            stringBuilder.append("[");

            for (T element : list) {
                stringBuilder.append(element).append(", ");
            }

            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

            stringBuilder.append("], ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}