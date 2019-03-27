package mayfly.common.utils;

import mayfly.common.exception.BusinessRuntimeException;

import java.util.Collection;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2019-01-19 4:22 PM
 */
public class Assert {
    public static void notEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new BusinessRuntimeException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessRuntimeException(message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new BusinessRuntimeException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessRuntimeException(message);
        }
    }

    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new BusinessRuntimeException(message);
        }
    }

    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new BusinessRuntimeException(message);
        }
    }
}