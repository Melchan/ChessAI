/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.dataStructure;

/**
 *
 * @author manhenri
 */
public class HashMap<K, V> {

    private HashMapStorage<K, V>[] list;
    private int lenght;
    private int storage;

    public HashMap() {
        clear();
    }

    /**
     * Initializes hashMap to starting state.
     */
    public void clear() {
        this.lenght = 64;
        this.storage = 0;
        this.list = new HashMapStorage[lenght];
        this.list[0] = new HashMapStorage<>(null, null);
    }

    /**
     * Will add key-value pair in hashMap.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = getIndex(key);
        storage++;
        if (index >= lenght) {
            this.increaseStorageSpace();
            index = getIndex(key);
        }
        list[index] = new HashMapStorage(key, value);
        handleStorageSpace();
        
    }

    /**
     * Will find value corresponding the key form hashMap.
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = getIndex(key);
        if (index >= lenght) {
            return null;
        } else if (list[index] == null) {
            return null;
        }
        return list[index].getValue();
    }

    /**
     * Will return true if key is found from hashMap.
     *
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        int index = getIndex(key);
        return list[index] != null;
    }

    /**
     * Removes key-value pair from hashMap.
     *
     * @param key
     */
    public void remove(K key) {
        int index = getIndex(key);
        if (index == 0) {
            this.put(null, null);
        } else {
            list[index] = null;
        }
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        int index = Math.abs(key.hashCode() % lenght);
        if (index == 0) {
            index++;
        }
        while (list[index] != null) {
            if (list[index].getKey().equals(key)) {
                return index;
            }
            index++;
            if (index >= lenght) {
                this.increaseStorageSpace();
                return getIndex(key);            
            }
        }

        return index;
    }

    private void handleStorageSpace() {
        if (lenght / 2 <= storage) {
            HashMapStorage<K, V>[] tempList = list;
            increaseStorageSpace();
            for (int i = 0; i < tempList.length; i++) {
                HashMapStorage<K, V> temp = tempList[i];
                if (temp == null) {
                }
                if (temp != null) {
                    this.put(temp.getKey(), temp.getValue());
                }
            }
        }
    }

    private void increaseStorageSpace() {
        this.lenght = this.lenght * 2;
        this.list = new HashMapStorage[lenght];
        this.storage = 0;
        this.list[0] = new HashMapStorage<>(null, null);
    }
}