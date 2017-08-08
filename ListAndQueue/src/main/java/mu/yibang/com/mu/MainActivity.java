package mu.yibang.com.mu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static android.R.attr.x;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
        list.add("77");
        method5();
//        System.out.println(list);
    }

    /**
     * 这种方式的问题在于，删除某个元素后，list的大小发生了变化，而你的索引也在变化，
     * 所以会导致你在遍历的时候漏掉某些元素。比如当你删除第1个元素后，继续根据索引访问第2个元素时，
     * 因为删除的关系后面的元素都往前移动了一位，所以实际访问的是第3个元素。
     * 因此，这种方式可以用在删除特定的一个元素时使用，但不适合循环删除多个元素时使用。
     */
    public void method1(){

        for(int i=0;i<list.size();i++){
//            if(list.get(i).equals("33"))
                list.remove(0);
            //[ 22, 33, 44, 55,66,77]
                list.remove(1);
            //[ 22, 44, 55,66,77]
        }
    }

    /**
     *增强for循环
     *  　　这种方式的问题在于，删除元素后继续循环会报错误信息ConcurrentModificationException，
     *  因为元素在使用的时候发生了并发的修改，导致异常抛出。但是删除完毕马上使用break跳出，则不会触发报错。


     */
    public void method2(){

        for(String x:list){
//            if(x.equals("11"))
                list.remove(0);
            //[ 22, 33, 44, 55,66,77]
                list.remove(1);
            //[ 22, 44, 55,66,77]
            break;
        }
    }

    /**
     * iterator遍历
     * 　这种方式可以正常的循环及删除。但要注意的是，使用iterator的remove方法，
     * 如果用list的remove方法同样会报上面提到的ConcurrentModificationException错误。
     */
    public void method3(){

        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String x = it.next();
           if(x.equals("11")||x.equals("22")){
                it.remove();
           }

        }
    }
    public void method4(){

        Queue pq = new LinkedBlockingQueue();
        //下面代码依次向pq中加入四个元素
        pq.offer(6);
        pq.offer(-3);
        pq.offer(9);
        pq.offer(0);
//        pq.add(6);
//        pq.add(1);
//        pq.add(9);
 //[6, -3, 9, 0]
     // 6

        System.out.println(pq);
        System.out.println(pq.poll());
    }

    /**
     * LinkedList同时表现出了双端队列、栈的用法。功能非常强大
     */
    public void method5(){
        LinkedList books = new LinkedList();

        //将字符串元素加入队列的尾部(双端队列)
        books.offer("疯狂Java讲义");
//        books.offer("疯狂Java讲义1");

        //将一个字符串元素加入栈的顶部(双端队列)
        books.push("轻量级Java EE企业应用实战");
//        books.push("轻量级Java EE企业应用实战1");

        //将字符串元素添加到队列的头(相当于栈的顶部)
      //  books.offerFirst("疯狂Android讲义");

//        for (int i = 0; i < books.size() ; i++ )
//        {
//            System.out.println(books.get(i));
//        }

//        //访问、并不删除栈顶的元素
//        System.out.println(books.peekFirst());
//        //访问、并不删除队列的最后一个元素
//        System.out.println(books.peekLast());
//        //将栈顶的元素弹出"栈"
//        System.out.println(books.pop());
//        //下面输出将看到队列中第一个元素被删除
//        System.out.println(books);
//        //访问、并删除队列的最后一个元素
//        System.out.println(books.pollLast());
        System.out.println(books.poll());
//        //下面输出将看到队列中只剩下中间一个元素：
//        //轻量级Java EE企业应用实战
//        System.out.println(books);
    }
    public void method6(){
        Stack v = new Stack();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        System.out.println(v.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(v.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(v);
    }

}
