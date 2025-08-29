package com.idcom4.utils.scanner;

import java.util.function.Supplier;

public class Scanner<T, X extends Throwable> {

    public interface IScannable<T> {
        T getValueAt(int index);
        int getLength();
    }

    protected final Supplier<X> outOfBoundException;
    protected final IScannable<T> scannable;
    protected int length;
    protected int cursor = 0;

    public Scanner(IScannable<T> scannable, Supplier<X> outOfBoundException) {
        this.scannable = scannable;
        this.length = scannable.getLength();
        this.outOfBoundException = outOfBoundException;
    }

    public boolean isFullyConsumed() {
        return this.cursor >= this.length;
    }

    public T peek() throws X {
        return this.peek(0);
    }

    public T peek(int offset) throws X {
        int peekIndex = cursor + offset;

        if (peekIndex >= length) {
            if (this.outOfBoundException != null)
                throw this.outOfBoundException.get();
            else
                return null;
        }

        return this.scannable.getValueAt(peekIndex);
    }

    public T consume() throws X {
        if (this.isFullyConsumed()) {
            if (this.outOfBoundException != null)
                throw this.outOfBoundException.get();
            else
                return null;
        }

        T consumed = this.scannable.getValueAt(cursor);
        cursor++;

        return consumed;
    }

    public void skip() {
        this.skip(1);
    }

    public void skip(int amount) {
        cursor += amount;
    }

    public void setCursor(int newCursor) {
        this.cursor = newCursor;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getLength() {
        return this.length;
    }
}
