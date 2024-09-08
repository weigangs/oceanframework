//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.lkyl.oceanframework.codegen.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class FullyQualifiedJavaType implements Comparable<FullyQualifiedJavaType> {
    private static final String JAVA_LANG = "java.lang";
    private static FullyQualifiedJavaType intInstance = null;
    private static FullyQualifiedJavaType stringInstance = null;
    private static FullyQualifiedJavaType booleanPrimitiveInstance = null;
    private static FullyQualifiedJavaType objectInstance = null;
    private static FullyQualifiedJavaType dateInstance = null;
    private static FullyQualifiedJavaType criteriaInstance = null;
    private static FullyQualifiedJavaType generatedCriteriaInstance = null;
    private String baseShortName;
    private String baseQualifiedName;
    private boolean explicitlyImported;
    private String packageName;
    private boolean primitive;
    private boolean isArray;
    private PrimitiveTypeWrapper primitiveTypeWrapper;
    private final List<FullyQualifiedJavaType> typeArguments = new ArrayList();
    private boolean wildcardType;
    private boolean boundedWildcard;
    private boolean extendsBoundedWildcard;

    public FullyQualifiedJavaType(String fullTypeSpecification) {
        this.parse(fullTypeSpecification);
    }

    public boolean isExplicitlyImported() {
        return this.explicitlyImported;
    }

    public String getFullyQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (this.wildcardType) {
            sb.append('?');
            if (this.boundedWildcard) {
                if (this.extendsBoundedWildcard) {
                    sb.append(" extends ");
                } else {
                    sb.append(" super ");
                }

                sb.append(this.baseQualifiedName);
            }
        } else {
            sb.append(this.baseQualifiedName);
        }

        if (!this.typeArguments.isEmpty()) {
            boolean first = true;
            sb.append('<');

            FullyQualifiedJavaType fqjt;
            for(Iterator var3 = this.typeArguments.iterator(); var3.hasNext(); sb.append(fqjt.getFullyQualifiedName())) {
                fqjt = (FullyQualifiedJavaType)var3.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
            }

            sb.append('>');
        }

        return sb.toString();
    }

    public String getFullyQualifiedNameWithoutTypeParameters() {
        return this.baseQualifiedName;
    }

    public List<String> getImportList() {
        List<String> answer = new ArrayList();
        if (this.isExplicitlyImported()) {
            int index = this.baseShortName.indexOf(46);
            if (index == -1) {
                answer.add(this.calculateActualImport(this.baseQualifiedName));
            } else {
                String sb = this.packageName + '.' + this.calculateActualImport(this.baseShortName.substring(0, index));
                answer.add(sb);
            }
        }

        Iterator var4 = this.typeArguments.iterator();

        while(var4.hasNext()) {
            FullyQualifiedJavaType fqjt = (FullyQualifiedJavaType)var4.next();
            answer.addAll(fqjt.getImportList());
        }

        return answer;
    }

    private String calculateActualImport(String name) {
        String answer = name;
        if (this.isArray()) {
            int index = name.indexOf(91);
            if (index != -1) {
                answer = name.substring(0, index);
            }
        }

        return answer;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        if (this.wildcardType) {
            sb.append('?');
            if (this.boundedWildcard) {
                if (this.extendsBoundedWildcard) {
                    sb.append(" extends ");
                } else {
                    sb.append(" super ");
                }

                sb.append(this.baseShortName);
            }
        } else {
            sb.append(this.baseShortName);
        }

        if (!this.typeArguments.isEmpty()) {
            boolean first = true;
            sb.append('<');

            FullyQualifiedJavaType fqjt;
            for(Iterator var3 = this.typeArguments.iterator(); var3.hasNext(); sb.append(fqjt.getShortName())) {
                fqjt = (FullyQualifiedJavaType)var3.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
            }

            sb.append('>');
        }

        return sb.toString();
    }

    public String getShortNameWithoutTypeArguments() {
        return this.baseShortName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FullyQualifiedJavaType)) {
            return false;
        } else {
            FullyQualifiedJavaType other = (FullyQualifiedJavaType)obj;
            return this.getFullyQualifiedName().equals(other.getFullyQualifiedName());
        }
    }

    public int hashCode() {
        return this.getFullyQualifiedName().hashCode();
    }

    public String toString() {
        return this.getFullyQualifiedName();
    }

    public boolean isPrimitive() {
        return this.primitive;
    }

    public PrimitiveTypeWrapper getPrimitiveTypeWrapper() {
        return this.primitiveTypeWrapper;
    }

    public static FullyQualifiedJavaType getIntInstance() {
        if (intInstance == null) {
            intInstance = new FullyQualifiedJavaType("int");
        }

        return intInstance;
    }

    public static FullyQualifiedJavaType getNewListInstance() {
        return new FullyQualifiedJavaType("java.util.List");
    }

    public static FullyQualifiedJavaType getNewHashMapInstance() {
        return new FullyQualifiedJavaType("java.util.HashMap");
    }

    public static FullyQualifiedJavaType getNewArrayListInstance() {
        return new FullyQualifiedJavaType("java.util.ArrayList");
    }

    public static FullyQualifiedJavaType getNewIteratorInstance() {
        return new FullyQualifiedJavaType("java.util.Iterator");
    }

    public static FullyQualifiedJavaType getStringInstance() {
        if (stringInstance == null) {
            stringInstance = new FullyQualifiedJavaType("java.lang.String");
        }

        return stringInstance;
    }

    public static FullyQualifiedJavaType getBooleanPrimitiveInstance() {
        if (booleanPrimitiveInstance == null) {
            booleanPrimitiveInstance = new FullyQualifiedJavaType("boolean");
        }

        return booleanPrimitiveInstance;
    }

    public static FullyQualifiedJavaType getObjectInstance() {
        if (objectInstance == null) {
            objectInstance = new FullyQualifiedJavaType("java.lang.Object");
        }

        return objectInstance;
    }

    public static FullyQualifiedJavaType getDateInstance() {
        if (dateInstance == null) {
            dateInstance = new FullyQualifiedJavaType("java.util.Date");
        }

        return dateInstance;
    }

    public static FullyQualifiedJavaType getCriteriaInstance() {
        if (criteriaInstance == null) {
            criteriaInstance = new FullyQualifiedJavaType("Criteria");
        }

        return criteriaInstance;
    }

    public static FullyQualifiedJavaType getGeneratedCriteriaInstance() {
        if (generatedCriteriaInstance == null) {
            generatedCriteriaInstance = new FullyQualifiedJavaType("GeneratedCriteria");
        }

        return generatedCriteriaInstance;
    }

    public int compareTo(FullyQualifiedJavaType other) {
        return this.getFullyQualifiedName().compareTo(other.getFullyQualifiedName());
    }

    public void addTypeArgument(FullyQualifiedJavaType type) {
        this.typeArguments.add(type);
    }

    private void parse(String fullTypeSpecification) {
        String spec = fullTypeSpecification.trim();
        if (spec.startsWith("?")) {
            this.wildcardType = true;
            spec = spec.substring(1).trim();
            if (spec.startsWith("extends ")) {
                this.boundedWildcard = true;
                this.extendsBoundedWildcard = true;
                spec = spec.substring(8);
            } else if (spec.startsWith("super ")) {
                this.boundedWildcard = true;
                this.extendsBoundedWildcard = false;
                spec = spec.substring(6);
            } else {
                this.boundedWildcard = false;
            }

            this.parse(spec);
        } else {
            int index = fullTypeSpecification.indexOf(60);
            if (index == -1) {
                this.simpleParse(fullTypeSpecification);
            } else {
                this.simpleParse(fullTypeSpecification.substring(0, index));
                int endIndex = fullTypeSpecification.lastIndexOf(62);
                if (endIndex == -1) {
                    throw new RuntimeException("RuntimeError.22" + fullTypeSpecification);
                }

                this.genericParse(fullTypeSpecification.substring(index, endIndex + 1));
            }

            this.isArray = fullTypeSpecification.endsWith("]");
        }

    }

    private void simpleParse(String typeSpecification) {
        this.baseQualifiedName = typeSpecification.trim();
        if (this.baseQualifiedName.contains(".")) {
            this.packageName = getPackage(this.baseQualifiedName);
            this.baseShortName = this.baseQualifiedName.substring(this.packageName.length() + 1);
            int index = this.baseShortName.lastIndexOf(46);
            if (index != -1) {
                this.baseShortName = this.baseShortName.substring(index + 1);
            }

            this.explicitlyImported = !"java.lang".equals(this.packageName);
        } else {
            this.baseShortName = this.baseQualifiedName;
            this.explicitlyImported = false;
            this.packageName = "";
            switch (this.baseQualifiedName) {
                case "byte":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getByteInstance();
                    break;
                case "short":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getShortInstance();
                    break;
                case "int":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getIntegerInstance();
                    break;
                case "long":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getLongInstance();
                    break;
                case "char":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getCharacterInstance();
                    break;
                case "float":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getFloatInstance();
                    break;
                case "double":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getDoubleInstance();
                    break;
                case "boolean":
                    this.primitive = true;
                    this.primitiveTypeWrapper = PrimitiveTypeWrapper.getBooleanInstance();
                    break;
                default:
                    this.primitive = false;
                    this.primitiveTypeWrapper = null;
            }
        }

    }

    private void genericParse(String genericSpecification) {
        int lastIndex = genericSpecification.lastIndexOf(62);
        if (lastIndex == -1) {
            throw new RuntimeException("RuntimeError.22" + genericSpecification);
        } else {
            String argumentString = genericSpecification.substring(1, lastIndex);
            StringTokenizer st = new StringTokenizer(argumentString, ",<>", true);
            int openCount = 0;
            StringBuilder sb = new StringBuilder();

            String token;
            while(st.hasMoreTokens()) {
                token = st.nextToken();
                if ("<".equals(token)) {
                    sb.append(token);
                    ++openCount;
                } else if (">".equals(token)) {
                    sb.append(token);
                    --openCount;
                } else if (",".equals(token)) {
                    if (openCount == 0) {
                        this.typeArguments.add(new FullyQualifiedJavaType(sb.toString()));
                        sb.setLength(0);
                    } else {
                        sb.append(token);
                    }
                } else {
                    sb.append(token);
                }
            }

            if (openCount != 0) {
                throw new RuntimeException( "RuntimeError.22" +  genericSpecification);
            } else {
                token = sb.toString();
                if (!token.trim().isEmpty()) {
                    this.typeArguments.add(new FullyQualifiedJavaType(token));
                }

            }
        }
    }

    private static String getPackage(String baseQualifiedName) {
        int index = baseQualifiedName.lastIndexOf(46);
        return baseQualifiedName.substring(0, index);
    }

    public boolean isArray() {
        return this.isArray;
    }

    public List<FullyQualifiedJavaType> getTypeArguments() {
        return this.typeArguments;
    }
}
