package current;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test5")
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        Thread.sleep(3000);
        twoPhaseTermination.stop();

    }
    @Slf4j(topic = "c.TwoPhaseTermination")
    static
    class TwoPhaseTermination {
        //监控线程
      private Thread monitor;
      //停止标记
      private volatile boolean stop;

      //启动监控线程
      public void start(){
          monitor = new Thread(() -> {
              while (true) {
                  Thread thread = Thread.currentThread();
                  if (stop) {
                      log.debug("料理后事");
                      break;
                  }
                  try {
                      Thread.sleep(1000);
                      log.debug("执行监控记录");
                  } catch (InterruptedException e) {
                  }
              }
          });
          monitor.start();
      }

      //停止监控线程
        public void stop(){
          stop = true;
          monitor.interrupt();
        }

    }
}
