package com.idcom4.compiler;

import com.idcom4.utils.Pair;

import java.util.Arrays;

public class Header {

    public enum EProperties {
        BYTE_ENCODING("byte_encoding"),
        TOTAL_SIZE("total_size");

        public final String value;

        EProperties(String value) {
            this.value = value;
        }
    }

    public interface IArgValueParser {
        void parse(Header header, String value);
    }

    public record SupportedProperties(String name, IArgValueParser parser) {}

    public static final SupportedProperties[] supportedProperties = new SupportedProperties[]{
            new SupportedProperties(
                    EProperties.BYTE_ENCODING.value,
                    (Header header, String value) -> header.byteEncoding = Integer.parseInt(value, 16)
            ),
            new SupportedProperties(
                    EProperties.TOTAL_SIZE.value,
                    (Header header, String value) -> header.totalSize = Integer.parseInt(value, 16)
            )
    };

    private int byteEncoding = 1;
    private int totalSize = 0;

    public Header() {}

    public static Pair<Header, String[]> Parse(String[] strTokens) {
        Header header = new Header();

        boolean parsingOver = false;

        int tokenIndex = 0;
        for (String property : strTokens) {
            String[] propertyKV = property.trim().split("=");

            if (propertyKV.length != 2) parsingOver = true;

            String key = propertyKV[0].trim();
            String value = propertyKV[1].trim();

            if (!key.startsWith("$"))
                parsingOver = true;

            // remove prefix
            key = key.substring(1);

            SupportedProperties supportedProperty = GetSupportedProperties(key);
            if (supportedProperty == null) parsingOver = true;

            if (parsingOver) {
                break;
            }

            supportedProperty.parser.parse(header, value);
            tokenIndex++;
        }

        return new Pair<>(header, Arrays.copyOfRange(strTokens, tokenIndex, strTokens.length));
    }

    private static SupportedProperties GetSupportedProperties(String key) {
        return Arrays.stream(supportedProperties)
                .filter(prop -> prop.name().equals(key))
                .findFirst().orElse(null);
    }

    public int GetByteEncoding() {
        return byteEncoding;
    }

    public int GetTotalSize() {
        return totalSize;
    }

}
