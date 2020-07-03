import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int NUM_OF_THREADS = 10;
    private static final int NUM_OF_NUMBERS = 1_000_000;

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < NUM_OF_NUMBERS; i++) {
            list.add(i);
        }
        ExecutorServiceImpl executorService = new ExecutorServiceImpl(NUM_OF_THREADS, list);
        System.out.println(executorService.countSum());
        executorService.shutdownExecutorService();
        ForkJoinImpl forkJoinImpl = new ForkJoinImpl(list);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(forkJoinPool.submit(forkJoinImpl).get());
    }
}
