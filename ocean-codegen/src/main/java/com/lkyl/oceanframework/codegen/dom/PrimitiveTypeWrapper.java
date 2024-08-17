//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.lkyl.oceanframework.codegen.dom;

public class PrimitiveTypeWrapper extends FullyQualifiedJavaType {
    private static PrimitiveTypeWrapper booleanInstance;
    private static PrimitiveTypeWrapper byteInstance;
    private static PrimitiveTypeWrapper characterInstance;
    private static PrimitiveTypeWrapper doubleInstance;
    private static PrimitiveTypeWrapper floatInstance;
    private static PrimitiveTypeWrapper integerInstance;
    private static PrimitiveTypeWrapper longInstance;
    private static PrimitiveTypeWrapper shortInstance;
    private final String toPrimitiveMethod;

    private PrimitiveTypeWrapper(String fullyQualifiedName, String toPrimitiveMethod) {
        super(fullyQualifiedName);
        this.toPrimitiveMethod = toPrimitiveMethod;
    }

    public String getToPrimitiveMethod() {
        return this.toPrimitiveMethod;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof PrimitiveTypeWrapper)) {
            return false;
        } else {
            PrimitiveTypeWrapper other = (PrimitiveTypeWrapper)obj;
            return this.getFullyQualifiedName().equals(other.getFullyQualifiedName());
        }
    }

    public int hashCode() {
        return this.getFullyQualifiedName().hashCode();
    }

    public static PrimitiveTypeWrapper getBooleanInstance() {
        if (booleanInstance == null) {
            booleanInstance = new PrimitiveTypeWrapper("java.lang.Boolean", "booleanValue()");
        }

        return booleanInstance;
    }

    public static PrimitiveTypeWrapper getByteInstance() {
        if (byteInstance == null) {
            byteInstance = new PrimitiveTypeWrapper("java.lang.Byte", "byteValue()");
        }

        return byteInstance;
    }

    public static PrimitiveTypeWrapper getCharacterInstance() {
        if (characterInstance == null) {
            characterInstance = new PrimitiveTypeWrapper("java.lang.Character", "charValue()");
        }

        return characterInstance;
    }

    public static PrimitiveTypeWrapper getDoubleInstance() {
        if (doubleInstance == null) {
            doubleInstance = new PrimitiveTypeWrapper("java.lang.Double", "doubleValue()");
        }

        return doubleInstance;
    }

    public static PrimitiveTypeWrapper getFloatInstance() {
        if (floatInstance == null) {
            floatInstance = new PrimitiveTypeWrapper("java.lang.Float", "floatValue()");
        }

        return floatInstance;
    }

    public static PrimitiveTypeWrapper getIntegerInstance() {
        if (integerInstance == null) {
            integerInstance = new PrimitiveTypeWrapper("java.lang.Integer", "intValue()");
        }

        return integerInstance;
    }

    public static PrimitiveTypeWrapper getLongInstance() {
        if (longInstance == null) {
            longInstance = new PrimitiveTypeWrapper("java.lang.Long", "longValue()");
        }

        return longInstance;
    }

    public static PrimitiveTypeWrapper getShortInstance() {
        if (shortInstance == null) {
            shortInstance = new PrimitiveTypeWrapper("java.lang.Short", "shortValue()");
        }

        return shortInstance;
    }
}
