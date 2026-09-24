package wit.worlds;

import wit.imports.test.javaBorrowLifetime.Host;

public class JavaBorrowLifetimeImpl {
    public static void testA(Host.Item value) {
    }

    public static int testB(Host.Item value) {
        return Host.methodItemPing(value);
    }
}
