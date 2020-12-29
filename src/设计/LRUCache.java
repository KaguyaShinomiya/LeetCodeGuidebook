package 设计;

import utils.DListNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Class: LRUCache
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-29 00:39
 */
public class LRUCache {
    /**
     * 解法1: 直接继承LinkedHashMap，重写removeEldestEntry方法
     */
    class LRUCacheSolution1 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCacheSolution1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * 解法2: 使用哈希表的值存储双向链表的节点
     */
    public class LRUCacheSolution2 {
        private int capacity;
        private Map<Integer, DListNode> cacheMap;
        private DListNode head;
        private DListNode tail;
        private int size;

        public LRUCacheSolution2(int capacity) {
            cacheMap = new HashMap<>();
            head = new DListNode();
            tail = new DListNode();
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
            // size的作用在于判断是否需要删除尾结点时，O(1)时间内返回哈希表大小
            size = 0;
        }

        public int get(int key) {
            if (!cacheMap.containsKey(key)) {
                return -1;
            }
            DListNode valNode = cacheMap.get(key);
            // 将valNode从链表中原来的位置中解开并加入到链表中head节点之后
            moveToHead(valNode);
            return valNode.val;
        }

        public void put(int key, int value) {
            DListNode valNode = cacheMap.get(key);
            if (valNode == null) {
                valNode = new DListNode(key, value);
                cacheMap.put(key, valNode);
                size++;
                // 将valNode加入到链表中head节点之后
                addToHead(valNode);
                if (size > capacity) {
                    DListNode res = removeTail();
                    // 注意不仅需要从双向链表中移除尾部节点，还需要从哈希表中移除该节点，并且降低size
                    cacheMap.remove(res.key);
                    size--;
                }
            } else {
                valNode.val = value;
                moveToHead(valNode);
            }
        }

        private void removeNode(DListNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        private void addToHead(DListNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void moveToHead(DListNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DListNode removeTail() {
            DListNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}
