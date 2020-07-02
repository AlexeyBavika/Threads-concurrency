import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinImpl extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 1;
    private final List<Integer> numbers;

    public ForkJoinImpl(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected Integer compute() {
        if (numbers.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubTasks()).stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return numbers.stream().mapToInt(numbers -> numbers)
                .sum();
    }

    private List<ForkJoinImpl> createSubTasks() {
        return List.of(new ForkJoinImpl(numbers.subList(0, numbers.size() / 2)),
                new ForkJoinImpl(numbers.subList(numbers.size() / 2, numbers.size())));
    }
}
