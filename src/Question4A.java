//Question 4
//        a) Design and Implement LFU caching
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.*;

 class LFUCache {
    // A class to represent the cache item
    private static class CacheItem {
        int key;
        int value;
        int frequency;

        public CacheItem(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    private int capacity; // The greatest number of things the cache can store
    private Map<Integer, CacheItem> cache; // The cache map to store the items
    private Map<Integer, LinkedList<CacheItem>> frequencyMap; // The frequency map to store the items based on their frequency
    private int minFrequency; // The minimum frequency of the items in the cache

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        CacheItem item = cache.get(key);
        updateFrequency(item);

        return item.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        // If the key already exists in the cache, update the value and frequency
        if (cache.containsKey(key)) {
            CacheItem item = cache.get(key);
            item.value = value;
            updateFrequency(item);
        } else {
            // If the cache is full, remove the least frequently used item
            if (cache.size() == capacity) {
                removeLFUItem();
            }

            // Add the new item to the cache and frequency map
            CacheItem item = new CacheItem(key, value, 1);
            cache.put(key, item);
            frequencyMap.computeIfAbsent(1, k -> new LinkedList<>()).addFirst(item);
            minFrequency = 1;
        }
    }

    private void updateFrequency(CacheItem item) {
        int frequency = item.frequency;
        frequencyMap.get(frequency).remove(item);

        // If the frequency map for the current frequency is empty and it is the minimum frequency,
        // update the minimum frequency
        if (frequency == minFrequency && frequencyMap.get(frequency).isEmpty()) {
            minFrequency++;
        }

        item.frequency++;
        frequencyMap.computeIfAbsent(item.frequency, k -> new LinkedList<>()).addFirst(item);
    }

    private void removeLFUItem() {
        LinkedList<CacheItem> list = frequencyMap.get(minFrequency);
        CacheItem item = list.removeLast();
        cache.remove(item.key);
    }

    public static void main(String[] args) {
        // Create a new LFU cache with a capacity of 3
        LFUCache cache = new LFUCache(3);

        // Add some items to the cache
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.put(4, 40);

        // The cache should contain items 2, 3, and 4
        System.out.println(cache.get(1)); // -1 (item not found)
        System.out.println(cache.get(2)); // 20
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40

        // Add a new item and update an existing item
        cache.put(5, 50);
        cache.put(2, 22);}
}

