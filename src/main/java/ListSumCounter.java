import java.util.List;
import java.util.concurrent.Callable;

public class ListSumCounter implements Callable<Integer> {
    private final List<Integer> numbers;

    public ListSumCounter(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return numbers.stream()
                .mapToInt(numbers -> numbers)
                .sum();
    }
}
