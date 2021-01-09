package jvm.gc;

/**
 * @author: ljf
 * @date: 2021/1/8 6:50
 * @description: 1.对象可以在被GC的时候进行自救
 * 2.这种自救的机会只有一次，因为一个对象的finalize方法最多只会被系统自动调用一次
 * @modified By:
 * @version: $ 1.0
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC saveHook = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.saveHook = this;
    }

    public static void main(String[] args) throws InterruptedException {
        saveHook = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        saveHook = null;
        System.gc();
        //因为finalize方法优先级很低，暂停0.5s，以等待他
        Thread.sleep(5000);
        if (saveHook != null) {
            saveHook.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        //系统只会调用一次finalize方法，第二次自救失败。
        saveHook = null;
        System.gc();
        //因为finalize方法优先级很低，暂停0.5s，以等待他
        Thread.sleep(5000);
        if (saveHook != null) {
            saveHook.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

    }
}
