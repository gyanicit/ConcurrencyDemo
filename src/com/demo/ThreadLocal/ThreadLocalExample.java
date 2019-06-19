package com.demo.ThreadLocal;

/*
 * This class provides thread local variable. These variables differ from their normal counterparts in that each
 * thread that accesses one (via its get or set method) has its own, independently initialized copy of the variable.
 * 
 * 1. Basically it is an another way to achieve thread safety apart from writing immutable classes.
 * 2. Since Object is no more shared there is no requirement of Synchronization which can improve scalability and performance
 * 	  of application.
 * 3. It extends class Object.
 * 4. ThreadLocal provides thread restriction which is extension of local variable. ThreadLocal are visible only in single thread.
 *    No two thread can see each others thread local variable.
 * 5. These variable are generally private static field in classes and maintain its state inside thread.
 * */
public class ThreadLocalExample {
	public static void main(String[] args) {

		ThreadLocal<Number> gfg_local = new ThreadLocal<Number>();

		ThreadLocal<String> gfg = new ThreadLocal<String>();

		/*
		 * Setting the value ----------------------------- This method sets the current
		 * thread’s copy of this thread-local variable to the specified value. Most
		 * subclasses will have no need to override this method, relying solely on the
		 * initialValue() method to set the values of thread-locals.
		 */
		gfg_local.set(100);

		/*
		 * Returns the current thread's value ---------------------------------------
		 * This method returns the value in the current thread’s copy of this
		 * thread-local variable. If the variable has no value for the current thread,
		 * it is first initialized to the value returned by an invocation of the
		 * initialValue() method.
		 */
		System.out.println("value = " + gfg_local.get());

		// setting the value
		gfg_local.set(90);

		// returns the current thread's value of
		System.out.println("value = " + gfg_local.get());

		// setting the value
		gfg_local.set(88.45);

		// returns the current thread's value of
		System.out.println("value = " + gfg_local.get());

		// setting the value
		gfg.set("ABC");

		// returns the current thread's value of
		System.out.println("value = " + gfg.get());
	}
}
