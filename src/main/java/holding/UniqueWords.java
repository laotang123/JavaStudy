
/*
 * @Author: ljf
 * @Date: 2020-11-28 18:39:53
 * @LastEditTime: 2020-11-28 18:45:47
 * @Description: 
 */
package holding;

import java.util.*;
import net.mindview.util.*;

public class UniqueWords {
  public static void main(String[] args) {
    Set<String> words = new TreeSet<String>(new TextFile("SetOperations.java", "\\W+"));
    System.out.println(words);
  }
} /*
   * Output: [A, B, C, Collections, D, E, F, G, H, HashSet, I, J, K, L, M, N,
   * Output, Print, Set, SetOperations, String, X, Y, Z, add, addAll, added, args,
   * class, contains, containsAll, false, from, holding, import, in, java, main,
   * mindview, net, new, print, public, remove, removeAll, removed, set1, set2,
   * split, static, to, true, util, void]
   */// :~
