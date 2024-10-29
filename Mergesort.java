import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;

            // Split the array into two halves
            int[] left = new int[mid];
            int[] right = new int[array.length - mid];

            System.arraycopy(array, 0, left, 0, mid);
            System.arraycopy(array, mid, right, 0, array.length - mid);

            // Recursively sort each half
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted halves
            merge(array, left, right);
        }
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}

public class MergeSortTest {

    @Test
    public void testPositiveCases() {
        int[] array = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};
        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testNegativeCases() {
        assertThrows(ClassCastException.class, () -> {
            Object[] array = {5, "two", 9, 1, 5, 6};
            MergeSort.mergeSort((int[]) array);
        });
    }

    @Test
    public void testPerformanceCases() {
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int) (Math.random() * 1000000);
        }
        MergeSort.mergeSort(largeArray);
        for (int i = 0; i < largeArray.length - 1; i++) {
            assertTrue(largeArray[i] <= largeArray[i + 1]);
        }
    }

    @Test
    public void testBoundaryCases() {
        int[] emptyArray = {};
        int[] singleElementArray = {1};
        int[] duplicateValuesArray = {2, 3, 2, 1, 3, 1};
        int[] alreadySortedArray = {1, 2, 3, 4, 5};
        int[] reverseSortedArray = {5, 4, 3, 2, 1};

        MergeSort.mergeSort(emptyArray);
        MergeSort.mergeSort(singleElementArray);
        MergeSort.mergeSort(duplicateValuesArray);
        MergeSort.mergeSort(alreadySortedArray);
        MergeSort.mergeSort(reverseSortedArray);

        assertArrayEquals(new int[]{}, emptyArray);
        assertArrayEquals(new int[]{1}, singleElementArray);
        assertArrayEquals(new int[]{1, 1, 2, 2, 3, 3}, duplicateValuesArray);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, alreadySortedArray);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, reverseSortedArray);
    }

    @Test
    public void testIdempotencyCases() {
        int[] array = {3, 1, 4, 1, 5, 9};
        int[] expected = {1, 1, 3, 4, 5, 9};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }
}
