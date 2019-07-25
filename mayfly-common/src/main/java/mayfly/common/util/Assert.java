package mayfly.common.util;

import java.util.Collection;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2019-01-19 4:22 PM
 */
public class Assert {
    private Assert() {}

    public static void notEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void notNull(T object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void notEmpty(T[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}