package current;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j(topic = "c.MessageQueu")
public class MessageQueu {
    public static void main(String[] args) {
        MessageQu messageQu = new MessageQu(5);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                messageQu.take();
            }
        },"consumer").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                messageQu.put(new Messag(i,"i" + i));
            }
        },"provider").start();
    }

}

@Slf4j(topic = "c.MessageQu")
class MessageQu{
    //队列
    private LinkedList<Messag> list = new LinkedList<>();
    //容量
    private int proxitity;

    private Object lock = new Object();

    public MessageQu(int proxitity) {
        this.proxitity = proxitity;
    }

    public void take(){
        synchronized (lock){
            while (list.size() == 0){
                try {
                    lock.wait();
                    log.debug("waiting provider.........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Messag message = list.removeFirst();
            log.debug("消费了：{}",message.getId());
            lock.notifyAll();
        }
    }

    public void put(Messag message){
        synchronized (lock){
            while (list.size() == proxitity){
                try {
                    lock.wait();
                    log.debug("waiting consumer.........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addFirst(message);
            log.debug("生产了：{}",message.getId());
            lock.notifyAll();
        }
    }

}

class Messag{
    private int id;
    private Object value;

    public Messag(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
