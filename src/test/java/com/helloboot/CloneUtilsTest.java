package com.helloboot;

import com.helloboot.util.base.CloneUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author lujianhao
 * @date 2018/12/25
 */
public class CloneUtilsTest {
    @Test
    public void deepClone() throws Exception {
        Person person = new Person("Lucas");
        Person clonePerson = CloneUtils.deepClone(person);
        System.out.println(person);
        System.out.println(clonePerson);
        Assert.assertNotEquals(person, clonePerson);
    }

    static class Person implements Serializable {

        String name;
        int    gender;

        public Person(String name) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", gender=" + gender + '}';
        }
    }
}
