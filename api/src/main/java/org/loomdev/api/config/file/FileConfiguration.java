package org.loomdev.api.config.file;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.config.VolatileConfiguration;

import java.io.*;

public abstract class FileConfiguration extends VolatileConfiguration {

    public FileConfiguration() {
        super();
    }

    public void save(@NotNull File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(saveToString());
        writer.close();
    }

    public abstract String saveToString();

    public void load(@NotNull File file) throws IOException {
        load(new FileReader(file));
    }

    public void load(@NotNull Reader reader) throws IOException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } finally {
            input.close();
        }

        load(builder.toString());
    }

    public abstract void load(@NotNull String string);
}
