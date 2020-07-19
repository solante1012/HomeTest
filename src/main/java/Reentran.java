import cn.hutool.core.thread.ThreadUtil;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:synchronize可以重入
 * 可以trylock
 * lockinterruptibly
 * 公平锁，谁等待时间长谁执行
 * date: 2020/7/19 10:06
 *
 * @author cc<br />
 * @since JDK 1.8
 */
public class Reentran {
    //获取Logger对象的实例
    public static final Logger logger = Logger.getLogger(Reentran.class);

    Lock reentrantLock = new ReentrantLock();

    void m(){
        try {
            reentrantLock.lock();
            ThreadUtil.safeSleep(5000);
            System.out.println("method running: m");
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }finally {
            reentrantLock.unlock();
        }
    }

    void m1(){
        boolean lock = false;
        try {
            //lock = reentrantLock.tryLock();
            reentrantLock.lockInterruptibly();
            lock = true;

            System.out.println("method running: m1");
            //System.out.println(lock);

        } catch (InterruptedException e) {
            //e.printStackTrace();
            // 直接打印，则只输出异常类型, e.getMessage()
            logger.debug("error "+e);

        }finally {
            if (lock){
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {


        Reentran reentran = new Reentran();
        new Thread(reentran::m).start();
        ThreadUtil.safeSleep(2000);
        Thread thread = new Thread(reentran::m1);
        thread.start();

        thread.interrupt();

    }

}
