//: concurrency/CallableDemo.java
//如果希望任务完成时能够返回一个值，应当使用Callable接口，而不是Runnable接口
// 必须使用ExecutorService.submit()调用call方法，产生Future对象，并用call返回值的特定类型进行参数化。
// 可以使用isDone()方法查询Future是否已经完成，若完成，则用get()方法获取结果
// 也可以不适用isDone()方法，直接调用get()，get()将阻塞，直到结果准备就绪
import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {//泛型接口，具体类型为call的返回值类型
  private int id;
  public TaskWithResult(int id) {
    this.id = id;
  }
  public String call() {
    return "result of TaskWithResult " + id;
  }
}

public class CallableDemo {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    ArrayList<Future<String>> results =
      new ArrayList<Future<String>>();
    for(int i = 0; i < 10; i++)
      results.add(exec.submit(new TaskWithResult(i)));
    for(Future<String> fs : results){
      try {
        // get() blocks until completion:
        System.out.println(fs.get());
      } catch(InterruptedException e) {
        System.out.println(e);
        return;
      } catch(ExecutionException e) {
        System.out.println(e);
      } finally {
        exec.shutdown();
      }
    }
  }
} /* Output:
result of TaskWithResult 0
result of TaskWithResult 1
result of TaskWithResult 2
result of TaskWithResult 3
result of TaskWithResult 4
result of TaskWithResult 5
result of TaskWithResult 6
result of TaskWithResult 7
result of TaskWithResult 8
result of TaskWithResult 9
*///:~
