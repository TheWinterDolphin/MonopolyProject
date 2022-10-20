public class CircularLinkedList<T> {
    private Link<T> first;

    public Link<T> getFirst() {
        return first;
    }

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

    public Link<T> find(T targetData) {
        if (targetData.equals(first.data)) {
            return first;
        }

        Link<T> current = first.next;

        while(current != first) {
            if (current.data.equals(targetData)) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    public Link<T> delete(T targetData) {
        if (first.data.equals(targetData)) {
            Link<T> deletedLink = first;
            Link<T> current = first;
            while(current.next != first) {
                current = current.next;
            }
            current.next = first.next;
            first = first.next;
            return deletedLink;
        }

        Link<T> current = first;
        while (current.next != first) {
            if (current.next.data.equals(targetData)) {
                Link<T> deletedLink = current.next;
                current.next = current.next.next;
                return deletedLink;
            }
            current = current.next;
        }

        return null;
    }

    public boolean replace(T targetData, T newData) {
        if (targetData.equals(first.data)) {
            first.replaceData(newData);
            return true;
        }

        Link<T> current = first.next;
        while(current != first) {
            if (current.data.equals(targetData)) {
                current.replaceData(newData);
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void devPrint(int numOfLinks) {
        Link<T> current = first;
        for (int i=0; i<numOfLinks; i++) {
            System.out.print("" + current.data + " --> ");
            current = current.next;
        }
    }
}
