package ru.academits.maksimenko.array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int currentModification;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("The list capacity must not be negative. Capacity: " + initialCapacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends T> collection) {
        //noinspection unchecked
        items = (T[]) collection.toArray();
        size = collection.size();
    }

    public void ensureCapacity(int size) {
        if (size >= items.length) {
            items = Arrays.copyOf(items, size);
        }
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
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            final private int initialModification = currentModification;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (initialModification != currentModification) {
                    throw new ConcurrentModificationException("The list was changed during the execution of the pass");
                }

                if (!hasNext()) {
                    throw new NoSuchElementException("Going beyond the boundaries of the list, the element is not found");
                }

                T value = items[index];

                ++index;

                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (items.length < a.length) {
            a[items.length] = null;
        }

        return a;
    }

    @Override
    public boolean add(T item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index >= 0) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index != size) {
            checkIndex(index);
        }

        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(c.size() + size);

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (T item : c) {
            items[i] = item;

            ++i;
        }

        ++currentModification;

        size += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        final int initialSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(items[i])) {
                remove(i);

                --i;
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        final int initialSize = size;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);

                --i;
            }
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        ++currentModification;

        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        ++currentModification;

        return items[index] = element;
    }

    @Override
    public void add(int index, T element) {
        if (index != size) {
            checkIndex(index);
        }

        if (size == 0) {
            ensureCapacity(size + 1);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;

        ++currentModification;
        ++size;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T item = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        --size;

        items[size] = null;

        ++currentModification;


        return item;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size - 1) + ". Index = " + index);
        }
    }
}