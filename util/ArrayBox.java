package util;

public class ArrayBox implements Box {
    //设计一个静态常量，用来存储数组的默认长度
    private static final int DEFAULT_CAPACITY = 10;
    //设计一个自己的属性，用来存放真实的数据
    private int[] elementData;
    //设计一个属性，用来记录数组真实元素的个数
    private int size;

    public ArrayBox(){
        elementData = new int[DEFAULT_CAPACITY];
    }

    public ArrayBox(int capacity){
        elementData = new int[capacity];
    }

    //小A同学，负责确保数组内部的容量
    private void ensureCapacityInternal(int minCapcity){
        if (minCapcity - elementData.length > 0){
            //需要进行数组的扩容
            this.grow(minCapcity);
        }
    }

    //负责计算扩容的大小
    private void grow(int minCapcity){
        //获取原数组的长度
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapcity){
            newCapacity = minCapcity;
        }
        //调用方法，创建新数组，将原数组中的所有元素移入新数组中
        elementData = this.copyOf(elementData,newCapacity);

    }

    //将原数组中的元素移入新数组中
    private int[] copyOf(int[] oldArray,int newCapacity){
        int[] newArray = new int[newCapacity];
        for (int i = 0;i < oldArray.length;i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    private void rangeCheck(int index){
        if (index < 0 || index >= size){
            //自定义一个异常 来说明游戏
            throw new BoxIndexOutOfBoundsException("Index:"+index+",Size:"+size);

        }
    }

    //将用户给定的element存起来
    //参数是需要存起来的元素
    //返回值：是否存储成功
    public boolean add(int element){
        //找一个方法做事，确保数组的内部容量够用
        this.ensureCapacityInternal(size + 1);
        //上述一行代码可以执行完毕，说明elementData的数组肯定够用
        //直接将新来的element元素存入数组中，并且size会加1
        elementData[size++] = element;
        return true;

    }

    //用来获取给定位置的元素 参数是索引位置
    //返回值是取得的元素
    public int get(int index){
        //监测给定的index是否合法
        this.rangeCheck(index);
        //如果上述代码可以执行，证明index是合法的
        //则找到index对应位置的元素，将其返回。
        return elementData[index];
    }

    //用来删除给定位置的元素
    //参数是元素的下标
    //返回值是删除掉的元素本身
    public int remove(int index){
        //检查index是否合法
        this.rangeCheck(index);
        //上述代码可以执行，index是合法的
        //找到index位置的元素，保留起来给用户
        int oldValue = elementData[index];

        for (int i = index;i < size - 1;i++){
            //从index位置开始至size - 1结束，后面元素依次前移覆盖一位
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = 0;

        return oldValue;
    }

    //设计一个方法，用来获取size有效的个数，没有设置
    //可以保证size的安全
    public int getSize() {
        return size;
    }
}
