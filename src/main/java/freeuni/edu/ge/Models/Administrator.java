package freeuni.edu.ge.Models;

import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Helpers.HashUsingSHA1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Administrator {
    private Hash hash = new HashUsingSHA1();
    private String id = "6000111223344";
    private String password;
    private boolean isInitialized = false;
    private Lock lock = new ReentrantLock();


    public Administrator(){
        if(!isInitialized){
            lock.lock();
            if(!isInitialized) {
                password = hash.generateHash("chemikai");
                isInitialized = true;
            }
            lock.unlock();
        }
    }


    public String getID(){
        return id;
    }

    public String getPassword(String id){
        if(getID().equals(id)) return password;
        return "";
    }
}
