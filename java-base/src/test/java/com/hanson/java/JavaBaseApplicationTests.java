package com.hanson.java;

import com.hanson.java.base.ComplexCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaBaseApplicationTests {

    @Test
    void contextLoads() {
        unittest unitTestInstance = new unittest();
        ComplexCalculator complexCalculator = unitTestInstance.new ComplexCalculator();

        // 测试用例1：加法运算
        assert complexCalculator.calculate(1, 2, 3) == 5;

        // 测试用例2：减法运算
        assert complexCalculator.calculate(5, 3, 2) == 2;

        // 测试用例3：乘法运算
        assert complexCalculator.calculate(4, 5, 3) == 20;

        // 测试用例4：除法运算
        assert complexCalculator.calculate(10, 2, 4) == 5;

        // 测试用例5：除数为0的情况
        assert complexCalculator.calculate(10, 0, 4) == 0;

        // 测试用例6：输入非数字字符
        assert complexCalculator.calculate("a", "b", 4) == 0;

        // 测试用例7：输入负数
        assert complexCalculator.calculate(-1, -2, 3) == -3;

        System.out.println("所有测试用例通过！");
    }

}
