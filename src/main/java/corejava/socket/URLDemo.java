package corejava.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: ljf
 * @date: 2020/12/17 6:11
 * @description: 访问网络url相关方法
 * @modified By:
 * @version: $ 1.0
 */
public class URLDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.javatpoint.com/java-tutorial");
            System.out.println("protocol: " + url.getProtocol());
            System.out.println("content: "+url.getContent());
            InputStream inputStream = url.openStream();
            int read = inputStream.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
