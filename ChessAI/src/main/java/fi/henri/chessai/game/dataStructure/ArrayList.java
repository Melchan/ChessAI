/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.dataStructure;

/**
 *
 * @author KavioElain
 */
public class ArrayList<T> {

    private int index;
    private Object[] o;
    private T[] e;
    private int lenght;
    boolean contains;

    public ArrayList() {
        clear();
    }

    /**
     * adds next thing at the end of arraylist and increases size by 1;
     */
    public void add(T thing) {
        e[index] = thing;
        index++;
        if (index >= e.length) {
            increaseSize();
        }
    }

    /**
     * gets i'th object from list
     *
     * @param i
     * @return i'th object.
     */
    public T get(int i) {
        return e[i];
    }

    /**
     * tells if arraylist is empty or not;
     *
     * @return
     */
    public boolean isEmpty() {
        return index == 0;
    }

    /**
     * empties and initializes arraylist.
     */
    public void clear() {
        this.lenght = 2;
        this.index = 0;
        createArray();
    }

    /**
     * will tell size of current arraylist.
     *
     * @return
     */
    public int size() {
        return index;
    }

    public void addAll(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }
    /**
     * removes i many things from end of list.
     * @param i 
     */
    public void remove(int i) {
        for (int j = i; j > 0; j--) {
            System.out.println("index is " + index);
            System.out.println(i + " " + j);
            e[index] = null;
            index--;
        }
    }
    
    public boolean contains(T t) {
        for(int i = 0; i < index; i++) {
            if (t.equals(e[i])) {
                return true;
            }
        }
        return false;
    }

    private void createArray() {
        this.o = new Object[lenght];
        this.e = (T[]) o;
    }

    private void increaseLength() {
        this.lenght = (int) Math.pow(lenght, 2);
    }

    private void increaseSize() {
        increaseLength();
        Object tempO = new Object[lenght];
        T[] tempE = (T[]) tempO;
        for (int i = 0; i < index; i++) {
            tempE[i] = this.e[i];
        }
        this.e = tempE;
    }
    
    public int getArrayLenght() {
        return e.length;
    }
   
}