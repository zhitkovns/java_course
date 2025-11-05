package fifth;

public class NarrowingConversion {
    public static void main(String[] args) {
        // double -> float
        double d1 = 3.14159265359;
        float f1 = (float) d1;
        System.out.println("double " + d1 + " -> float " + f1);

        // long -> int
        long l1 = 2147483648L;
        int i1 = (int) l1;
        System.out.println("long " + l1 + " -> int " + i1);

        // int -> short
        int i2 = 32768;
        short s1 = (short) i2;
        System.out.println("int " + i2 + " -> short " + s1);

        // short -> byte
        short s2 = 200;
        byte b1 = (byte) s2;
        System.out.println("short " + s2 + " -> byte " + b1);

        // float -> int
        float f2 = 123.78f;
        int i3 = (int) f2;
        System.out.println("float " + f2 + " -> int " + i3);

        // double -> byte
        double d2 = 130.56;
        byte b2 = (byte) d2;
        System.out.println("double " + d2 + " -> byte " + b2);
    }
}