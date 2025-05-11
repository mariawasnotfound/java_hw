package edu.phystech.hw4.lock;

public class CASTicketLock {
    private int ticket = 0;
    private int cur = 0;

    public synchronized void lock() {
        int myTicket = ticket++;
        while (cur != myTicket) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public synchronized void unlock() {
        cur++;
        notifyAll();
    }
}