package scripts.API.util;

/**
 * Created by Sphiinx on 2/16/2016.
 */
public class ArrayUtil {

    /**
     * Checks to see if the specified integer array contains the specified value.
     * <p/>
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value, false otherwise.
     */
    public static boolean contains(int value, int... array) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the specified double array contains the specified value.
     * <p/>
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value, false otherwise.
     */
    public static boolean contains(double value, double... array) {
        for (double d : array) {
            if (d == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the specified long array contains the specified value.
     * <p/>
     *
     * @param value The value.
     * @param array The array.
     * @return True if the array contains the value, false otherwise.
     */
    public static boolean contains(long value, long... array) {
        for (long l : array) {
            if (l == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the specified short array contains the specified value.
     * <p/>
     *
     * @param value The key.
     * @param array The array.
     * @return True if the array contains the key, false otherwise.
     */
    public static boolean contains(short value, short... array) {
        for (short s : array) {
            if (s == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the specified float array contains the specified value.
     * <p/>
     *
     * @param value The key.
     * @param array The array.
     * @return True if the array contains the key, false otherwise.
     */
    public static boolean contains(float value, float... array) {
        for (float f : array) {
            if (f == value) {
                return true;
            }
        }
        return false;
    }

}

