package com.helloboot.util.base;

/**
 * @author lujianhao
 * @date 2019/5/28
 */
public final class BitUtil {
    private final static int[] INTEGER_MASKS = new int[32];

    static {
        for (int i = 0; i < 32; i++) {
            INTEGER_MASKS[i] = (1 << i);
        }
    }

    private final static long[] LONG_MASKS = new long[64];

    static {
        for (int i = 0; i < 64; i++) {
            LONG_MASKS[i] = (1l << i);
        }
    }

    /**
     * 判断int型的vlaue的第index(0<=index<=31)位是否为1
     *
     * @param value
     * @param index
     * @return 对应有效位置为1则返回true，否则为false
     */
    public static boolean is(int value, int index) {
        return index > -1 && index < 32 && (value & INTEGER_MASKS[index]) != 0;
    }

    /**
     * 设置int型的value的第index(0<=index<=31)位为1
     *
     * @param value
     * @param index
     * @return 若位置有效则返回更新后的value，否则返回value
     */
    public static int set(int value, int index) {
        return index > -1 && index < 32 ? value | INTEGER_MASKS[index] : value;
    }

    /**
     * 设置int型的value的第index(0<=index<=31)位为0
     *
     * @param value
     * @param index
     * @return 若位置有效则返回更新后的value，否则返回value
     */
    public static int clear(int value, int index) {
        return index > -1 && index < 32 ? value & (-1 ^ INTEGER_MASKS[index]) : value;
    }

    /**
     * 判断long型的vlaue的第index(0<=index<=63)位是否为1
     *
     * @param value
     * @param index
     * @return 对应有效位置为1则返回true，否则为false
     */
    public static boolean is(long value, int index) {
        return index > -1 && index < 64 && (value & LONG_MASKS[index]) != 0;
    }

    /**
     * 设置long型的value的第index(0<=index<=63)位为1
     *
     * @param value
     * @param index
     * @return 若位置有效则返回更新后的value，否则返回value
     */
    public static long set(long value, int index) {
        return index > -1 && index < 64 ? value | LONG_MASKS[index] : value;
    }

    /**
     * 设置long型的value的第index(0<=index<=63)位为0
     *
     * @param value
     * @param index
     * @return 若位置有效则返回更新后的value，否则返回value
     */
    public static long clear(long value, int index) {
        return index > -1 && index < 64 ? value & (-1l ^ LONG_MASKS[index]) : value;
    }

    /**
     * 获取运算数指定位置的值<br>
     * 例如： 0000 1011 获取其第 0 位的值为 1, 第 2 位 的值为 0<br>
     *
     * @param source 需要运算的数
     * @param pos    指定位置 (0<=pos<=7)
     * @return 指定位置的值(0 or 1)
     */
    public static byte getBitValue(byte source, int pos) {
        return (byte) ((source >> pos) & 1);
    }


    /**
     * 将运算数指定位置的值置为指定值<br>
     * 例: 0000 1011 需要更新为 0000 1111, 即第 2 位的值需要置为 1<br>
     *
     * @param source 需要运算的数
     * @param pos    指定位置 (0<=pos<=7)
     * @param value  只能取值为 0, 或 1, 所有大于0的值作为1处理, 所有小于0的值作为0处理
     * @return 运算后的结果数
     */
    public static byte setBitValue(byte source, int pos, byte value) {

        byte mask = (byte) (1 << pos);
        if (value > 0) {
            source |= mask;
        } else {
            source &= (~mask);
        }

        return source;
    }


    /**
     * 将运算数指定位置取反值<br>
     * 例： 0000 1011 指定第 3 位取反, 结果为 0000 0011; 指定第2位取反, 结果为 0000 1111<br>
     *
     * @param source
     * @param pos    指定位置 (0<=pos<=7)
     * @return 运算后的结果数
     */
    public static byte reverseBitValue(byte source, int pos) {
        byte mask = (byte) (1 << pos);
        return (byte) (source ^ mask);
    }


    /**
     * 检查运算数的指定位置是否为1<br>
     *
     * @param source 需要运算的数
     * @param pos    指定位置 (0<=pos<=7)
     * @return true 表示指定位置值为1, false 表示指定位置值为 0
     */
    public static boolean checkBitValue(byte source, int pos) {

        source = (byte) (source >>> pos);

        return (source & 1) == 1;
    }
}
