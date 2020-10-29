package ru.academits.maksimenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;

        ++size;
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        insertByIndex(size, data);

        ++size;
    }

    public T getFirsData() {
        checkListSize();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            if (size == 0) {
                throw new IndexOutOfBoundsException("The list is empty");
            }

            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size - 1) + ". Index = " + index);
        }
    }

    private void checkListSize() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        checkIndex(index);

        ListItem<T> item = null;
        int i = 0;

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            if (index == i) {
                item = current;

                break;
            }

            ++i;
        }

        return item;
    }

    public T getDataByIndex(int index) {
        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> item = getItemByIndex(index);

        T oldValue = item.getData();

        item.setData(data);

        return oldValue;
    }

    public T deleteItemByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> previous = getItemByIndex(index - 1);
        ListItem<T> current = previous.getNext();

        T data = current.getData();

        previous.setNext(current.getNext());

        --size;

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    public void insertByIndex(int index, T data) {
        if (size != index) {
            checkIndex(index);
        }

        if (index == 0) {
            if (head != null) {
                addFirst(data);

                return;
            }

            head = new ListItem<>(data);

            return;
        }

        ListItem<T> newItem = new ListItem<>(data);
        ListItem<T> previous = getItemByIndex(index - 1);

        newItem.setNext(previous.getNext());
        previous.setNext(newItem);
    }

    public boolean deleteItemByValue(T data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            deleteFirstItem();

            return true;
        }

        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(data, current.getData())) {
                assert previous != null;
                previous.setNext(current.getNext());

                size--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        checkListSize();

        T data = head.getData();

        head = head.getNext();

        size--;

        return data;
    }

    public void reverse() {
        ListItem<T> previous = null;
        ListItem<T> current = head;

        while (current != null) {
            ListItem<T> next = current.getNext();

            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            copy.add(current.getData());
        }

        return copy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{ ");

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            stringBuilder.append(current.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(" }");

        return stringBuilder.toString();
    }
}