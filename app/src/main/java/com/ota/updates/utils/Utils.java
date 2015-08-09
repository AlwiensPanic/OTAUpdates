package com.ota.updates.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

public class Utils {
    public final static String TAG = "Utils";

    private static final int KILOBYTE = 1024;
    private static int KB = KILOBYTE;
    private static int MB = KB * KB;
    private static int GB = MB * KB;
    private static DecimalFormat decimalFormat = new DecimalFormat("##0.#");

    static {
        decimalFormat.setMaximumIntegerDigits(3);
        decimalFormat.setMaximumFractionDigits(1);
    }

    public static String getFileContents(final File file) throws IOException {
        final InputStream inputStream = new FileInputStream(file);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final StringBuilder stringBuilder = new StringBuilder();

        boolean done = false;

        while (!done) {
            final String line = reader.readLine();
            done = (line == null);

            if (line != null) {
                stringBuilder.append(line);
            }
        }
        reader.close();
        inputStream.close();

        return stringBuilder.toString();
    }

    public static String formatDataFromBytes(long size) {

        String symbol;
        KB = KILOBYTE;
        symbol = "B";
        if (size < KB) {
            return decimalFormat.format(size) + symbol;
        } else if (size < MB) {
            return decimalFormat.format(size / (float) KB) + 'k' + symbol;
        } else if (size < GB) {
            return decimalFormat.format(size / (float) MB) + 'M' + symbol;
        }
        return decimalFormat.format(size / (float) GB) + 'G' + symbol;
    }

    public static String getUrlHost(String url) {
        URI uri;
        String result = null;
        try {
            uri = new URI(url);
            String domain = uri.getHost();
            result = domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
}
