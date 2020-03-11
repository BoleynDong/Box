package util;

public class Node {
    public Node prev;//上一个Node对象
    public int item;//当前数据
    public Node next;//下一个Node对象

    public Node(Node prev,int item,Node next){
        this.prev = prev;
        this.item = item;
        this.next = next;
    }
}
