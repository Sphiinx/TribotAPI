package TribotAPI.util;

import java.util.Arrays;

/**
 * Created by Sphiinx on 2/16/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class ArrayUtil {

    /**
     * Checks to see if the specified integer array contains the specified value.
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value; false otherwise.
     */
    public static boolean contains(int value, int[] array) {
        return Arrays.stream(array).anyMatch(i -> i == value);
    }

    /**
     * Checks to see if the specified double array contains the specified value.
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value; false otherwise.
     */
    public static boolean contains(double value, double[] array) {
        return Arrays.stream(array).anyMatch(i -> i == value);
    }

    /**
     * Checks to see if the specified long array contains the specified value.
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value; false otherwise.
     */
    public static boolean contains(long value, long[] array) {
        return Arrays.stream(array).anyMatch(i -> i == value);
    }

}