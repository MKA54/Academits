package ru.academits.maksimenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);

        ++size;
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void add(T data) {
        insertByIndex(size, data);
    }

    public T getFirstData() {
        checkListSize();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size - 1) + ". Index = " + index);
        }
    }

    private void checkListSize() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
    }

    private ListItem<T> getByIndex(int index) {
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
        return getByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> item = getByIndex(index);

        T oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> previous = getByIndex(index - 1);
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
            addFirst(data);

            return;
        }

        ListItem<T> newItem = new ListItem<>(data);
        ListItem<T> previous = getByIndex(index - 1);

        newItem.setNext(previous.getNext());
        previous.setNext(newItem);

        ++size;
    }

    public boolean deleteByData(T data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            deleteFirst();

            return true;
        }

        for (ListItem<T> current = head.getNext(), previous = head; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(data, current.getData())) {
                assert previous != null;
                previous.setNext(current.getNext());

                size--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirst() {
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
        if (size == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> copy = new SinglyLinkedList<>(head.getData());

        for (ListItem<T> current = head.getNext(), currentCopy = copy.head; current != null; current = current.getNext(),
                currentCopy = currentCopy.getNext()) {
            ListItem<T> itemCopy = new ListItem<>(current.getData());

            currentCopy.setNext(itemCopy);
        }

        copy.setSize(size);

        return copy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            stringBuilder.append(current.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}