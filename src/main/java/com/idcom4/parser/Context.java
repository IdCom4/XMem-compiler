package com.idcom4.parser;

import com.idcom4.exceptions.IDException;

public class Context {

    public static class Address {
        private int address;

        public Address(int value) {
            this.address = value;
        }

        public void incrementAddress(int amount) {
            this.address += amount;
        }

        public int getCurrentAddress() {
            return address;
        }
    }

    private final Address addressSpace;

    private int byteEncoding = 1;
    private int totalSize = 1;

    public Context(Address addressSpace) {
        this.addressSpace = addressSpace;
    }

    public Address getAddressSpace() {
        return this.addressSpace;
    }

    public int getByteEncoding() {
        return byteEncoding;
    }

    public void setByteEncoding(int value) throws IDException {
        if (value < 1) throw new IDException("invalid byte encoding: " + value);
        this.byteEncoding = value;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int value) {
        this.totalSize = value;
    }
}
