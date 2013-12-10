package com.mgalala.noteall.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by galala on 11/27/13.
 */
public class FilePathUtil {

    public static final String CHARSET_NAME = "ISO-8859-1";

    public static String encodeFilePath(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            return URLEncoder.encode(filePath, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decodeFilePath(String filePath) {
        if (filePath == null) {
            return null;
        }

        try {
            return URLDecoder.decode(filePath, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
