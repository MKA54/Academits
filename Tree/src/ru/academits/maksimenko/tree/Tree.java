package ru.academits.maksimenko.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    /**
     * @noinspection unused
     */
    public Tree() {
    }

    public Tree(T data) {
        root = new TreeNode<>(data);

        ++size;
    }

    public int getSize() {
        return size;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void add(T data) {
        if (size == 0) {
            root = new TreeNode<>(data);

            ++size;

            return;
        }

        TreeNode<T> current = root;
        TreeNode<T> newNode = new TreeNode<>(data);

        while (true) {
            if (newNode.compareTo(current) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);

                    break;
                }

                current = current.getLeft();

                continue;
            }

            if (current.getRight() == null) {
                current.setRight(newNode);

                break;
            }

            current = current.getRight();
        }

        ++size;
    }

    public boolean contains(Object o) {
        //noinspection unchecked
        TreeNode<T> node = new TreeNode<>((T) o);

        for (TreeNode<T> current = root; current != null; ) {
            if (node.compareTo(current) == 0) {
                return true;
            }

            if (node.compareTo(current) < 0) {
                if (current.getLeft() == null) {
                    return false;
                }

                current = current.getLeft();

                continue;
            }

            if (current.getRight() == null) {
                return false;
            }

            current = current.getRight();
        }

        return false;
    }

    public boolean remove(Object o) {
        final int initialSize = size;

        //noinspection unchecked
        TreeNode<T> node = new TreeNode<>((T) o);

        for (TreeNode<T> current = root, previous = null; current != null; ) {
            if (node.compareTo(current) == 0 && current.getLeft() == null && current.getRight() == null) {
                if (current == root) {
                    size--;

                    root = null;

                    break;
                }

                assert previous != null;
                if (node.compareTo(previous) < 0) {
                    previous.setLeft(null);
                } else {
                    previous.setRight(null);
                }

                size--;

                break;
            }

            if (node.compareTo(current) == 0 && current.getLeft() != null && current.getRight() != null) {
                TreeNode<T> leftParent = null;
                TreeNode<T> leftmost = current.getRight();

                while (leftmost.getLeft() != null) {
                    leftParent = leftmost;

                    leftmost = leftmost.getLeft();
                }

                if (leftmost.getRight() != null && leftParent != null) {
                    leftParent.setLeft(leftmost.getRight());
                }

                if (leftmost.getRight() == null && leftParent != null) {
                    leftParent.setLeft(null);
                }

                if (current == root) {
                    root = leftmost;

                    leftmost.setLeft(current.getLeft());

                    if (current.getRight() == leftmost) {
                        leftmost.setRight(null);
                    } else {
                        leftmost.setRight(current.getRight());
                    }

                    size--;

                    break;
                }

                assert previous != null;
                if (node.compareTo(previous) < 0) {
                    previous.setLeft(leftmost);
                } else {
                    previous.setRight(leftmost);
                }

                leftmost.setLeft(current.getLeft());
                leftmost.setRight(current.getRight());

                size--;

                break;
            }

            if (node.compareTo(current) == 0 && current.getLeft() != null) {
                if (current == root) {
                    root = current.getLeft();

                    --size;

                    break;
                }

                assert previous != null;
                previous.setLeft(current.getLeft());

                --size;

                break;
            }

            if (node.compareTo(current) == 0 && current.getRight() != null) {
                if (current == root) {
                    root = current.getRight();

                    --size;

                    break;
                }

                assert previous != null;
                previous.setRight(current.getRight());

                --size;

                break;
            }

            previous = current;

            if (node.compareTo(current) < 0) {
                current = current.getLeft();

                continue;
            }

            current = current.getRight();
        }

        return initialSize != size;
    }

    /**
     * @noinspection unchecked
     */
    public void widthInVisit() {
        if (size == 0) {
            System.out.println("There are no elements in the tree");

            return;
        }

        Queue<T> queue = new LinkedList<>();

        queue.add((T) root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = (TreeNode<T>) queue.remove();

            if (current.getLeft() != null) {
                queue.add((T) current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add((T) current.getRight());
            }

            System.out.println(current.getData());
        }
    }

    public void depthInVisitRecursion(TreeNode<T> node) {
        System.out.println(node.getData());

        if (node.getLeft() != null) {
            depthInVisitRecursion(node.getLeft());
        }

        if (node.getRight() != null) {
            depthInVisitRecursion(node.getRight());
        }
    }

    /**
     * @noinspection unchecked
     */
    public void depthInVisit() {
        if (size == 0) {
            System.out.println("There are no elements in the tree");

            return;
        }

        Deque<T> stack = new LinkedList<>();

        stack.addLast((T) root);

        while (!stack.isEmpty()) {
            TreeNode<T> current = (TreeNode<T>) stack.removeLast();

            if (current.getRight() != null) {
                stack.addLast((T) current.getRight());
            }

            if (current.getLeft() != null) {
                stack.addLast((T) current.getLeft());
            }

            System.out.println(current.getData());
        }
    }
}