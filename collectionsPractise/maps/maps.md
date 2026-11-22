| Thread state | CPU consumption |
|---|---|
| RUNNABLE and executing | Consumes CPU |
| BLOCKED waiting for monitor | Normally does not consume CPU |
| WAITING | Normally does not consume CPU |
| TIMED_WAITING | Normally does not consume CPU |


High CPU:
Thread executing millions of map operations.

High waiting:
100 threads waiting for one synchronized map lock.




Map<Integer, Integer> map = new ConcurrentHashMap<>();

map.put(1, 0);

Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {

        synchronized (map) {
            int value = map.get(1);
            map.put(1, value + 1);
        }
    }
};

Thread t1 = new Thread(task);
Thread t2 = new Thread(task);

t1.start();
t2.start();



Map<Integer, Integer> map = new ConcurrentHashMap<>();

map.put(1, 0);

Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {
        map.compute(1, (key, value) -> value + 1);
    }
};

Thread t1 = new Thread(task);
Thread t2 = new Thread(task);

t1.start();
t2.start();