import java.io.*;
import java.util.StringTokenizer;

public class teque {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        helperTQ Teque = new helperTQ();
        int noOperations = fio.nextInt();
        for (int i = 0 ; i < noOperations ; i++) {
            String op = fio.next();
            int value = fio.nextInt();
            if (op.equals("push_back")) {
                Teque.pushBack(value);
            } else if (op.equals("push_front")) {
                Teque.pushFront(value);

            } else if (op.equals("push_middle")) {
                Teque.pushMiddle(value);

            } else {
                fio.println(Teque.get(value));

            }
        }


        fio.close();
    }
}

class helperTQ {
    ArrayWithPointer frontArray = new ArrayWithPointer();
    ArrayWithPointer backArray = new ArrayWithPointer();


    public helperTQ() {

    }
    public void pushFront(int num) {
        frontArray.addFront(num);
        if (getFrontArraySize() > Math.round(0.5 * getTotalSize())) {
            backArray.addFront(frontArray.getBack());
            frontArray.removeBack();
        }

    }

    public void pushBack(int num) {
        backArray.addBack(num);
        if (getBackArraySize() > 0.5 * getTotalSize()) {
            frontArray.addBack(backArray.getFront());
            backArray.removeFront();
        }

    }


    public void pushMiddle(int num) {
        frontArray.addBack(num);
        if (getFrontArraySize() > Math.round(0.5 * getTotalSize())) {
            backArray.addFront(frontArray.getBack());
            frontArray.removeBack();
        }
    }

    public int get(int index) {
        if (index >= getFrontArraySize()) {
            index -= getFrontArraySize();
            return backArray.getAtIndex(backArray.getFrontIndex() +1+ index);
        } else {
            return frontArray.getAtIndex(frontArray.getFrontIndex() +1+ index);
        }
    }

    public int getTotalSize() {
        int totalSize = frontArray.getSize() +  backArray.getSize();
        return totalSize;
    }

    public int getFrontArraySize() {
        return frontArray.getSize();
    }

    public int getBackArraySize() {
        return backArray.getSize();
    }

}




class ArrayWithPointer {
    int[] array;
    int front = 1000000;
    int back = 1000000;
    int size = 0;

    public ArrayWithPointer() {
        this.array = new int[2000000];
    }

    public void addFront(int num) {
        array[front] = num;
        decreaseFront();
        size++;
        if (size == 1) {
            increaseBack();
        }
    }
    public void removeFront() {
        increaseFront();
        size--;
    }

    public void addBack(int num) {
        array[back] = num;
        increaseBack();
        size++;
        if (size == 1) {
            decreaseFront();
        }
    }
    public void removeBack() {
        decreaseBack();
        size--;
    }

    public int getSize() {
        return this.size;
    }


    public int getFrontIndex(){
        return this.front;
    }

    public int getFront(){
        return array[front+1];
    }
    public int getBackIndex(){
        return this.back;
    }
    public int getBack(){
        return array[back-1];
    }

    public void increaseFront() {
        front++;
    }
    public void decreaseFront() {
        front--;
    }
    public void increaseBack() {
        back++;
    }
    public void decreaseBack() {
        back--;
    }

    public int getAtIndex(int index) {
        return array[index];
    }

}


