import java.util.*;

public class joinstrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        ArrayList<TailedLinkedList> allWords = new ArrayList<>();
        int testCases = fio.nextInt();
        for (int i  = 0 ; i < testCases ; i++ ) {
            TailedLinkedList word = new TailedLinkedList();
            word.addFront(fio.nextLine());
            allWords.add(word);
        }
        int a = 0;
        int b;
        for (int i = 0 ; i < testCases - 1 ; i++) {
            a = fio.nextInt() - 1;
            b = fio.nextInt() - 1;
            TailedLinkedList bword = allWords.get(b);
            allWords.get(a).extendList(bword);
        }



        ListNode cur = allWords.get(a).getHead();
        fio.print(cur.getItem());
        for (int i=1; i < allWords.get(a).size(); i++) {
            cur = cur.getNext();
            fio.print(cur.getItem());
        }

        fio.close();

    }


}








class TailedLinkedList{
    // attributes
    public ListNode head;
    public ListNode tail;
    public int num_nodes;

    // Return number of items in list
    public int size() { return num_nodes; }

    public void extendList(TailedLinkedList list) {
        this.tail.setNext(list.getHead());
        this.tail = list.getTail();
        list.head = null;
        list.tail = null;
        num_nodes = num_nodes + list.size();
    }

    // Add item to front of list
    public void addFront(String item) { addAtIndex(0,item); }

    public void  addAtIndex(int index, String item) {
        ListNode cur;
        ListNode newNode = new ListNode(item);

        if (index >= 0 && index <= size()) {
            if (index == 0) // insert in front
                insert(null,newNode);
            else if (index == size()) // insert at the back, don't have to move all the way to the back
                insert(tail,newNode);
            else {
                cur = getNodeAtIndex(index-1); // access node at index-1
                insert(cur,newNode);
            }
        }
        else { // index out of bounds
            System.out.println("invalid index");
            System.exit(1);
        }
    }

    // insert newNode after the node referenced by cur
    public void insert(ListNode cur, ListNode n) {
        // insert in front
        if (cur == null) {
            n.setNext(head);
            head = n; // update head
            if (tail == null) // update tail if list originally empty
                tail = head;
        }
        else { // insert anywhere else
            n.setNext(cur.getNext());
            cur.setNext(n);
            if (cur == tail) // update tail if inserted new last item
                tail = tail.getNext();
        }
        num_nodes++;
    }



    // non-interface helper methods
    public ListNode getHead() { return head; }
    public ListNode getTail() { return tail; }

    /* return the ListNode at index */
    public ListNode getNodeAtIndex(int index) {
        int counter = 0;
        ListNode node = null;

        if (index < 0 || index > size()-1) {
            System.out.println("invalid index");
            System.exit(1);
        }
        if (index == size()-1) // return tail if index == size()-1
            return tail;
        for (ListNode cur = head; cur != null; cur = cur.getNext()) {
            if (counter == index) {
                node = cur;
                break;
            }
            counter++;
        }
        return node;
    }



}


class ListNode {
    /* attributes */
    public String item;
    public ListNode next;

    /* constructors */
    public ListNode(String val) { this(val, null); }

    public ListNode(String val, ListNode n) {
        item = val;
        next = n;
    }

    /* get the next ListNode */
    public ListNode getNext() { return next; }

    /* get the item of the ListNode */
    public String getItem() { return item; }

    /* set the item of the ListNode */
    public void setItem(String val) { item = val; }

    /* set the next reference */
    public void setNext(ListNode n) { next = n; }
}

