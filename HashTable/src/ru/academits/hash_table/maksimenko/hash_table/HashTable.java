package ru.academits.hash_table.maksimenko.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;
    private int count;
    private int currentModification;

    /**
     * @noinspection unused
     */
    public HashTable() {
        //noinspection unchecked
        hashTable = new ArrayList[10];
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid table size entered" + size);
        }

        //noinspection unchecked
        hashTable = new ArrayList[size];
    }

    private int getIndex(Object o) {
        return Math.abs(o.hashCode() % hashTable.length);
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
        int index = getIndex(o);

        if (hashTable[index] != null) {
            ArrayList<T> list = hashTable[index];

            return list.contains(o);
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int modification = currentModification;
            int i = 0;
            int j = 0;
            int currentElementNumber = 0;
            ArrayList<T> list;

            @Override
            public boolean hasNext() {
                return currentElementNumber < count;
            }

            @Override
            public T next() {
                if (modification != currentModification) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                for (; ; i++) {
                    if (hashTable[i] != null) {
                        if (list == null) {
                            list = hashTable[i];
                        }

                        T value = list.get(j);

                        ++j;
                        ++currentElementNumber;

                        if (j == list.size()) {
                            j = 0;
                            ++i;

                            list = null;
                        }

                        return value;
                    }
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[count];

        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                for (T element : list) {
                    objects[i] = element;

                    ++i;
                }
            }

            if (i == count) {
                break;
            }
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < count) {
            //noinspection unchecked
            a = (T1[]) Arrays.copyOf(a, count, a.getClass());
        }

        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                for (T element : list) {
                    //noinspection unchecked
                    a[i] = (T1) element;

                    ++i;
                }
            }

            if (i == count) {
                break;
            }
        }

        if (i < a.length) {
            a[i] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        ++count;
        ++currentModification;

        if (hashTable[index] == null) {
            ArrayList<T> list = new ArrayList<>();

            list.add(t);

            hashTable[index] = list;

            return true;
        }

        ArrayList<T> list = hashTable[index];

        list.add(t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (hashTable[index] != null) {
            ArrayList<T> list = hashTable[index];

            if (list.remove(o)) {
                --count;
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
        for (Object element : c) {
            //noinspection unchecked
            add((T) element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int initialSize = count;

        int i = 0;

        while (i < hashTable.length) {
            if (hashTable[i] != null) {
                ArrayList<T> list = hashTable[i];

                for (int j = 0; j < list.size(); j++) {
                    if (c.contains(list.get(j))) {
                        list.remove(j);

                        --j;
                        --count;
                    }
                }

                if (list.size() == 0) {
                    hashTable[i] = null;
                }
            }

            ++i;
        }

        return initialSize != count;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = count;

        int i = 0;

        while (i < hashTable.length) {
            if (hashTable[i] != null) {
                ArrayList<T> list = hashTable[i];

                for (int j = 0; j < list.size(); j++) {
                    if (!c.contains(list.get(j))) {
                        list.remove(j);

                        --count;
                        --j;
                    }
                }

                if (list.size() == 0) {
                    hashTable[i] = null;
                }
            }

            ++i;
        }

        return initialSize != count;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                hashTable[i] = null;
            }
        }

        count = 0;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                stringBuilder.append("[");

                for (T element : list) {
                    stringBuilder.append(element).append(", ");
                }

                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

                stringBuilder.append("], ");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}