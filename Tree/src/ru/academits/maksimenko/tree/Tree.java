package ru.academits.maksimenko.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
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

    public boolean contains(T data) {
        for (TreeNode<T> current = root; current != null; ) {
            int comparisonResult = compare(data, current.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
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
        final int initialSize = size;

        for (TreeNode<T> current = root, previous = null; current != null; ) {
            int afterCurrentComparison = compare(data, current.getData());

            int afterPreviousComparison = previous == null
                    ? 0 :
                    compare(data, previous.getData());

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