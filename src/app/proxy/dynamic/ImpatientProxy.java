package app.proxy.dynamic;

import java.lang.reflect.*;

/**
 * This class is an example of a dynamic proxy. Instances of this class wrap a
 * proxied object. This class simply forwards calls to the object it wraps.
 * However, if any method takes a long time to execute, this class will print a
 * warning message.
 */

// TODO: 1/7/2024  Proxy design pattern - Dynamic Proxies
public class ImpatientProxy implements InvocationHandler {

    private Object obj;

    /**
     * Construct a dynamic proxy around the given object.
     * @param obj the object to wrap
     * @return the proxy
     */

    //The Proxy class will need
    //a list of interfaces and a class loader, as well as an instance of
    //ImpatientProxy. To simplify the creation of a dynamic proxy, we
    //might add the following method
    public static Object newInstance(Object obj) {
        ClassLoader loader = obj.getClass().getClassLoader();
        Class<?>[] classes = obj.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, classes, new ImpatientProxy(obj));
    }

    private ImpatientProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * The method that all dynamic proxies must implement. This dynamic proxy
     * complains when a method takes a long time to return.
     */
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        long t1 = System.currentTimeMillis();
        //The beauty of dynamic proxies is that you can add any behavior
        //you like before or after executing this line.
        result = m.invoke(obj, args);
        long t2 = System.currentTimeMillis();
        if (t2 - t1 > 10) {
            System.out.println("> It takes " + (t2 - t1) + " millis to invoke " + m.getName()
                    + "() with");
            for (int i = 0; i < args.length; i++) 
                System.out.println(">     arg[" + i + "]: " + args[i]);
        }
        return result;
    }
}
