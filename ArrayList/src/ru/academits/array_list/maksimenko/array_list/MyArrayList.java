package ru.academits.array_list.maksimenko.array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int count;
    private int currentModification;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Invalid argument entered: " + initialCapacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends T> collection) {
        //noinspection unchecked
        items = (T[]) collection.toArray();
        count = collection.size();
    }

    private void ensureCapacity(int size) {
        if (size + count >= items.length) {
            items = Arrays.copyOf(items, (size + count) * 2);
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int i = 0;
            final int modification = currentModification;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public T next() {
                if (modification != currentModification) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T value = items[i];

                ++i;

                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, count);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < items.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, count, a.getClass());
        }

        int i = 0;

        for (Object element : items) {
            //noinspection unchecked
            a[i] = (T1) element;

            ++i;
        }

        if (i < a.length) {
            a[i] = null;
        }

        return a;
    }

    @Override
    public boolean add(T element) {
        add(count, element);

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
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(count, c);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index != count) {
            checkIndex(index);
        }

        ensureCapacity(c.size());

        System.arraycopy(items, index, items, index + c.size(), count - index);

        for (T element : c) {
            items[index] = element;

            ++index;
        }

        ++currentModification;

        count += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int initialSize = count;

        for (int i = 0; i < count; i++) {
            if (c.contains(items[i])) {
                remove(i);

                --i;
            }
        }

        return initialSize != count;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = count;

        for (int i = 0; i < count; i++) {
            if (!c.contains(items[i])) {
                remove(i);

                --i;
            }
        }

        return initialSize != count;
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            items[i] = null;
        }

        ++currentModification;

        count = 0;
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
        if (index != count) {
            checkIndex(index);
        }

        System.arraycopy(items, index, items, index + 1, count - index);

        items[index] = element;

        ++currentModification;
        ++count;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T element = items[index];

        System.arraycopy(items, index + 1, items, index, count - index - 1);

        ++currentModification;
        --count;

        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = count - 1; i >= 0; i--) {
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
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (int i = 0; i < count; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Limits of acceptable values from 0, to " + count
                    + " entered: " + index);
        }
    }
}