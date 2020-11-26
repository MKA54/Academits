package ru.academits.maksimenko.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {
    }

    public Tree(Comparator<? super T> c) {
        comparator = c;
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (data == null) {
            return;
        }

        if (size == 0) {
            root = new TreeNode<>(data);

            ++size;

            return;
        }

        TreeNode<T> current = root;

        //noinspection unchecked
        Comparable<T> value = (Comparable<T>) data;

        while (true) {
            int afterComparisonValue = comparator != null ? comparator.compare(data, current.getData()) :
                    value.compareTo(current.getData());

            if (afterComparisonValue < 0) {
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

    public boolean contains(T data) {
        if (data == null) {
            return false;
        }

        //noinspection unchecked
        Comparable<T> value = (Comparable<T>) data;

        for (TreeNode<T> current = root; current != null; ) {
            int afterComparisonValue = comparator != null ? comparator.compare(data, current.getData()) :
                    value.compareTo(current.getData());

            if (afterComparisonValue == 0) {
                return true;
            }

            if (afterComparisonValue < 0) {
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

    public boolean remove(T data) {
        if (data == null) {
            return false;
        }

        final int initialSize = size;
        //noinspection unchecked
        Comparable<T> value = (Comparable<T>) data;

        for (TreeNode<T> current = root, previous = null; current != null; ) {
            int afterCurrentComparison = comparator != null ? comparator.compare(data, current.getData()) :
                    value.compareTo(current.getData());

            int afterPreviousComparison = previous == null ? 0 : comparator != null ? comparator.compare(data, previous.getData()) :
                    value.compareTo(previous.getData());

            if (afterCurrentComparison == 0 && current.getLeft() == null && current.getRight() == null) {
                if (current == root) {
                    size--;

                    root = null;

                    break;
                }

                if (afterPreviousComparison < 0) {
                    previous.setLeft(null);
                } else {
                    assert previous != null;
                    previous.setRight(null);
                }

                size--;

                break;
            }

            if (afterCurrentComparison == 0 && current.getLeft() != null && current.getRight() != null) {
                if (current.getRight().getLeft() == null) {
                    current.getRight().setLeft(current.getLeft());

                    if (afterPreviousComparison < 0) {
                        previous.setLeft(current.getRight());
                    } else {
                        assert previous != null;
                        previous.setRight(current.getRight());
                    }

                    size--;

                    break;
                }

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
                if (afterPreviousComparison < 0) {
                    previous.setLeft(leftmost);
                } else {
                    previous.setRight(leftmost);
                }

                leftmost.setLeft(current.getLeft());
                leftmost.setRight(current.getRight());

                size--;

                break;
            }

            if (afterCurrentComparison == 0 && current.getLeft() != null) {
                if (current == root) {
                    root = current.getLeft();

                    --size;

                    break;
                }

                if (afterPreviousComparison < 0) {
                    previous.setLeft(current.getLeft());
                } else {
                    assert previous != null;
                    previous.setRight(current.getLeft());
                }

                --size;

                break;
            }

            if (afterCurrentComparison == 0 && current.getRight() != null) {
                if (current == root) {
                    root = current.getRight();

                    --size;

                    break;
                }

                if (afterPreviousComparison < 0) {
                    previous.setLeft(current.getRight());
                } else {
                    assert previous != null;
                    previous.setRight(current.getRight());
                }

                --size;

                break;
            }

            previous = current;

            if (afterCurrentComparison < 0) {
                current = current.getLeft();

                continue;
            }

            current = current.getRight();
        }

        return initialSize != size;
    }

    private void print() {
        System.out.println("There are no elements in the print");
    }

    public void visitInWidth() {
        if (size == 0) {
            print();

            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        Consumer<T> print = System.out::println;

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.remove();

            print.accept(current.getData());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public void visitInDepthRecursion() {
        if (size == 0) {
            print();

            return;
        }

        Consumer<T> print = System.out::println;

        visit(root, print);
    }

    private void visit(TreeNode<T> node, Consumer<T> print) {
        if (node == null) {
            return;
        }

        print.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), print);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), print);
        }
    }

    public void visitInDepth() {
        if (size == 0) {
            print();

            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.addLast(root);

        Consumer<T> print = System.out::println;

        while (!stack.isEmpty()) {
            TreeNode<T> current = stack.removeLast();

            print.accept(current.getData());

            if (current.getRight() != null) {
                stack.addLast(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.addLast(current.getLeft());
            }
        }
    }
}