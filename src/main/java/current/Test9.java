package current;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j(topic = "c.Test9")
public class Test9 {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(50);
        for(int i = 0;i < 100;i++){
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id,"值"+id));
            },"生产者").start();
        }
        new Thread(() -> {
            while (true){
                //Sleeper.sleep(1);
                messageQueue.take();
            }

        },"消费者").start();
    }

}

@Slf4j(topic = "c.MessageQueue")
class MessageQueue {
    //消息的队列集合
    private LinkedList<Message> list = new LinkedList<>();
    //队列容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    //获取消息
    public Message take(){
        synchronized (list){
            while (list.size() == 0){
                try {
                    log.debug("队列为空，等待消息存入");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            log.debug("消费id:{},内容{}",message.getId(),message.getValue());
            list.notifyAll();
            return message;

        }
    }
    //存入消息
    public void put(Message message){
        synchronized (list){
          while (list.size() == capcity){
              try {
                  log.debug("队列已满，等待消息消费");
                  list.wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          list.addLast(message);
          log.debug("放入消息id:{},内容{}",message.getId(),message.getValue());
          list.notifyAll();
        }
    }
}

class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }
}

