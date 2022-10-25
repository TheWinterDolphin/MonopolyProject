/*Andrew*/
public class Link<T> {
    public T data;
    public Link next;

    public Link(T newData) {
        data = newData;
    }

    public void replaceData(T newData) {
        data = newData;
    }

    public boolean equals(Link<T> other) {
        return other.data.equals(this.data);
    }
}
