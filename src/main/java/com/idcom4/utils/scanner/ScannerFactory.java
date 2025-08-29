package com.idcom4.utils.scanner;

import java.util.function.Supplier;

public class ScannerFactory {

    public static <X extends Throwable> StringScanner<X> fromString(String str, Supplier<X> indexOutOfBoundException) {
        return new StringScanner<>(str, indexOutOfBoundException);
    }
}
