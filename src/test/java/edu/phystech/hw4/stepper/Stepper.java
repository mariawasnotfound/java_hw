package edu.phystech.hw4.stepper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stepper {
    public enum Side {
        LEFT,
        RIGHT
    }

    private final List<Side> steps = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition leftStep_ = lock.newCondition();
    private final Condition rightStep_ = lock.newCondition();
    private boolean isLeft = true;

    public void leftStep() throws InterruptedException {
        lock.lock();
        try {
            while (!isLeft) {
                leftStep_.await();
            }
            steps.add(Side.LEFT);
            isLeft = false;
            rightStep_.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void rightStep() throws InterruptedException {
        lock.lock();
        try {
            while (isLeft) {
                rightStep_.await();
            }
            steps.add(Side.RIGHT);
            isLeft = true;
            leftStep_.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public List<Side> getHistory() {
        return new ArrayList<>(steps);
    }
}