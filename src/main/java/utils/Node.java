package utils;

/**
 * @author huaqiliang
 * @date: 2020/11/1023:23
 * @Description:链表类
 * @Version:
 */

public class Node {
    private Object content;
    private Node next;

    public Node() {

    }

    public Node(Object content, Node next) {
        this.content = content;
        this.next = next;
    }


    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
