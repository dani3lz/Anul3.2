package Iterator;

public class Product implements Collection{
    private String name;
    private String[] compoents;

    public Product(String name, String[] components) {
        this.name = name;
        this.compoents = components;
    }
    public String getName() {
        return name;
    }

    @Override
    public Iterator getIterator() {
        return new GenreIterator();
    }
private class GenreIterator implements Iterator{
int index;
    @Override
    public boolean hasNext() {
        if (index< compoents.length){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        return compoents[index++];
    }
}
}
