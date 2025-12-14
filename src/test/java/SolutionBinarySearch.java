import org.junit.jupiter.api.Test;

public class SolutionBinarySearch {

    static Integer[] SOURCE_ARR = {1, 5, 6, 10, 11, 27, 117, 118};

    private int binarySearch(Integer[] sourceArr, int target) {
        int leftBord = 0;
        int rightBord = sourceArr.length - 1;
        while (leftBord <= rightBord) {
            int mid = leftBord + (rightBord - leftBord) / 2;
            int compare = sourceArr[mid].compareTo(target);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                leftBord = mid + 1;
            } else {
                rightBord = mid - 1;
            }
        }
        return -1;
    }

    @Test()
    public void firstItemTest() {
        var fPosition = new SolutionBinarySearch().binarySearch(SOURCE_ARR, 1);
        // Поиск значения в начале массива -> ожидаем в консоле "Позиция: 0"
        System.out.println("Позиция: " + fPosition);
    }

    @Test()
    public void lastItemTest() {
        var fPosition = new SolutionBinarySearch().binarySearch(SOURCE_ARR, 118);
        // Поиск значения в конце массива -> ожидаем в консоле "Позиция: 7"
        System.out.println("Позиция: " + fPosition);
    }

    @Test()
    public void notItemTest() {
        var fPosition = new SolutionBinarySearch().binarySearch(SOURCE_ARR, 14);
        // Поиск значения отсутствующего в массиве -> ожидаем в консоле "Позиция: -1"
        System.out.println("Позиция: " + fPosition);
    }
}
