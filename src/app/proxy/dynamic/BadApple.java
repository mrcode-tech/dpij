package app.proxy.dynamic;

/**
 * An example of a class with a method that takes too long to execute.
 */
// TODO: 1/7/2024  Proxy design pattern - Dynamic Proxies
public class BadApple {
    public String name;

    /**
     * Create a "bad apple" with the given name.
     * @param name the name of the BadApple
     */
    public BadApple(String name) {
        this.name = name;
    }

    /**
     * This is here just to show a class that implements behaviors for
     * participation in sets.
     */
    public boolean equals(Object o) {
        if (!(o instanceof BadApple))
            return false;
        BadApple f = (BadApple) o;
        return name.equals(f.name);
    }

    /**
     * This is the "bad" code. It takes a nap so the routine takes artificially
     * long to run.
     */
    public int hashCode() {
        try {
            // fialed sample
            Thread.sleep(1200);
        } catch (InterruptedException ignored) {
        }
        return name.hashCode();
    }

    /**
     * Provide a string representation of this object.
     */
    public String toString() {
        return name;
    }
}
