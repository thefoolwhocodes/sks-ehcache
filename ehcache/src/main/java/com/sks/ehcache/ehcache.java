/*
 * This program intends to demonstrate the usage of ehcache.
 * It also shows a glimse of how various tiers of the ehcache can be layered.
 * This code also demonstrates the thread safety aspect of ehcache.
 */

package com.sks.ehcache;

import java.util.Random;
import org.ehcache.Cache;

public class ehcache implements Runnable{
    Cache<Integer, String> _myCache;
    Random _randomWork;
    boolean _getter;
    public ehcache(Random randomWork, boolean getter) {
        _myCache = CacheHolder.getInstance().getCache();
        _randomWork = randomWork;
        _getter = getter;
    }

    //@Override
    public void run() {
        while(true){
            String myName = Thread.currentThread().getName();
            int value = _randomWork.nextInt(10);
            if(_getter) {
                System.out.println(String.format("%s:getter with %d",myName, value));
                _myCache.get(value);
                _myCache.remove(value);
            }
            else {
                System.out.println(String.format("%s:setter with %d",myName, value));
                _myCache.put(value, Integer.toString(value));
            } 
        }       
    }
 
    public static void main(String[] args) {
        System.out.println("Hi! I am an ehcache demonstrator.");
        
        int threadCount = 10;
        int milliSleep = 5;
        Random randomWork = new Random();
        boolean getter = false;
        for (int i=0 ; i < threadCount ; i++) {
            if(0 == i%2) {
                getter = true;
            }
            else {
                getter = false;
            }
            ehcache cache = new ehcache(randomWork, getter);
            Thread t1 = new Thread(cache);
            t1.start();
            try {
                //sleep for milliseconds before spawning a new thread
                Thread.sleep(milliSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }               
    }
}
