package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EntityLocker {
    private final Map<Object, Lock> locks = new ConcurrentHashMap<>();

    public void executeProtectedCode(Object entityId, Runnable protectedCode) throws InterruptedException {
        Lock lock = locks.computeIfAbsent(entityId, k -> new ReentrantLock());
        lock.lockInterruptibly(); // need to wait if the lock is already held by another thread
        try {
            protectedCode.run();
        } finally {
            lock.unlock();
        }
    }
}




