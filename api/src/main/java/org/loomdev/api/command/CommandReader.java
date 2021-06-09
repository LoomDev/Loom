package org.loomdev.api.command;

// Based on https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/ImmutableStringReader.java
/**
 * Represents a mutable command reader.
 */
public interface CommandReader {

    /**
     * Gets the command being read.
     * @return The command (as a string).
     */
    String getString();

    /**
     * Gets the number of characters after the cursor.
     * @return The number of characters.
     */
    int getRemainingLength();

    /**
     * Gets the total length of the command.
     * @return The total.
     */
    int getTotalLength();

    /**
     * Gets the position of the cursor.
     * @return The position.
     */
    int getCursor();

    /**
     * Sets the position of the cursor.
     * @param cursor The position.
     */
    void setCursor(int cursor);
    
    /**
     * Gets the string before the cursor.
     * @return The string.
     */
    String getRead();

    /**
     * Gets the string after the cursor.
     * @return The string.
     */
    String getRemaining();

    boolean canRead(int length);

    boolean canRead();

    /**
     * Takes a peek at the next character.
     * @return The character.
     */
    char peek();

    /**
     * Takes a peek at the character <code>offset</code> places after the cursor.
     * @return The character.
     */
    char peek(int offset);

    /**
     * Moves the cursor by one step, reading a single character.
     * @return The character.
     */
    char read();

    /**
     * Moves the cursor past a boolean.
     * @return The boolean.
     */
    boolean readBoolean();

    /**
     * Moves the cursor past an integer.
     * @return The integer.
     */
    int readInt();

    /**
     * Moves the cursor past a long.
     * @return The long.
     */
    long readLong();

    /**
     * Moves the cursor past a double.
     * @return The double.
     */
    double readDouble();

    /**
     * Moves the cursor past a float.
     * @return The float.
     */
    float readFloat();

    /**
     * Moves the cursor past an unquoted string.
     * @return The string.
     */
    String readUnquotedString();

    /**
     * Moves the cursor past a quoted string.
     * @return The string.
     */
    String readQuotedString();

    /**
     * Moves the cursor until <code>character</code> is reached.
     * @param character The character.
     * @return The string the cursor passed over.
     */
    String readStringUntil(char character);

    /**
     * Moves the cursor past a string.
     * @return The float.
     */
    String readString();
}