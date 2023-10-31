package current;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Slf4j(topic = "c.Test8")
public class Test8 {
    public static void main(String[] args) {

    }
}
@Slf4j(topic = "c.People")
class People extends Thread{
    @Override
    public void run() {
        GuardedObject guardedObject = GuardedObjectBoxs.createGuardedObject();
        //收信
        log.debug("开始收信ID:{}",guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("收信ID:{},内容{}",guardedObject.getId(),mail);
    }
}
@Slf4j(topic = "c.PostMan")
class PostMan extends Thread{
    private int id;
    private String mail;
    public PostMan(int id,String mail){
        this.id = id;
        this.mail = mail;
    }
    @Override
    public void run() {
        //送信
        log.debug("送信中.......");
        GuardedObject guardedObject = GuardedObjectBoxs.getGuardedObjectById(id);
        log.debug("送信ID:{},内容{}",id,mail);
        guardedObject.complete(mail);

    }
}

class GuardedObjectBoxs {
    private static Map<Integer,GuardedObject> map = new Hashtable<>();
    private static int id = 1;
    //生成唯一id
    private static synchronized int createGuardedObjectId(){
        return id++;
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject guardedObject = new GuardedObject(createGuardedObjectId());
        map.put(guardedObject.getId(),guardedObject);
        return guardedObject;
    }

    public static GuardedObject getGuardedObjectById(int id){
        return map.remove(id);
    }

    public static Set<Integer> getIds(){
        return map.keySet();
    }



}

//增加超时效果
class GuardedObject{
    private int id;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    private Object response;

    //获取结果
    public Object get(long timeout){
        synchronized (this){
            //开始时间
            long begin = System.currentTimeMillis();
            //经历时间
            long parsrTime = 0;
            while (response == null){
                //这一轮应该等待的时间
                long waitTime = timeout - parsrTime;
                //经历的时间超过了最大等待时间，退出循环
                if(waitTime <= 0){
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parsrTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response){
        synchronized (this){
            this.response = response;
            this.notifyAll();
        }
    }
}

