package hexlet.code;

import java.util.List;
import java.util.Objects;

public class Node {
    public enum Status {
        ADDED,      // Значение добавлено во втором файле
        REMOVED,    // Значение удалено во втором файле
        UNCHANGED,  // Значение не изменилось
        CHANGED,    // Значение изменилось
        NESTED      // Значение - вложенный объект, содержащий другие изменения
    }

    private final String key;
    private final Status status;
    private final Object valueBefore; // Значение из первого файла
    private final Object valueAfter;  // Значение из второго файла
    private final List<Node> children; // Вложенные изменения (для статуса NESTED)

    // NESTED
    public Node(String key, Status status, List<Node> children) {
        this.key = key;
        this.status = status;
        this.children = children;
        this.valueBefore = null;
        this.valueAfter = null;
    }

    public Node(String key, Status status, Object valueBefore, Object valueAfter) {
        this.key = key;
        this.status = status;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
        this.children = null;
    }


    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getValueBefore() {
        return valueBefore;
    }

    public Object getValueAfter() {
        return valueAfter;
    }

    public List<Node> getChildren() {
        return children;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(key, node.key)
                && status == node.status
                && Objects.equals(valueBefore, node.valueBefore)
                && Objects.equals(valueAfter, node.valueAfter)
                && Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, status, valueBefore, valueAfter, children);
    }

    @Override
    public String toString() {
        return "Node{"
                + "key='" + key + '\''
                + ", status=" + status
                + ", valueBefore=" + valueBefore
                + ", valueAfter=" + valueAfter
                + ", children=" + children
                + '}';
    }
}
