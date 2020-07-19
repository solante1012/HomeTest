import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description:
 * date: 2020/7/19 8:51
 *
 * @author cc<br />
 * @since JDK 1.8
 */
public class AtomT {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    void add() {
        for (int i = 0; i < 1000; i++) {
            atomicInteger.getAndIncrement();
        }
    }

    LongAdder longAdder = new LongAdder();


    void longAdd() {
        for (int i = 0; i < 1000; i++) {
            longAdder.increment();
        }
    }

    public static void main(String[] args) {

        AtomT t = new AtomT();
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Thread> threads1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::add));
        }

        for (int i = 0; i < 10; i++) {
            threads1.add(new Thread(t::longAdd));
        }

        threads.forEach(thread -> thread.start());
        threads1.forEach(thread -> thread.start());


        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads1.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.atomicInteger);
        System.out.println(t.longAdder);


    }
}
