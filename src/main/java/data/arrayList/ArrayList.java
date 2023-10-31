package data.arrayList;

import java.util.Arrays;

public class ArrayList {
    private Object[] elementData;
    private int size;

    private int size(){
      return this.size;
    }

    public ArrayList()   {
        this.elementData = new Object[10];
    }

    public ArrayList(int cap){
        //判断是否合法
        if(cap < 0){
            throw new RuntimeException("数据不合法：cap"+cap);
        }
        this.elementData = new Object[cap];
    }

    //增加
    public void add(Object element){
        //判断是否需要进行扩容
        kuorong();
        elementData[size] = element;
        size++;
    }

    /**
     * 根据索引添加数据
     * @param index  索引
     * @param element 要添加的数据
     */
    public void add(int index,Object element){
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("数组越界 index:"+index);
        }
        kuorong();
        for(int i = size -1;i >= size;i--){
            elementData[i+1] = elementData[i];
        }
        elementData[index] = element;
        size++;
    }

    /**
     * 根据索引删除元素
     * @param index
     */
    public void remove(int index){
        rangCheck(index);
        for(int i = size -1;i >= index;i--){
            elementData[i] = elementData[i+1];
        }
        //把数组的最后一个元素设置为null
        elementData[size - 1] = null;
        //更新size的值
        size--;
    }


    public Object get(int index){
        rangCheck(index);
        return elementData[index];
    }

    /**
     * 索引越界检查
     * @param index
     */
    public void rangCheck(int index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("数组越界 index:"+index);
        }
    }

    public void kuorong(){
        if(elementData.length == size()){
            //创建一个新数组
            Object[] newArray = new Object[elementData.length * 2 + 1];
            //将原来数组复制到新数组中
            for(int i = 0;i < size;i++){
                newArray[i] = elementData[i];
            }
            elementData = newArray;
        }
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
