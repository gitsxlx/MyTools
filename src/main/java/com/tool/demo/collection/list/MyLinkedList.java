package com.tool.demo.collection.list;

/**
 * MyLinkedList
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class MyLinkedList<E> implements MyList<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    private static class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E item) {
        linkLast(item);
        size++;
    }

    @Override
    public void remove(E item) {
        int index = 0;
        for (Node<E> temp = first; temp != null; ) {
            if (temp.item.equals(item)) {
                temp = temp.next;
                remove(index);
            } else {
                temp = temp.next;
                index++;
            }
        }
    }

    public void remove(int index) {
        rangCheck(index);

        Node<E> temp = node(index);

        Node<E> up = temp.prev;
        Node<E> down = temp.next;

        if (up == null) {
            down.prev = null;
            first = down;
            size--;
            return;
        }

        if (down == null) {
            up.next = null;
            last = up;
            size--;
            return;
        }

        up.next = down;
        down.prev = up;

        size--;
    }

    private void rangCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
    }

    private Node<E> node(int index) {
        Node<E> node = first;
        if (node != null) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public E get(int index) {
        rangCheck(index);
        Node<E> temp = node(index);
        return temp == null ? null : temp.item;
    }

    @Override
    public int size() {
        return size;
    }

    private void linkLast(E item) {
        Node<E> temp = new Node<E>(last, item, null);

		if (first == null) {
			first = last = temp;
		} else {
			last.next = temp;
			last = temp;
		}
    }

    public static Node<?> reverse1(Node<?> node) {
        Node pre = null;
        Node cur = node;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static MyLinkedList<?> reverse(MyLinkedList<?> myLinkedList) {
        Node node = reverse1(myLinkedList.first);
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
        return myLinkedList;
    }

}
