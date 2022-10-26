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
            return first; //Return the first link
        }

        //Loop through each link in the circle (stop once you get back to the first link)
        Link<T> current = first.next;
        while(current != first) {
            if (current.data.equals(targetData)) { //If this link has the targetData
                return current; //Return this link
            }
            current = current.next;
        }

        return null; //None of the links in the circle have the targetData
    }

    //Removes a link from the circle (and returns the deleted link)
    public Link<T> delete(T targetData) {
        if (first.data.equals(targetData)) { //Edge case for if the first link has the targetData
            Link<T> deletedLink = first; //Save the link to be deleted for returning later

            //Loop through each link in the circle until you get to the "last" link
            Link<T> current = first;
            while(current.next != first) {
                current = current.next;
            }

            current.next = first.next; //The last link's next is now the second link (the first link's next)
            first = first.next; //The new first link is the old first link's next (the second link)
            return deletedLink;
        }

        //Else:
        //Loop through every link's next in the circle until you get to the last link
        Link<T> current = first;
        while (current.next != first) {
            if (current.next.data.equals(targetData)) { //If the current link's next has the targetData
                Link<T> deletedLink = current.next; //The current link's next will be deleted (save for returning later)
                current.next = current.next.next; //The current link's new next is now the original current link's next's next
                return deletedLink;
            }
            current = current.next;
        }

        return null; //None of the links in the circle have the targetData
    }

    //Changes a link with the targetData to have new data
    public boolean replace(T targetData, T newData) {
        if (targetData.equals(first.data)) { //Edge case for if the first link has the targetData
            first.replaceData(newData); //Replace the first link's data with newData
            return true; //Replace was successful
        }

        //Loop through every link in the circle until you get to the first link
        Link<T> current = first.next;
        while(current != first) {
            if (current.data.equals(targetData)) { //If this link has the targetData
                current.replaceData(newData); //Replace this link's data with newData
                return true; //Replace was successful
            }
            current = current.next;
        }

        return false; //No link had the targetData so replace was unsuccessful
    }

    //Method used for debugging (prints the circular linked list to the screen in a way that is visibly understandable)
    public void devPrint(int numOfLinks) {
        Link<T> current = first; //Start with the first link
        for (int i=0; i<numOfLinks; i++) { //Loop through and print "Link --> " for the given amount of links that was inputted
            System.out.print("" + current.data + " --> ");
            current = current.next;
        }
    }
}
