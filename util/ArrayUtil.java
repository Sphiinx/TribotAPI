package scripts.TribotAPI.util;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Sphiinx on 2/16/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class ArrayUtil {

    /**
     * Initialization of the randomisation object.
     * */
    private static Random random = new Random();

    /**
     * Gets a random integer from an integer array/
     *
     * @param array The array in which to get a random number from.
     * @return A random integer from the integer array.
     * */
    public static int getRandomInt(int[] array){
        if (array == null)
            return -1;

        if (array.length <= 0)
            return -1;

        return array[random.nextInt(array.length)];
    }

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