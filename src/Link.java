/*Andrew*/
public class Link<T> {
    public T data;
    public Link next;

    //Creates a link with the inputted newData
    public Link(T newData) {
        data = newData;
    }

    //Replaces this link's data with the inputted newData
    public void replaceData(T newData) {
        data = newData;
    }

    //Checks if this link has the same data as another link (same memory location) (returns true if they do) (false if they don't)
    public boolean equals(Link<T> other) {
        return other.data.equals(this.data);
    }
}
