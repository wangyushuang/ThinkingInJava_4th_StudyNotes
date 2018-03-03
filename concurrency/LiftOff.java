//: concurrency/LiftOff.java
// Demonstration of the Runnable interface.
//并发编程
public class LiftOff implements Runnable {
  protected int countDown = 10; // Default
  private static int taskCount = 0;
  private final int id = taskCount++;
  public LiftOff() {}
  public LiftOff(int countDown) {
    this.countDown = countDown;
  }
  public String status() {
    return "#" + id + "(" +
      (countDown > 0 ? countDown : "Liftoff!") + ")\n";
  }
  public void run() {
    while(countDown-- > 0) {
      System.out.print(status());
      Thread.yield();
    }
  }
  public static void main(String[] args){
  	LiftOff launch=new LiftOff();
  	launch.run();
  }
} ///:~
