package corejava.callback;

/**
 * @author: ljf
 * @date: 2021/1/13 9:32
 * @description: 内部类加回调函数实现
 * @modified By：
 * @version: $ 1.0
 */
public class Client {
    private final Filter filter;
    private final InterceptingFilter interceptingFilter;

    public Client(Filter filter) {
        this.filter = filter;
        this.interceptingFilter = new InterceptingFilter();
    }

    private class InterceptingFilter {

        public void sendMessage(String request) {
            filter.doFilter(request);
        }
    }

    public void sendMessage(String request) {
        interceptingFilter.sendMessage(request);
    }

    public static void main(String[] args) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Client client = new Client(authenticationFilter);
        client.sendMessage("Downloading...");
    }
}
