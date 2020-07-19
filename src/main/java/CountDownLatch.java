import java.util.ArrayList;

/**
 * Description:  门栓 等待线程结束 ，也可以用join
 * date: 2020/7/19 10:58
 *
 * @author cc<br />
 * @since JDK 1.8
 */
public class CountDownLatch {


    public static void main(String[] args) {


        ArrayList<Thread> threads = new ArrayList<>();

        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(()-> {

                System.out.println(Thread.currentThread().getName());

                countDownLatch.countDown();
            },String.valueOf(i)));
        }
        threads.forEach(thread -> thread.start());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("latch finish");
    }


}
