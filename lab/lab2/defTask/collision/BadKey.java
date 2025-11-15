package collision;

public class BadKey {
    int id;

    public BadKey(int id) {
        this.id = id;
    }

    public int getKey() {
        return id;
    }

    @Override
    public int hashCode() {
        return 1; // ВСЕ ключи имеют одинаковый hashCode!
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadKey badKey = (BadKey) o;
        return id == badKey.id;
    }
};
