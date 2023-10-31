package current;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "c.Test7")
public class Test7 {
    public static void main(String[] args) {
        GuardedObject1 guardedObject = new GuardedObject1();
        new Thread(() -> {
            log.debug("等待结果");
            List<String> response = (List<String>) guardedObject.get(2000);
            log.debug("结果大小,{}",response.size());
        },"t1").start();

        new Thread(() -> {
            try {
                log.debug("下载中.........");
                List<String> download = Downloader.download();
                guardedObject.complete(download);
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}

//增加超时效果
class GuardedObject1{
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

