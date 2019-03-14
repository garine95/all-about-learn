package leetcode.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddAdd {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(new PrintThread(1, condition, lock)).start();
        new Thread(new PrintThread(0, condition, lock)).start();
    }

    static class PrintThread implements Runnable{

        Integer startCount;

        Condition toPrint;

        ReentrantLock lock;

        public PrintThread(Integer startCount, Condition toPrint, ReentrantLock lock) {
            this.startCount = startCount;
            this.toPrint = toPrint;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            while (startCount <= 100) {
                try {
                    if (startCount == 1) {
                        toPrint.await();
                    }
                    System.out.println(startCount);
                    startCount += 2;
                    toPrint.signal();//唤醒对方进入aqs等待队列（确保另一个线程已经进入aqs等待队列，这样的话下一个肯定就是另一个线程）
                    toPrint.await();//await释放锁，进入condition队列
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }
}
