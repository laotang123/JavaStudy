/*
 * @Author: ljf
 * @Date: 2020-11-28 18:07:12
 * @LastEditTime: 2020-11-28 18:38:49
 * @Description: 
 */
package containers;

// Demonstrates the "fail-fast" behavior.
import java.util.*;

public class FailFast {
  public static void main(String[] args) {
    Collection<String> c = new ArrayList<String>();
    Iterator<String> it = c.iterator();
    c.add("An object");
    try {
      String s = it.next();
    } catch (ConcurrentModificationException e) {
      System.out.println(e);
    }
  }
} /*
   * Output: java.util.ConcurrentModificationException
   */// :~
