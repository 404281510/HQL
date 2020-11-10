import utils.Node;

/**
 * @author huaqiliang
 * @date: 2020/11/1023:27
 * @Description:手写hashMap演示
 * @Version:
 */

public class HashMapDemo {


    public static void main(String[] args) {
        //第一个节点，既是头节点也是尾节点
        Node head = new Node("huaqiliang",null);

        //添加节点方式为头部添加
        head = new Node("zhangsan",head);

        Node second = head.getNext();

        System.out.println(head.getContent().toString() + head.getNext().getContent().toString());
        System.out.println(second.getContent());

    }

}
