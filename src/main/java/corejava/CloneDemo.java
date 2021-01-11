package corejava;

/**
 * @author: ljf
 * @date: 2021/1/11 9:04
 * @description: clone为object方法
 * 默认为浅拷贝
 * <p>
 * 手动实现深拷贝：在进行clone时将没有实现Cloneable的属性 重新new对象赋值
 * 或者属性都实现Cloneable接口
 * @modified By：
 * @version: $ 1.0
 */
class Data {
    double value;
}


class ShadeEmployee implements Cloneable {
    double salary;
    String name;
    Data data = new Data();


    /**
     * 浅拷贝实现
     */
    @Override
    protected ShadeEmployee clone() throws CloneNotSupportedException {
        return (ShadeEmployee) super.clone();
    }
}

class DeepcopyEmployee implements Cloneable {
    double salary;
    String name;
    Data data = new Data();


    /**
     * 浅拷贝实现
     */
    @Override
    protected DeepcopyEmployee clone() throws CloneNotSupportedException {
        DeepcopyEmployee employee = (DeepcopyEmployee) super.clone();
        employee.data = new Data();
        return employee;
    }
}


public class CloneDemo {
    public static void shadeCopy() {
        ShadeEmployee employee = new ShadeEmployee();
        employee.salary = 2000;
        employee.name = "tom";
        employee.data.value = 90.9;


        ShadeEmployee copy1 = null;
        try {
            copy1 = employee.clone();
            copy1.salary = 5000;
            copy1.data.value = 88.8;

            //浅拷贝，被拷贝对象属性为引用对象，共享。即employee中的data
            System.out.println("拷贝前：" + employee.salary + " " + employee.name + " " + employee.data.value);
            System.out.println("拷贝后：" + copy1.salary + " " + copy1.name + " " + employee.data.value + " ");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    public static void deepcopy() {
        DeepcopyEmployee employee = new DeepcopyEmployee();
        employee.salary = 2000;
        employee.name = "tom";
        employee.data.value = 90.9;


        DeepcopyEmployee copy1 = null;
        try {
            copy1 = employee.clone();
            copy1.salary = 5000;
            copy1.data.value = 88.8;

            //浅拷贝，被拷贝对象属性为引用对象，共享。即employee中的data
            System.out.println("拷贝前：" + employee.salary + " " + employee.name + " " + employee.data.value);
            System.out.println("拷贝后：" + copy1.salary + " " + copy1.name + " " + copy1.data.value + " ");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        shadeCopy();
        deepcopy();
    }
}
