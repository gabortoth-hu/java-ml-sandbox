/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gabortoth.ml.utils;

/**
 *
 * @author gtoth
 */
  public class Functions{  
      
    @FunctionalInterface
    public interface Func1<T1,R>{
        public R apply(T1 t1);
    } 
      
    @FunctionalInterface
    public interface Func2<T1,T2,R>{
        public R apply(T1 t1,T2 t2);
    }
      
    @FunctionalInterface
    public interface Func3<T1,T2,T3,R>{
        public R apply(T1 t1,T2 t2,T3 t3);
    }

    @FunctionalInterface
    public interface Func4<T1,T2,T3,T4,R>{
        public R apply(T1 t1,T2 t2,T3 t3, T4 t4);
    }

    @FunctionalInterface
    public interface Func5<T1,T2,T3,T4,T5,R>{
        public R apply(T1 t1,T2 t2,T3 t3, T4 t4, T5 t5);
    }
    
}
