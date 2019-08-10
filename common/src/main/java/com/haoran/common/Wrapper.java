package com.haoran.common;

/**
 * @author hr.han
 * @date 2019/5/12 18:32
 */

public class Wrapper {
    private Wrapper() {}

    public static <E1, E2> W2<E1, E2> create(E1 e1, E2 e2) {
        return new W2<>(e1, e2);
    }

    public static <E1, E2, E3> W3<E1, E2, E3> create(E1 e1, E2 e2, E3 e3) {
        return new W3<>(e1, e2, e3);
    }

    public static <E1, E2, E3, E4> W4<E1, E2, E3, E4> create(E1 e1, E2 e2, E3 e3, E4 e4) {
        return new W4<>(e1, e2, e3, e4);
    }

    public static <E1, E2> W2<E1, E2> createW2() {
        return new W2<>();
    }

    public static <E1, E2> W2<E1, E2> createW2(E1 e1, E2 e2) {
        return create(e1, e2);
    }

    public static <E1, E2, E3> W3<E1, E2, E3> createW3() {
        return new W3<>();
    }

    public static <E1, E2, E3> W3<E1, E2, E3> createW3(E1 e1, E2 e2, E3 e3) {
        return create(e1, e2, e3);
    }

    public static <E1, E2, E3, E4> W4<E1, E2, E3, E4> createW4() {
        return new W4<>();
    }

    public static <E1, E2, E3, E4> W4<E1, E2, E3, E4> createW4(E1 e1, E2 e2, E3 e3, E4 e4) {
        return create(e1, e2, e3, e4);
    }

    public static class W4<E1, E2, E3, E4> extends W3<E1, E2, E3> {
        E4 e4;

        W4() {
            super();
        }

        W4(E1 e1, E2 e2, E3 e3, E4 e4) {
            super(e1, e2, e3);
            this.e4 = e4;
        }

        public E4 getE4() {
            return e4;
        }

        public void setE4(E4 e4) {
            this.e4 = e4;
        }

        @Override
        public String toString() {
            return "Wrapper.W4{e1=" + e1 +
                    ", e2=" + e2 +
                    ", e3=" + e3 +
                    ", e4=" + e4 +
                    "}";
        }
    }

    public static class W3<E1, E2, E3> extends W2<E1, E2> {
        E3 e3;

        W3() {
            super();
        }

        W3(E1 e1, E2 e2, E3 e3) {
            super(e1, e2);
            this.e3 = e3;
        }

        public E3 getE3() {
            return e3;
        }

        public void setE3(E3 e3) {
            this.e3 = e3;
        }

        @Override
        public String toString() {
            return "Wrapper.W3{e1=" + e1 +
                    ", e2=" + e2 +
                    ", e3=" + e3 +
                    "}";
        }
    }

    public static class W2<E1, E2> {
        E1 e1;
        E2 e2;

        W2() {
        }

        W2(E1 e1, E2 e2) {
            this.e1 = e1;
            this.e2 = e2;
        }

        public E1 getE1() {
            return e1;
        }

        public void setE1(E1 e1) {
            this.e1 = e1;
        }

        public E2 getE2() {
            return e2;
        }

        public void setE2(E2 e2) {
            this.e2 = e2;
        }

        @Override
        public String toString() {
            return "Wrapper.W2{e1=" + e1 +
                    ", e2=" + e2 +
                    "}";
        }
    }
}
