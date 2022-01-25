package by.academy.util;

import java.util.Base64;

public final class BlobPictureUtil {
    public static String convertByteArrayToStringUsingBase64(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    public static String getJspImageSrcUsingByteArray(byte[] array) {
        StringBuilder builder = new StringBuilder("data:image/jpg;base64,");
        builder.append(convertByteArrayToStringUsingBase64(array));
        return builder.toString();
    }
}
