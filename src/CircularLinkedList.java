public class CircularLinkedList<T> {
    private Link<T> first;

    public CircularLinkedList() {
        first = null;
    }

    public void insertFirst(T newData) {
        Link<T> newLink = new Link<T>(newData);
        if (first == null) {
            newLink.next = newLink;
            first = newLink;
        }
        else {
            newLink.next = first;
            Link<T> current = first;
            while(current.next != first) {
                current = current.next;
            }
            current.next = newLink;
            first = newLink;
        }
    }

    public void devPrint(int numOfLinks) {
        Link<T> current = first;
        for (int i=0; i<numOfLinks; i++) {
            System.out.print("" + current.data + " --> ");
            current = current.next;
        }
    }
}
