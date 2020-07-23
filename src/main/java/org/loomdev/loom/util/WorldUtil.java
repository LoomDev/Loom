package org.loomdev.loom.util;

import java.io.*;
import java.util.UUID;

public final class WorldUtil {

    /**
     * Returns the unique ID for a given world directory
     * @param directory
     * @return
     */
    public static UUID getUUID(File directory) {
        File uuidFile = new File(directory, "uuid.dat");

        if (uuidFile.exists()) {
            try (DataInputStream stream = new DataInputStream(new FileInputStream(uuidFile))) {
                return new UUID(stream.readLong(), stream.read());
            } catch (IOException e) {
                e.printStackTrace(); // TODO logger log
            }
        }

        UUID uuid = UUID.randomUUID(); // TODO make sure there aren't duplicate world uuids
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(uuidFile))) {
            stream.writeLong(uuid.getMostSignificantBits());
            stream.writeLong(uuid.getLeastSignificantBits());
        } catch (IOException e) {
            e.printStackTrace(); // TODO logger log
        }

        return uuid;
    }

    private WorldUtil() {
        throw new UnsupportedOperationException("You should not be attempting to instantiate this class.");
    }
}
