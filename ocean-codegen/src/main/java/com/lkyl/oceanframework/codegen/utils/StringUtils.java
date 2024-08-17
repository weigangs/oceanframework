package com.lkyl.oceanframework.codegen.utils;

public class StringUtils {

    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;

        for(int i = 0; i < inputString.length(); ++i) {
            char c = inputString.charAt(i);
            switch (c) {
                case ' ':
                case '#':
                case '$':
                case '&':
                case '-':
                case '/':
                case '@':
                case '_':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;
                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }
}
