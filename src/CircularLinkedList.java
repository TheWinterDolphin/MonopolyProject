/*Andrew*/
public class CircularLinkedList<T> {
    private Link<T> first; //Only store the first link in the circle

    public Link<T> getFirst() { //Getter for first
        return first;
    }

    public CircularLinkedList() {
        first = null; //First is null at first when circle is empty
    }

    //Makes a newLink with newData and makes it the first link in the circle
    public void insertFirst(T newData) {
        Link<T> newLink = new Link<T>(newData); //Create newLink with newData
        if (first == null) { //If this is the first link in the circle
            newLink.next = newLink; //This link points to itself
            first = newLink; //First equals the newLink
        }
        else {
            newLink.next = first; //NewLink's next is original first link

            //Loop through links in the circle until you get to the link that points to the original first link
            Link<T> current = first;
            while(current.next != first) {
                current = current.next;
            }

            current.next = newLink; //This "last" link's next is now the newLInk
            first = newLink; //newLink is now the first in the circle
        }
    }

    //Returns a link that has the given targetData
    public Link<T> find(T targetData) {
        if (targetData.equals(first.data)) { //Edge case for if the first link has the targetData
            return first;
        }

        //Loop through each link in the circle (stop at
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
