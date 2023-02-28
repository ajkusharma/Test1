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


//  Here's how we can use the EntityLocker class in our  application:


EntityLocker locker = new EntityLocker();

// here protected code for entity with ID "entity1"
locker.executeProtectedCode("entity1", () -> {
    // do something with entity1
});

// given protected code for entity with ID 123
locker.executeProtectedCode(123, () -> {
    // do something with entity2
});


