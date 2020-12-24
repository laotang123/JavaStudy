package corejava.exception;

import net.mindview.util.Null;

import java.util.Optional;

/**
 * @author: ljf
 * @date: 2020/12/24 11:22
 * @description: 防止空指针异常，先判断
 * 或者使用jdk8的Option类 NPE问题 NullPointerException
 * @modified By：
 * @version: $ 1.0
 */
public class PackageException {
    public static Optional<Integer> f(){
        Integer value = null;
        return Optional.empty();
    }
    public static void main(String[] args) {
        Optional<Integer> value = f();
        System.out.println(value.hashCode());
        Integer integer = value.orElse(3);
        System.out.println(integer);
    }
}
