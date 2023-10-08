import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lab2 implements TableViewer {
    private static final Double D = 0.0;
    private static final Double M = 0.438199546857908;
    private final Random random = new Random();
    private final TableFrameBuilder tableFrameCreator = TableFrameBuilder.getInstance();
    private final List<String> columsNames = List.of("N", "MX", "m", "|MX-m|", "DX", "d", "DX-d");


    public void init() {
        var tableFrame = tableFrameCreator.createTableFrame(
                generateRowsData(List.of(100, 500, 1000, 2000, 5000)), columsNames);
        execute(tableFrame);
    }

    private Object[][] generateRowsData(List<Integer> countsOfGeneratedNums) {
        var rows = new Object[countsOfGeneratedNums.size()][columsNames.size()];
        int rowCounter = 0;

        for (var countNums : countsOfGeneratedNums) {
            rows[rowCounter++] = generateRowData(countNums);
        }
        return rows;
    }

    private Object[] generateRowData(int countOfNums) {
        var nums = generateRandomValues(countOfNums);
        var estimateM = calculateEstimateOfM(nums);
        var estimateD = calculateEstimateOfG(nums, estimateM);
        var differenceBetweenMAndEstimateM = M - estimateM < 0 ? -(M - estimateM) : M - estimateM; //по модулю
        var differenceBetweenGNadEstimateD = D - estimateD < 0 ? -(D - estimateD) : D - estimateD;
        return new Object[]{countOfNums, M, estimateM, differenceBetweenMAndEstimateM, D, estimateD, differenceBetweenGNadEstimateD};
    }


    /**
     * @param count - Количество генерируемых чисел
     * @return Возвращает заданное количество случайно сгенерированных чисел
     * по заданной формуле внутри функции.
     */
    private double[] generateRandomValues(int count) {
        final var leftBound = 0.1;
        final var rightBound = 0.9;
        var randomValues = random.doubles(count, leftBound, rightBound).toArray();
        return Arrays.stream(randomValues).map(Math::atan).toArray(); //Math.atan() -> обратная функция распределения
    }

    /**
     * @return Возвращает оценку математического ожидания
     */
    private Double calculateEstimateOfM(double[] nums) {
        var sumOfNums = Arrays.stream(nums).sum();
        return sumOfNums / nums.length;
    }

    /**
     * @return Возвращает оценку математической дисперсии
     */
    private Double calculateEstimateOfG(double[] nums, double estimateM) {
        var sumOfSquareNums = Arrays.stream(nums).map(Math::sqrt).sum();
        var a = sumOfSquareNums / (nums.length - 1);
        var b = nums.length * estimateM / (nums.length - 1);
        return a - b;
    }
}
