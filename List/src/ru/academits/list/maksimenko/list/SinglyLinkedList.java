package ru.academits.list.maksimenko.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
    }

    public int getSize() {
        return count;
    }

    public void add(T data) {
        ++count;

        insertByIndex(count, data);
    }

    public T getFirsData() {
        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("The index has a negative value: " + index);
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Index greater than the length of the list: index " + index +
                    ", size " + count);
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        checkIndex(index);

        ListItem<T> item = null;
        int i = 0;

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            if (index == i) {
                item = current;
            }

            ++i;
        }

        return item;
    }

    public T getDataByIndex(int index) {
        ListItem<T> item = getItemByIndex(index);

        return item.getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> item = getItemByIndex(index);

        T oldValue = item.getData();

        item.setData(data);

        return oldValue;
    }

    public T deleteItemByIndex(int index) {
        T data;

        if (index == 0) {
            data = head.getData();

            deleteFirstItem();

            count--;

            return data;
        }

        ListItem<T> previous = getItemByIndex(index - 1);
        ListItem<T> current = previous.getNext();

        data = current.getData();

        previous.setNext(current.getNext());

        --count;

        return data;
    }

    public void insertByBeginning(T data) {
        head = new ListItem<>(data, head);

        ++count;
    }

    public void insertByIndex(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            insertByBeginning(data);

            return;
        }

        ListItem<T> newItem = new ListItem<>(data);
        ListItem<T> previous = getItemByIndex(index - 1);

        newItem.setNext(previous.getNext());
        previous.setNext(newItem);
    }

    public boolean deleteItemByValue(T data) {
        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (data.equals(current.getData())) {
                assert previous != null;
                previous.setNext(current.getNext());

                count--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
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
        SinglyLinkedList<T> copy = new SinglyLinkedList<>(new ListItem<>(head.getData()));

        for (ListItem<T> current = head.getNext(), currentCopy = copy.head; current != null; current = current.getNext(), currentCopy = currentCopy.getNext()) {
            ListItem<T> copyItem = new ListItem<>(current.getData());

            currentCopy.setNext(copyItem);
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            stringBuilder.append(current.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.toString();
    }
}