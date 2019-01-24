package com.helloboot.reflect;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class TestHierarchicalMethodsSubclass extends TestHierarchicalMethodsBase {
    public static String PUBLIC_RESULT  = "PUBLIC_SUB";
    public static String PRIVATE_RESULT = "PRIVATE_SUB";

    // Both of these are hiding fields in the super type
    private int invisibleField2;
    public  int visibleField2;

    private int invisibleField3;
    public  int visibleField3;

    private String priv_method(int number) {
        return PRIVATE_RESULT;
    }

    private String pub_method(Integer number) {
        return PRIVATE_RESULT;
    }
}
