package com.haoran.common.u;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author hr.han
 * @date 2019/5/12 19:03
 */

public final class U4File {
    private U4File() {}

    private static final Logger logger = LoggerFactory.getLogger(U4File.class);
    private static final String UTF8 = "UTF-8";

    /* Get File Content With List<String> --------------------------------------------------------------------------- */

    public static List<String> read2List(String filePath) {
        return read2List(filePath, UTF8);
    }

    public static List<String> read2List(String filePath, String encoding) {
        return read2List(filePath, Charset.forName(encoding));
    }

    public static List<String> read2List(String filePath, Charset charset) {
        List<String> contentList = new ArrayList<>();
        String temp;
        try (BufferedReader reader = bufferedReader(filePath, charset)) {
            while ((temp = reader.readLine()) != null) {
                contentList.add(temp);
            }
            return contentList;
        } catch (IOException e) {
            logger.error("read content failed", e);
        }

        return contentList;
    }

    /* Get File Content With String --------------------------------------------------------------------------------- */

    public static String read(String filePath) {
        return read(filePath, UTF8);
    }

    public static String read(String filePath, String encoding) {
        return read(filePath, Charset.forName(encoding));
    }

    public static String read(String filePath, Charset charset) {
        StringBuilder content = new StringBuilder();
        String temp;
        try (BufferedReader reader = bufferedReader(filePath, charset)) {
            while ((temp = reader.readLine()) != null) {
                content.append(temp).append("\r\n");
            }
            return content.toString();
        } catch (IOException e) {
            logger.error("read content failed", e);
        }

        return content.toString();
    }

    /* Write File --------------------------------------------------------------------------------------------------- */

    public static void write(String filePath, String contents) {
        write(filePath, contents, UTF8);
    }

    public static void write(String filePath, String contents, String encoding) {
        write(filePath, contents, Charset.forName(encoding));
    }

    public static void write(String filePath, String contents, Charset charset) {
        try (BufferedWriter writer = bufferedWriter(filePath, charset)) {
            writer.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String filePath, List<String> contents) {
        write(filePath, contents, "UTF-8");
    }

    public static void write(String filePath, List<String> contents, String encoding) {
        write(filePath, contents, Charset.forName(encoding));
    }

    public static void write(String filePath, List<String> contents, Charset charset) {
        try (BufferedWriter writer = bufferedWriter(filePath, charset)) {
            for (String content : contents) {
                writer.write(content +  "\r\n");
            }
        } catch (IOException e) {
            logger.error("write content failed", e);
        }
    }

    /* read2PropertiesWithoutCloseStream ---------------------------------------------------------------------------------------------- */

    public static Properties read2Properties(String filename) {
        return read2Properties(filename, UTF8);
    }

    public static Properties read2Properties(String filename, String encoding) {
        return read2Properties(new File(filename), encoding);
    }

    public static Properties read2Properties(String filename, Charset charset) {
        return read2Properties(new File(filename), charset);
    }

    public static Properties read2Properties(File file) {
        return read2Properties(file, UTF8);
    }

    public static Properties read2Properties(File file, String encoding) {
        return read2Properties(file, Charset.forName(encoding));
    }

    public static Properties read2Properties(File file, Charset charset) {
        Properties properties = new Properties();
        try (BufferedReader reader = bufferedReader(file, charset)) {
            properties.load(reader);
        } catch (IOException e) {
            logger.error("load properties failed", e);
        }
        return properties;
    }

    public static Properties read2Properties(Reader reader, boolean close) {
        return close ? read2Properties1(reader) : read2Properties0(reader);
    }

    public static Properties read2Properties(InputStream stream, boolean close) {
        return close ? read2Properties1(stream) : read2Properties0(stream);
    }

    private static Properties read2Properties0(Closeable closeable) {
        Properties properties = new Properties();
        try {
            read2Properties2(closeable, properties);
        } catch (IOException e) {
            logger.error("load properties failed", e);
        }
        return properties;
    }

    private static Properties read2Properties1(Closeable closeable) {
        Properties properties = new Properties();
        try {
            read2Properties2(closeable, properties);
        } catch (IOException e) {
            logger.error("load properties failed", e);
        } finally {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("load properties succeed but close failed", e);
            }
        }
        return properties;
    }

    private static void read2Properties2(Closeable closeable, Properties properties) throws IOException {
        if (closeable instanceof Reader) {
            properties.load((Reader) closeable);
        } else if (closeable instanceof InputStream) {
            properties.load((InputStream) closeable);
        }
    }

    /* BufferedReader ----------------------------------------------------------------------------------------------- */

    public static BufferedReader bufferedReader(String filename) throws IOException {
        return bufferedReader(filename, UTF8);
    }

    public static BufferedReader bufferedReader(String filename, String encoding) throws IOException {
        return bufferedReader(new File(filename), encoding);
    }

    public static BufferedReader bufferedReader(String filename, Charset charset) throws IOException {
        return bufferedReader(new File(filename), charset);
    }

    public static BufferedReader bufferedReader(File file) throws IOException {
        return bufferedReader(file, UTF8);
    }

    public static BufferedReader bufferedReader(File file, String encoding) throws IOException {
        return bufferedReader(file, Charset.forName(encoding));
    }

    public static BufferedReader bufferedReader(File file, Charset charset) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    /* BufferedWriter ----------------------------------------------------------------------------------------------- */

    public static BufferedWriter bufferedWriter(String filename) throws IOException {
        return bufferedWriter(filename, UTF8);
    }

    public static BufferedWriter bufferedWriter(String filename, String encoding) throws IOException {
        return bufferedWriter(new File(filename), encoding);
    }

    public static BufferedWriter bufferedWriter(String filename, Charset charset) throws IOException {
        return bufferedWriter(new File(filename), charset);
    }

    public static BufferedWriter bufferedWriter(File file) throws IOException {
        return bufferedWriter(file, UTF8);
    }

    public static BufferedWriter bufferedWriter(File file, String encoding) throws IOException {
        return bufferedWriter(file, Charset.forName(encoding));
    }

    public static BufferedWriter bufferedWriter(File file, Charset charset) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }
}
