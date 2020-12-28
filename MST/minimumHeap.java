package com.graphs.implementations.MST;

import java.util.ArrayList;

public class minimumHeap {

    private static class Node{
        int v,cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    ArrayList<Node>items=new ArrayList<>();

    /*getting the index of the nodes*/
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int Parent) {
        return 2 * Parent + 2;
    }

    private int getParentIndex(int chiledIndex) {
        return (chiledIndex - 1) / 2;
    }
    /*checking for the children*/
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < items.size();
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < items.size();
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }
    /*******************getting the items******************/
    private int getLeftChild(int index) {
        return items.get(getLeftChildIndex(index)).cost;
    }

    private int getRightChild(int index) {
        return items.get(getRightChildIndex(index)).cost;
    }

    private int getParent(int index) {
        return items.get(getParentIndex(index)).cost;
    }
    private void Swap(int i1, int i2) {
          /*  int temp = items.get(i1);
            items.get(i1) = items.get(i2);
            items.get(i2) = temp;*/
        Node temp = items.get(i1);
        items.set(i1, items.get(i2));
        items.set(i2, temp);
    }
    public int peek() {
        if (items.size() == 0) throw new IllegalStateException();
        return items.get(0).v;//return the first ele in array
    }

    public int poll() {
        if (items.size() == 0) throw new IllegalStateException();
        Node firstItems = items.get(0);

        // items[0] = items[size - 1];
        // last items as first item
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        //size--;//redues the heap size
        heapifyDown();
        return firstItems.v;//return the polled ele
    }

    public ArrayList<Node> add(int item,int cost) {
        Node addedNode=new Node(item,cost);
        items.add(addedNode);
        heapifyUp();
        return items;
    }

    private void heapifyUp() {
        //because the size has increased before heapify
        int index = items.size() - 1;
        while (hasParent(index) && items.get(index).cost < getParent(index)) {
            Swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) <
                    getLeftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
                //if false then smallerChildIndex = getLeftChildIndex(index);
                // is true
            } else if (items.get(index).cost < items.get(smallerChildIndex).cost) {
                /*that means the node is correclty placed
                 * we just need to get out of the loop*/
                break;
            } else {
                Swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }
}
