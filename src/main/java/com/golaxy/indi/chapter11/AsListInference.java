package com.golaxy.indi.chapter11;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ：ljf
 * @date ：2020/11/15 17:26
 * @description：批量向容器中添加元素
 * @modified By：
 * @version: $ 1.0
 */
class Snow {
}

class Power extends Snow {
}

class Light extends Snow {
}

class Heavy extends Snow {
}

public class AsListInference {
    public static void main(String[] args) {
        ArrayList<Snow> snowArrayList = new ArrayList<>();
        Collections.addAll(snowArrayList, new Heavy(), new Light(), new Power());

        for (Snow snow : snowArrayList) {
            System.out.println(snow);
        }
        System.out.println(snowArrayList);

    }
}
