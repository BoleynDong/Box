package util;

public class LinkedBox implements Box {

    private Node head;//记录首节点
    private Node tail;//记录尾结点
    private int size;//记录有效元素的个数

    //负责将元素添加在新的node里，挂在链表的尾端
    private void linkLast(int element){
        //获取链表的尾结点
        Node l = tail;
        //创建一个新的Node对象，将新数据包装起来
        Node newNode = new Node(l,element,null);
        tail = newNode;
        if (l == null){
            head = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    //设计一个方法，负责监测index是否合法
    private void rangeCheck(int index){
        if (index < 0 || index >= size){
            throw new BoxIndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }

    //设计一个方法，负责找寻给定index位置的node对象
    private Node node(int index){
        Node targetNode;
        if (index < (size >> 1)){//从前向后找
            targetNode = head;
            for (int i = 0;i < index;i++){
                targetNode = targetNode.next;
            }
        }else{//从后向前找
            targetNode = tail;
            for (int i = size - 1;i > index;i--){
                targetNode = targetNode.prev;
            }
        }
        return targetNode;
    }

    //设计一个方法，将给定的node节点删除，并保留数据
    private int unlink(Node targetNode){
        int oldValue = targetNode.item;
        //当前node的前一个node
        Node prev = targetNode.prev;
        //当前node的下一个node
        Node next = targetNode.next;

        if (prev == null){//说明targetNode是头节点
            head = next;
        }else{
            prev.next = next;
            targetNode.next = null;
        }

        if (next == null){//说明targetNode是尾结点
            tail = prev;
        }else{
            next.prev = prev;
            targetNode.prev = null;
        }
        //删除元素后需要让有效元素个数减少一个
        size--;
        return oldValue;
    }


//--------------------------------------
    @Override
    public boolean add(int element) {
        //将element存入一个新的Node对象里，添加至链表的尾端
        this.linkLast(element);
        return true;
    }

    @Override
    public int get(int index) {
        //监测index是否合法
        this.rangeCheck(index);
        //找寻给定位置的node对象
        Node targetNode = this.node(index);
        return targetNode.item;
    }

    @Override
    public int remove(int index) {
        //监测index是否合法
        this.rangeCheck(index);
        Node targetNode = this.node(index);
        int oldValue = this.unlink(targetNode);
        return oldValue;
    }

    @Override
    public int getSize() {
        return size;
    }
}
