package com.helloboot.util.base;

import java.util.StringTokenizer;

/**
 * @author lujianhao
 * @date 2019/5/8
 */
public final class ArrayUtils {

    private static final String STRING_EMPTY = "";
    private static final String STRING_COMMA = ",";
    private static final int[] EMPTY_INT = new int[0];
    private static final Integer[] EMPTY_WRAPPER_INTEGER = new Integer[0];
    private static final long[] EMPTY_LONG = new long[0];
    private static final Long[] EMPTY_WRAPPER_LONG = new Long[0];

    public static boolean isEmpty(Object[] objs) {
        return objs == null || objs.length == 0;
    }

    public static boolean isNotEmpty(Object[] objs) {
        return !isEmpty(objs);
    }

    public static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    public static boolean isNotEmpty(byte[] objs) {
        return !isEmpty(objs);
    }

    public static boolean isEmpty(char[] chars) {
        return chars == null || chars.length == 0;
    }

    public static boolean isNotEmpty(char[] chars) {
        return !isEmpty(chars);
    }

    public static boolean isEmpty(int[] ints) {
        return ints == null || ints.length == 0;
    }

    public static boolean isNotEmpty(int[] objs) {
        return !isEmpty(objs);
    }

    public static boolean isEmpty(long[] longs) {
        return longs == null || longs.length == 0;
    }

    public static boolean isNotEmpty(long[] longs) {
        return !isEmpty(longs);
    }

    public static boolean isEmpty(double[] doubles) {
        return doubles == null || doubles.length == 0;
    }

    public static boolean isNotEmpty(double[] doubles) {
        return !isEmpty(doubles);
    }

    public static boolean isEmpty(float[] floats) {
        return floats == null || floats.length == 0;
    }

    public static boolean isNotEmpty(float[] floats) {
        return !isEmpty(floats);
    }

    public static boolean isEmpty(short[] shorts) {
        return shorts == null || shorts.length == 0;
    }

    public static boolean isNotEmpty(short[] shorts) {
        return !isEmpty(shorts);
    }

    public static <T> T[] emptyToDefault(T[] values, T[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static byte[] emptyToDefault(byte[] values, byte[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static short[] emptyToDefault(short[] values, short[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static int[] emptyToDefault(int[] values, int[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static long[] emptyToDefault(long[] values, long[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static float[] emptyToDefault(float[] values, float[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static double[] emptyToDefault(double[] values, double[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static char[] emptyToDefault(char[] values, char[] def) {
        return values == null || values.length == 0 ? def : values;
    }

    public static Byte[] boxing(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            Byte[] objs = new Byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                objs[i] = bytes[i];
            }
            return objs;
        }
        return null;
    }

    public static Short[] boxing(short[] shorts) {
        if (shorts != null && shorts.length > 0) {
            Short[] objs = new Short[shorts.length];
            for (int i = 0; i < shorts.length; i++) {
                objs[i] = shorts[i];
            }
            return objs;
        }
        return null;
    }

    public static Integer[] boxing(int[] ints) {
        if (ints != null && ints.length > 0) {
            Integer[] objs = new Integer[ints.length];
            for (int i = 0; i < ints.length; i++) {
                objs[i] = ints[i];
            }
            return objs;
        }
        return null;
    }

    public static Long[] boxing(long[] longs) {
        if (longs != null && longs.length > 0) {
            Long[] objs = new Long[longs.length];
            for (int i = 0; i < longs.length; i++) {
                objs[i] = longs[i];
            }
            return objs;
        }
        return null;
    }

    public static Float[] boxing(float[] floats) {
        if (floats != null && floats.length > 0) {
            Float[] objs = new Float[floats.length];
            for (int i = 0; i < floats.length; i++) {
                objs[i] = floats[i];
            }
            return objs;
        }
        return null;
    }

    public static Double[] boxing(double[] doubles) {
        if (doubles != null && doubles.length > 0) {
            Double[] objs = new Double[doubles.length];
            for (int i = 0; i < doubles.length; i++) {
                objs[i] = doubles[i];
            }
            return objs;
        }
        return null;
    }

    public static String toString(Object[] objs) {
        if (isNotEmpty(objs)) {
            StringBuilder buff = new StringBuilder(64);
            for (Object obj : objs) {
                if (obj != null) {
                    String str = obj.toString();
                    if (str != null && str.trim().length() != 0) {
                        if (buff.length() > 0) {
                            buff.append(STRING_COMMA);
                        }
                        buff.append(str);
                    }
                }
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(byte[] bytes) {
        if (isNotEmpty(bytes)) {
            StringBuilder buff = new StringBuilder(64);
            for (long l : bytes) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(l);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(short[] shorts) {
        if (isNotEmpty(shorts)) {
            StringBuilder buff = new StringBuilder(64);
            for (short s : shorts) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(s);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(int[] ints) {
        if (isNotEmpty(ints)) {
            StringBuilder buff = new StringBuilder(64);
            for (int i : ints) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(i);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(long[] longs) {
        if (isNotEmpty(longs)) {
            StringBuilder buff = new StringBuilder(64);
            for (long l : longs) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(l);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(float[] floats) {
        if (isNotEmpty(floats)) {
            StringBuilder buff = new StringBuilder(64);
            for (float f : floats) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(f);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(double[] doubles) {
        if (isNotEmpty(doubles)) {
            StringBuilder buff = new StringBuilder(64);
            for (double d : doubles) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(d);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static String toString(char[] chars) {
        if (isNotEmpty(chars)) {
            StringBuilder buff = new StringBuilder(64);
            for (char c : chars) {
                if (buff.length() > 0) {
                    buff.append(STRING_COMMA);
                }
                buff.append(c);
            }
            return buff.toString();
        }
        return STRING_EMPTY;
    }

    public static int[] parseInt(String str) {
        if (isEmpty(str)) {
            return EMPTY_INT;
        }
        str = str.trim();
        StringTokenizer st = new StringTokenizer(str, STRING_COMMA);
        int[] array = new int[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            String tmp = (String) st.nextToken();
            array[i++] = Integer.parseInt(tmp);
        }
        return array;
    }

    public static Integer[] parseInteger(String str) {
        if (isEmpty(str)) {
            return EMPTY_WRAPPER_INTEGER;
        }
        str = str.trim();
        StringTokenizer st = new StringTokenizer(str, STRING_COMMA);
        Integer[] array = new Integer[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            String tmp = (String) st.nextToken();
            array[i++] = Integer.parseInt(tmp);
        }
        return array;
    }


    public static long[] parseLong(String str) {
        if (isEmpty(str)) {
            return EMPTY_LONG;
        }
        str = str.trim();
        StringTokenizer st = new StringTokenizer(str, STRING_COMMA);
        long[] array = new long[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            String tmp = (String) st.nextToken();
            array[i++] = Long.valueOf(tmp);
        }
        return array;
    }

    public static Long[] parseLonger(String str) {
        if (isEmpty(str)) {
            return EMPTY_WRAPPER_LONG;
        }
        str = str.trim();
        StringTokenizer st = new StringTokenizer(str, STRING_COMMA);
        Long[] array = new Long[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            String tmp = (String) st.nextToken();
            array[i++] = Long.valueOf(tmp);
        }
        return array;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}
