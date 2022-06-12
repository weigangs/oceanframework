package com.lkyl.oceanframework.common.utils.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.time.DurationUtils;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 12:07
 */
public class ObjectUtils {

    public static boolean isNotAllNull(@Nullable Object ... object) {
        if (object != null) {
            for(Object var : object) {
                if (isEmpty(var)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAllNull(@Nullable Object ... object) {
        if (object != null) {
            for(Object var : object) {
                if (isNotEmpty(var)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNotEmpty(@Nullable Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(@Nullable Object[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }


    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            return !((Optional)obj).isPresent();
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map ? ((Map)obj).isEmpty() : false;
        }
    }
    public static <T> T clone(T obj) {
        if (!(obj instanceof Cloneable)) {
            return null;
        } else {
            Object result;
            if (obj.getClass().isArray()) {
                Class<?> componentType = obj.getClass().getComponentType();
                if (componentType.isPrimitive()) {
                    int length = Array.getLength(obj);
                    result = Array.newInstance(componentType, length);

                    while(length-- > 0) {
                        Array.set(result, length, Array.get(obj, length));
                    }
                } else {
                    result = ((Object[])((Object[])obj)).clone();
                }
            } else {
                try {
                    Method clone = obj.getClass().getMethod("clone");
                    result = clone.invoke(obj);
                } catch (NoSuchMethodException var4) {
                    throw new CloneFailedException("Cloneable type " + obj.getClass().getName() + " has no clone method", var4);
                } catch (IllegalAccessException var5) {
                    throw new CloneFailedException("Cannot clone Cloneable type " + obj.getClass().getName(), var5);
                } catch (InvocationTargetException var6) {
                    throw new CloneFailedException("Exception cloning Cloneable type " + obj.getClass().getName(), var6.getCause());
                }
            }

            return (T) result;
        }
    }

    public static <T> T cloneIfPossible(T obj) {
        T clone = clone(obj);
        return clone == null ? obj : clone;
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return compare(c1, c2, false);
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
        if (c1 == c2) {
            return 0;
        } else if (c1 == null) {
            return nullGreater ? 1 : -1;
        } else if (c2 == null) {
            return nullGreater ? -1 : 1;
        } else {
            return c1.compareTo(c2);
        }
    }

    public static void identityToString(Appendable appendable, Object object) throws IOException {
        Validate.notNull(object, "object", new Object[0]);
        appendable.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }

    public static String identityToString(Object object) {
        if (object == null) {
            return null;
        } else {
            String name = object.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(object));
            StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
            builder.append(name).append('@').append(hexString);
            return builder.toString();
        }
    }


    public static void identityToString(StringBuffer buffer, Object object) {
        Validate.notNull(object, "object", new Object[0]);
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        buffer.ensureCapacity(buffer.length() + name.length() + 1 + hexString.length());
        buffer.append(name).append('@').append(hexString);
    }

    public static void identityToString(StringBuilder builder, Object object) {
        Validate.notNull(object, "object", new Object[0]);
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
        builder.append(name).append('@').append(hexString);
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T max(T... values) {
        T result = null;
        if (values != null) {
            Comparable[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                T value = (T) var2[var4];
                if (compare(value, result, false) > 0) {
                    result = value;
                }
            }
        }

        return result;
    }

    @SafeVarargs
    public static <T> T median(Comparator<T> comparator, T... items) {
        Validate.notEmpty(items, "null/empty items", new Object[0]);
        Validate.noNullElements(items);
        Validate.notNull(comparator, "comparator", new Object[0]);
        TreeSet<T> sort = new TreeSet(comparator);
        Collections.addAll(sort, items);
        T result = (T) sort.toArray()[(sort.size() - 1) / 2];
        return result;
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T median(T... items) {
        Validate.notEmpty(items);
        Validate.noNullElements(items);
        TreeSet<T> sort = new TreeSet();
        Collections.addAll(sort, items);
        T result = (T) sort.toArray()[(sort.size() - 1) / 2];
        return result;
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T min(T... values) {
        T result = null;
        if (values != null) {
            Comparable[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                T value = (T) var2[var4];
                if (compare(value, result, true) < 0) {
                    result = value;
                }
            }
        }

        return result;
    }

    @SafeVarargs
    public static <T> T mode(T... items) {
        if (ArrayUtils.isNotEmpty(items)) {
            HashMap<T, MutableInt> occurrences = new HashMap(items.length);
            Object[] var2 = items;
            int max = items.length;

            for(int var4 = 0; var4 < max; ++var4) {
                T t = (T) var2[var4];
                MutableInt count = (MutableInt)occurrences.get(t);
                if (count == null) {
                    occurrences.put(t, new MutableInt(1));
                } else {
                    count.increment();
                }
            }

            T result = null;
            max = 0;
            Iterator var8 = occurrences.entrySet().iterator();

            while(var8.hasNext()) {
                Map.Entry<T, MutableInt> e = (Map.Entry)var8.next();
                int cmp = ((MutableInt)e.getValue()).intValue();
                if (cmp == max) {
                    result = null;
                } else if (cmp > max) {
                    max = cmp;
                    result = e.getKey();
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public static <T> T requireNonEmpty(T obj) {
        return requireNonEmpty(obj, "object");
    }

    public static <T> T requireNonEmpty(T obj, String message) {
        Objects.requireNonNull(obj, message);
        if (isEmpty(obj)) {
            throw new IllegalArgumentException(message);
        } else {
            return obj;
        }
    }

    public static String toString(Object obj, Supplier<String> supplier) {
        return obj == null ? (supplier == null ? null : (String)supplier.get()) : obj.toString();
    }

    public static void wait(Object obj, Duration duration) throws InterruptedException {
        DurationUtils.accept(obj::wait, DurationUtils.zeroIfNull(duration));
    }


}
