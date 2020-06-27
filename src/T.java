/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */


abstract class A {
    public A() {
        aa();
    }

    abstract void aa();
}

class B extends A {
    public B() {
        System.out.println("build b");
    }

    @Override
    void aa() {
        System.out.println("aa func in B");
    }
}

class C extends B {
    int _a, _b, _c;

    public C(int a, int b, int c) {
        System.out.println("build C");
        _a = a;
        _b = b;
        _c = c;
        aa();
    }

    @Override
    void aa() {
        System.out.println(_a + _b + _c);
    }
}


