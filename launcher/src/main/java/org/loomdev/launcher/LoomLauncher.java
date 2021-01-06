package org.loomdev.launcher;

import io.sigpipe.jbsdiff.Patch;

import java.lang.reflect.Method;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.jar.JarFile;

public class LoomLauncher {

    public static void main(String[] args) {
        Agent.addToClassPath(setup());

        org.loomdev.loom.Loom.main(args);
    }

    private static File setup() {
        try {
            boolean display = System.console() != null;
            MessageDigest digest = MessageDigest.getInstance("SHA-1");


            File folder = new File(System.getProperty("user.dir"));
            File cacheFolder = new File(folder, "cache");
            if (!cacheFolder.exists()) {
                cacheFolder.mkdirs();
            }

            Properties properties = new Properties();
            try (InputStream propertiesInput = LoomLauncher.class.getResourceAsStream("/launcher.properties")) {
                properties.load(propertiesInput);
            }

            byte[] patchedSha1 = parseHexBinary(properties.getProperty("patched.sha1"));
            File patchedJar = new File(cacheFolder, "patched.jar");
            byte[] patchedBytes = null;

            if (patchedJar.exists()) {
                patchedBytes = toByteArray(patchedJar);
            }

            if (patchedBytes == null || !checkSha1(digest, patchedBytes, patchedSha1)) {
                URL vanillaURL = getURL(properties.getProperty("vanilla.download"));
                byte[] vanillaSha1 = parseHexBinary(properties.getProperty("vanilla.sha1"));
                File vanillaJar = new File(cacheFolder, "vanilla.jar");
                byte[] vanillaBytes = null;

                if (vanillaJar.exists()) {
                    vanillaBytes = toByteArray(vanillaJar);
                }

                if (vanillaBytes == null || !checkSha1(digest, vanillaBytes, vanillaSha1)) {
                    System.out.println("Downloading vanilla jar...");
                    download(vanillaURL, vanillaJar, display);
                    if (!checkSha1(digest, vanillaBytes = toByteArray(vanillaJar), vanillaSha1)) {
                        System.err.println("\rInvalid jar!");
                        System.exit(1);
                    }
                }

                System.out.println("Patching vanilla jar...");
                byte[] patchBytes = toByteArray("/loom.patch");
                try (OutputStream output = Files.newOutputStream(patchedJar.toPath())) {
                    Patch.patch(vanillaBytes, patchBytes, output);
                }
                if (!checkSha1(digest, toByteArray(patchedJar), patchedSha1)) {
                    System.err.println("Invalid jar!");
                    System.exit(1);
                }
            }
            return patchedJar;
        } catch (Throwable error) {
            System.err.println("Could not launch:");
            error.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    private static URL getURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException error) {
            throw new IllegalStateException(url + " invalid", error);
        }
    }

    private static void download(URL url, File file, boolean display) throws IOException {
        byte[] buffer = new byte[8192];
        int length;
        int totalBytes = 0;
        URLConnection connection = url.openConnection();
        DecimalFormat megabytesFormat = new DecimalFormat("##0.0");
        String contentLengthStr = megabytesFormat.format(connection.getContentLength() / 1024.0 / 1024.0);
        try (
            InputStream input = connection.getInputStream();
            OutputStream output = new FileOutputStream(file)
        ) {
            while ((length = input.read(buffer, 0, buffer.length)) != -1) {
                output.write(buffer, 0, length);
                if (display) {
                    totalBytes += length;
                    int percent = (int) ((((double) totalBytes) / ((double) connection.getContentLength())) * 100);
                    int bars = percent / 5;
                    String megabytesStr = megabytesFormat.format(totalBytes / 1024.0 / 1024.0);
                    StringBuilder line = new StringBuilder();
                    line.append("[");
                    for (int i = 0; i < bars; i++) {
                        if (i == bars - 1) {
                            line.append(">");
                        } else {
                            line.append("=");
                        }
                    }
                    line.append(repeat(" ", 20 - bars));
                    line.append("] ");
                    line.append(megabytesStr + "/" + contentLengthStr + " MB (" + percent + "%)");
                    System.out.print("\r" + line);
                }
            }
        }
        if (display) {
            System.out.println();
        }
    }

    private static String repeat(String string, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(string);
        }
        return result.toString();
    }

    private static boolean checkSha1(MessageDigest digest, byte[] bytes, byte[] expectedSha1) throws IOException {
        return Arrays.equals(digest.digest(bytes), expectedSha1);
    }

    private static byte[] toByteArray(File file) throws IOException {
        try (InputStream input = Files.newInputStream(file.toPath())) {
            return toByteArray(input);
        }
    }

    private static byte[] toByteArray(String resource) throws IOException {
        try (InputStream input = LoomLauncher.class.getResourceAsStream(resource)) {
            return toByteArray(input);
        }
    }

    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int length = 0;
        byte[] buffer = new byte[8192];
        while ((length = input.read(buffer, 0, buffer.length)) != -1) {
            output.write(buffer, 0, length);
        }
        byte[] result = output.toByteArray();
        output.close();
        return result;
    }

    private static boolean checkJar(File jar) {
        try {
            new JarFile(jar).close();
            return true;
        } catch(Throwable error) {
            return false;
        }
    }

    // https://stackoverflow.com/a/140861
    private static byte[] parseHexBinary(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                 + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

}
