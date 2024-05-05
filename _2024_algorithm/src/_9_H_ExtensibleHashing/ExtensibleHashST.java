package _9_H_ExtensibleHashing;

import java.util.ArrayList;
import java.util.Arrays;

class Item<K, V> {
	K key;
	V value;
	
	public Item(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

class AtomicBucket<K, V> extends Bucket<K, V> {
	public static final int atomicMaxSize = 4; 
	
	private Item<K, V>[] items;
	
	public AtomicBucket(int size, int i) {
		super(size, i);
		if (size < 1 || size > atomicMaxSize)
			return;
		this.items = new Item[size];
	}
	
	public void put(Item<K, V> item) {
		items[n++] = item; 
	}
	
	public V update(K key, V value) {
		for (int idx = 0; idx < n; idx++)
			if (items[idx].key.equals(key)) {
				V updatedValue = items[idx].value;
				items[idx].value = value;
				return updatedValue;
			}
		return null;
	}
	
	public V get(K key) {
		for (int idx = 0; idx < n; idx++)
			if (items[idx].key.equals(key))
				return items[idx].value;
		return null;
	}
	
	public Item<K, V>[] popAll() {
		Item<K, V>[] allItems = Arrays.copyOf(items, n);
		Arrays.fill(items, null);
		n = 0;
		
		return allItems;
	}
	
	public Item<K, V>[] getAll() {
		return Arrays.copyOf(items, n);
	}
	
	public ArrayList<K> keys() {
		ArrayList<K> keys = new ArrayList<K>(n);
		for (int idx = 0; idx < n; idx++)
			keys.add(items[idx].key);
		return keys;
	}
}

class HashBucket<K, V> extends Bucket<K, V> {
	ExtensibleHashST<K, V> ht;
	
	public HashBucket(int size, int i) {
		super(size, i);
		ht = new ExtensibleHashST<K, V>(AtomicBucket.atomicMaxSize);
	}
	
	public void put(Item<K, V> item) {
		ht.put(item);
		n++;
	}
	
	public V update(K key, V value) {
		return ht.update(key, value);
	}
	
	public V get(K key) {
		return ht.get(key);
	}
	
	public Item<K, V>[] popAll() {
		n = 0;
		return ht.popAll();
	}
	
	public Item<K, V>[] getAll() {
		return ht.getAll();
	}
	
	public ArrayList<K> keys() { 
		return (ArrayList<K>) ht.keys();
	}
}

abstract class Bucket<K, V> {
	public int i;
	int size;
	protected int n;
	
	public Bucket(int size, int i) {
		this.size = size;
		this.i = i;
		this.n = 0;
	}

	public boolean isMax() {
		return n == size;
	}
	
	public abstract void put(Item<K, V> item) ;
	
	public abstract V update(K key, V value);
	
	public abstract V get(K key);
	
	public abstract Item<K, V>[] popAll();
	
	public abstract Item<K, V>[] getAll();
	
	public abstract ArrayList<K> keys();
}

class BucketFactory {
	public static Bucket makeBucketFactory(int size, int i) {
		if (size <= AtomicBucket.atomicMaxSize)
			return new AtomicBucket(size, i);
		else 
			return new HashBucket(size, i);
	}
}

public class ExtensibleHashST<K, V> {
	private int bucketSize;
	private int bucketCount = 1;
	private int recordCount = 0;
	private int i = 0;
	private Bucket<K, V>[] directory = new Bucket[1];
	
	public ExtensibleHashST(int bucketSize) {
		this.bucketSize = bucketSize;
		directory[0] = BucketFactory.makeBucketFactory(bucketSize, 0);
	}
	
	public ExtensibleHashST() {
		this(4);
	}
	
	private int hash(K key) {
		return key.hashCode() & ((1 << i) - 1);
	}
	
	private int HashToDir(int hashValue) {
		int d = 0;
		for (int idx = 0; idx < i; idx++)
			d |= (((hashValue & (1 << idx)) >> idx) << (i - idx - 1));
		return d;
	}
	
	public boolean contains(K key) {
		return null != directory[HashToDir(hash(key))].get(key);
	}
	
	public boolean isEmpty() {
		return recordCount == 0;
	}
	
	public int size() {
		return recordCount;
	}
	
	public V get(K key) {
		return directory[HashToDir(hash(key))].get(key);
	}
	
	private void sizeUp() {
		Bucket<K, V>[] newDir = new Bucket[directory.length * 2]; 
		for (int idx = 0; idx < directory.length; idx++)
			newDir[(idx << 1)] = newDir[(idx << 1) + 1] = directory[idx];
		
		i++;
		directory = newDir;
	}
	
	private int getFirstIdxOfBucket(int dir, int bucketI) {
		return ((dir >> (i - bucketI)) << (i - bucketI));
	}
	
	private int getBucketDomain(int bucketI) {
		return (1 << (i - bucketI));
	}
	
	private void split(K key) {
		if (this.i == directory[HashToDir(hash(key))].i) {
			sizeUp();
		}

		int dir = HashToDir(hash(key));
		directory[dir].i++;
		Bucket<K, V> newBucket = BucketFactory.makeBucketFactory(bucketSize, directory[dir].i);
		bucketCount++;

		int newBucketDomain = getBucketDomain(directory[dir].i);
		int newDir = getFirstIdxOfBucket(dir, directory[dir].i - 1) + newBucketDomain;
		
		Item<K, V>[] lastBucketItems = directory[dir].popAll();
		for (;newBucketDomain > 0; newBucketDomain--)
			directory[newDir++] = newBucket;
		for (Item<K, V> item : lastBucketItems)
			directory[HashToDir(hash(item.key))].put(item);
	}
	
	public V put(K key, V value) {
		recordCount++;
		if (contains(key))
			return directory[HashToDir(hash(key))].update(key, value);
		else {
			while (directory[HashToDir(hash(key))].isMax())
				split(key);
			directory[HashToDir(hash(key))].put(new Item<K, V>(key, value));
			return null;
		}
	}
	
	public V put(Item<K, V> item) {
		recordCount++;
		if (contains(item.key))
			return directory[HashToDir(hash(item.key))].update(item.key, item.value);
		else {
			while (directory[HashToDir(hash(item.key))].isMax())
				split(item.key);
			directory[HashToDir(hash(item.key))].put(item);
			return null;
		}
	}
	
	public V update(K key, V value) {
		return directory[HashToDir(hash(key))].update(key, value);
	}
	
	public void delete(K key) {
		
	}
	
	public Item<K, V>[] popAll() {
		ArrayList<Item<K, V>> result = new ArrayList(recordCount);
		for (Bucket<K, V> b : directory)
			for (Item<K, V> item : b.popAll())
				result.add(item);
		return result.toArray(new Item[result.size()]);
	}
	
	public Item<K, V>[] getAll() {
		ArrayList<Item<K, V>> result = new ArrayList(recordCount);
		Bucket lastBucket = null;
		for (int idx = 0; idx < directory.length; idx++)
			if (lastBucket != directory[idx]) {
				lastBucket = directory[idx];
				for (Item<K, V> item : lastBucket.getAll())
					result.add(item);
			}
		return result.toArray(new Item[result.size()]);
	}
	
	public Iterable<K> keys() {
		ArrayList<K> keys = new ArrayList(bucketCount);
		for (Bucket b : directory)
			keys.addAll(b.keys());
		return keys;
	}
	
	public void summaryInfo() {
		System.out.println("Global i = " + i + "비트, (key, value) 쌍의 수 = " + recordCount + ", 버킷의 수 = " + bucketCount);
	}
	
	public void detailInfo() {
		summaryInfo();
		
		Bucket lastBucket = null;
		int bucketCnt = -1;
		for (int idx = 0; idx < directory.length; idx++) {
			if (lastBucket != directory[idx]) {
				lastBucket = directory[idx];
				bucketCnt++;
			}
			System.out.println("Directory[" + idx + "] -> Bucket" + bucketCnt);
		}
		
		System.out.println();
		bucketCnt = 0;
		for (int idx = 0; idx < directory.length; idx += (1 << (i - directory[idx].i)), bucketCnt++) {
			System.out.println("Bucket " + bucketCnt + ": size = " + directory[idx].size + ", nbits = " + "" + directory[idx].i + "비트");
			for (Item<K, V> item : directory[idx].getAll())
				System.out.println("    " + HashToDir(hash(item.key)) + " : " + item.value);
		}
	}
}
