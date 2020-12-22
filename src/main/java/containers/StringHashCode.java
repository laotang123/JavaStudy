/*
 * @Author: ljf
 * @Date: 2020-11-28 18:07:12
 * @LastEditTime: 2020-11-28 18:42:01
 * @Description: 
 */
package containers;

public class StringHashCode {
  public static void main(String[] args) {
    String[] hellos = "Hello Hello".split(" ");
    System.out.println(hellos[0].hashCode());
    System.out.println(hellos[1].hashCode());
  }
} /*
   * Output: (Sample) 69609650 69609650
   */// :~
