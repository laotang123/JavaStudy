package jvm;

/**
 * @author: ljf
 * @date: 2021/1/7 7:23
 * @description: VM args: -Xss128k
 * 测试栈溢出
 * 现在的栈容量是不可能动态分配的。创建线程申请内存已经确定。栈帧越大，栈容量越小
 * @modified By:
 * @version: $ 1.0
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        long unused1, unused2, unused3, unused4, unused5,
                unused6, unused7, unused8, unused9, unused10;
        stackLength++;
        stackLeak();

        unused1=unused2= unused3=unused4= unused5=
                unused6=unused7= unused8= unused9=unused10 = 0;
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();

        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + javaVMStackSOF.stackLength);
//            e.printStackTrace();
        }
    }
}
