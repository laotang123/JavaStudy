package corejava.callback;

/**
 * @author: ljf
 * @date: 2021/1/13 9:25
 * @description: 回调函数的接口类，回调链中传入接口的实现
 * @modified By：
 * @version: $ 1.0
 */
public interface Filter {

    void doFilter(String request);
}
