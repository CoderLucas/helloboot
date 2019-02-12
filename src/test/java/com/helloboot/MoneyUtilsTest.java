package com.helloboot;

import com.helloboot.util.other.MoneyUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lujianhao
 * @date 2019/2/12
 */
public class MoneyUtilsTest {
    @Test
    public void number2CNMontray() {
        System.out.println(MoneyUtils.number2CNMontray("156454654"));
        System.out.println(MoneyUtils.number2CNMontray(BigDecimal.valueOf(156454654)));
    }

    @Test
    public void accountantMoney() {
        System.out.println(MoneyUtils.accountantMoney(BigDecimal.valueOf(156454654)));
        System.out.println(MoneyUtils.getAccountantMoney(BigDecimal.valueOf(156454654),1,10000));
        System.out.println(MoneyUtils.getFormatMoney(BigDecimal.valueOf(156454654),1,10000));
    }
}
