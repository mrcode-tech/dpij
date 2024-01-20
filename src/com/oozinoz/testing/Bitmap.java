package com.oozinoz.testing;
//Note that a method’s signature does not include its return type,
//although if a method declaration overrides the declaration of another
//method, a compile-time error occurs if they have different return types

//Exception in clone method:
//For pre–Java 5 versions: If you were to somehow change the return
//value of Bitmap.clone(), the code wouldn’t compile. The clone()
//signature matches the signature of Object.clone(), so the return
//type must match as well.
//In Java 5: The language definition has changed to allow covariant
//return types, whereby a subclass can declare a more specific return type.

//starting from Java 5, you can declare the return type of the clone() method as a more specific type (such as Bitmap) in the subclass,
// and it would still compile. This is an exception to the usual rule of covariant return types.
class Bitmap implements Cloneable {
    int i = 15;

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        // Your cloning logic...
//        return super.clone();
//    }
    @Override
    protected Bitmap clone() throws CloneNotSupportedException {
        // Your cloning logic...
        return (Bitmap) super.clone();
    }

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap();
        Bitmap cloneBitmap;
        try {
            cloneBitmap = bitmap.clone();
            System.out.println(bitmap.i);
            System.out.println(cloneBitmap.i);
        }catch (Exception e) {
        }
    }
}