import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * @description:
 * @author: cc
 * @date: 2020/7/17
 */
public class LisenableFuture {

    public static void main(String[] args) {

        //回调函数
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<Integer> future = service.submit(() -> 8);

        Futures.addCallback(future, new FutureCallback<Integer>() {

            @Override
            public void onSuccess(Integer result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        service.shutdown();

    }

}
