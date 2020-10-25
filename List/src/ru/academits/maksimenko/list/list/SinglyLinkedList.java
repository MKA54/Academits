package ru.academits.maksimenko.list.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;

        ++count;
    }

    public int getSize() {
        return count;
    }

    public void add(T data) {
        insertByIndex(count, data);

        ++count;
    }

    public T getFirsData() {
        checkListSize();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + count + ". Index = " + index);
        }
    }

    private void checkListSize() {
        if (count == 0) {
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

        --count;

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        ++count;
    }

    public void insertByIndex(int index, T data) {
        if (count != index) {
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
        if (count == 0) {
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

                count--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        checkListSize();

        T data = head.getData();

        head = head.getNext();

        count--;

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
        if (count == 0) {
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