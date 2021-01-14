package corejava.callback;

/**
 * @author: ljf
 * @date: 2021/1/13 9:30
 * @description: 授权拦截请求
 * @modified By：
 * @version: $ 1.0
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(String request) {
        System.out.println("authentication filter: " + request);
    }
}
