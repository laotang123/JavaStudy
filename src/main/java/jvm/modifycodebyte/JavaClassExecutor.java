package jvm.modifycodebyte;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.Objects;

/**
 * Javaclass执行工具
 *
 * @author zzm
 */
public class JavaClassExecutor {

    /**
     * 执行外部传过来的代表一个Java类的Byte数组<br>
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     *
     * @param classByte 代表一个Java类的Byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modifyBytes = cm.modifyUTF8Constant("java/lang/System", "jvm/modifycodebyte/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class<?> clazz = loader.loadByte(modifyBytes); //在不影响原有项目的加载机制前提下，通过开放defineClass方法多次载入指定类
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    public static void main(String[] args) throws IOException {
        String classPath = "target/classes/jvm/modifycodebyte/ServerHandler.class";
        File file = new File(classPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];

        int readCount = fileInputStream.read(buffer);

        String logPath = "./trace_log.txt";
        FileOutputStream fos = new FileOutputStream(logPath);

        if (readCount > 0) {
            String log = JavaClassExecutor.execute(buffer);
            System.out.println(log);
            fos.write(log.getBytes());
        }
    }
}


