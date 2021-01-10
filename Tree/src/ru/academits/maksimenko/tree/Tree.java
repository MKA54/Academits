package ru.academits.maksimenko.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private TreeNode<T> previousNode;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<T> value = (Comparable<T>) data1;

        return value.compareTo(data2);
    }

    public void add(T data) {
        if (size == 0) {
            root = new TreeNode<>(data);

            ++size;

            return;
        }

        TreeNode<T> current = root;

        while (true) {
            int comparisonResult = compare(data, current.getData());

            if (comparisonResult < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(data));

                    break;
                }

                current = current.getLeft();

                continue;
            }

            if (current.getRight() == null) {
                current.setRight(new TreeNode<>(data));

                break;
            }

            current = current.getRight();
        }

        ++size;
    }

    private TreeNode<T> findNode(T data) {
        for (TreeNode<T> current = root; current != null; ) {
            int comparisonResult = compare(data, current.getData());

            if (comparisonResult == 0) {
                return current;
            }

            if (comparisonResult < 0) {
                if (current.getLeft() == null) {
                    return null;
                }

                previousNode = current;
                current = current.getLeft();

                continue;
            }

            if (current.getRight() == null) {
                return null;
            }

            previousNode = current;
            current = current.getRight();
        }

        return null;
    }

    public boolean contains(T data) {
        return findNode(data) != null;
    }

    public boolean remove(T data) {
        TreeNode<T> removingNode = findNode(data);

        if (removingNode == null) {
            return false;
        }

        if (removingNode == root) {
            removeRoot(removingNode);

            size--;

            return true;
        }

        if (removingNode.getLeft() == null && removingNode.getRight() == null) {
            if (previousNode.getRight() == removingNode) {
                previousNode.setRight(null);

                size--;

                return true;
            }

            previousNode.setLeft(null);

            size--;

            return true;
        }

        if (removingNode.getLeft() != null && removingNode.getRight() != null) {
            removeWithTwoChildren(removingNode);

            size--;

            return true;
        }

        if (previousNode.getLeft() == removingNode) {
            previousNode.setLeft(determineChild(removingNode));
        } else {
            previousNode.setRight(determineChild(removingNode));
        }

        size--;

        return true;
    }

    private void removeRoot(TreeNode<T> removingNode) {
        if (removingNode.getLeft() == null && removingNode.getRight() == null) {
            root = null;

            return;
        }

        if (removingNode.getRight() == null) {
            root = removingNode.getLeft();

            return;
        }

        removeWithTwoChildren(removingNode);
    }

    private void removeWithTwoChildren(TreeNode<T> removingNode) {
        TreeNode<T> parent = null;
        TreeNode<T> leftmost = removingNode.getRight();

        while (leftmost.getLeft() != null) {
            parent = leftmost;

            leftmost = leftmost.getLeft();
        }

        if (parent != null) {
            if (leftmost.getRight() != null) {
                parent.setLeft(leftmost.getRight());
            } else {
                parent.setLeft(null);
            }
        }

        if (previousNode != null) {
            if (previousNode.getLeft() == removingNode) {
                previousNode.setLeft(leftmost);
            } else {
                previousNode.setRight(leftmost);
            }
        }

        leftmost.setLeft(removingNode.getLeft());
        leftmost.setRight(removingNode.getRight() == leftmost
                ? leftmost.getRight() == null
                ? null
                : leftmost.getRight()
                : removingNode.getRight());

        if (removingNode == root) {
            root = leftmost;
        }
    }

    private TreeNode<T> determineChild(TreeNode<T> removingNode) {
        if (removingNode.getLeft() != null) {
            return removingNode.getLeft();
        }

        return removingNode.getRight();
    }

    public void visitInWidth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.remove();

            consumer.accept(current.getData());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public void visitInDepthRecursion(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        visit(root, consumer);
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), consumer);
        }
    }

    public void visitInDepth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> current = stack.removeLast();

            consumer.accept(current.getData());

            if (current.getRight() != null) {
                stack.addLast(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.addLast(current.getLeft());
            }
        }
    }
}