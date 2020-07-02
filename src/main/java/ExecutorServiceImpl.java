import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceImpl {
    private final int threadsQuantity;
    private final ExecutorService executorService;
    private final List<Integer> numbers;

    public ExecutorServiceImpl(int threadsQuantity, List<Integer> numbers) {
        this.threadsQuantity = threadsQuantity;
        this.executorService = Executors.newFixedThreadPool(threadsQuantity);
        this.numbers = numbers;
    }

    public void shutdownExecutorService() {
        executorService.shutdown();
    }

    public Integer countSum() throws InterruptedException, ExecutionException {
        int sum = 0;
        List<Future<Integer>> list = executorService.invokeAll(getSumLists());
        for (Future<Integer> future : list) {
            sum += future.get();
        }
        return sum;
    }

    private List<Callable<Integer>> getSumLists() {
        List<Callable<Integer>> list = new ArrayList<>();
        int divisionStep = numbers.size() / threadsQuantity;
        for (int i = 0; i < threadsQuantity; i++) {
            ListSumCounter listSumCounter = new ListSumCounter(numbers
                    .subList(divisionStep * i, divisionStep * (i + 1)));
            list.add(listSumCounter);
        }
        return list;
    }
}
