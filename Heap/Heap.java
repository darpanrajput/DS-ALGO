package com.graphs.implementations.Heap;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap {
    private static class minHeap {
        //private int capacity = 10, size = 0;
        ArrayList<Integer> items = new ArrayList<>();

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
            return items.get(getLeftChildIndex(index));
        }

        private int getRightChild(int index) {
            return items.get(getRightChildIndex(index));
        }

        private int getParent(int index) {
            return items.get(getParentIndex(index));
        }

        private void Swap(int i1, int i2) {
          /*  int temp = items.get(i1);
            items.get(i1) = items.get(i2);
            items.get(i2) = temp;*/
            int temp = items.get(i1);
            items.set(i1, items.get(i2));
            items.set(i2, temp);
        }

       /* private void esureCapacity() {
            if (capacity == size) {
                items = Arrays.copyOf(items, 2 * capacity);
                capacity *= 2;
            }
        }*/

        /*operaitons on the heap*/

        public int peek() {
            if (items.size() == 0) throw new IllegalStateException();
            return items.get(0);//return the first ele in array
        }

        public int poll() {
            if (items.size() == 0) throw new IllegalStateException();
            int firstItems = items.get(0);

            // items[0] = items[size - 1];
            // last items as first item
            items.set(0, items.get(items.size() - 1));
            items.remove(items.size() - 1);
            //size--;//redues the heap size
            heapifyDown();
            return firstItems;//return the polled ele
        }

        public ArrayList<Integer> add(int item) {
            //  esureCapacity();
//            items.get(size] =item;
            items.add(item);
//            size++;
            heapifyUp();
            return items;
        }

        private void heapifyUp() {
            //because the size has increased before heapify
            int index = items.size() - 1;
            while (hasParent(index) && items.get(index) < getParent(index)) {
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
                } else if (items.get(index) < items.get(smallerChildIndex)) {
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

    public static void main(String[] args) {
        Heap heap = new Heap();
        Heap.minHeap minHeap = new minHeap();
        minHeap.add(3);
        minHeap.add(8);
        minHeap.add(4);
        minHeap.add(9);
        minHeap.add(13);
        minHeap.add(10);
        minHeap.add(9);
        minHeap.add(15);
        minHeap.add(20);
        minHeap.add(7);
        System.out.println(minHeap.items);

        System.out.println("peek="+minHeap.peek());
        System.out.println(minHeap.add(2));

        minHeap.poll();
        System.out.println((minHeap.items));
        System.out.println(minHeap.hasParent(0));
        System.out.println(minHeap.add(1));

    }

}
