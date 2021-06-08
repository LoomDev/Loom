package org.loomdev.api.command;

/**
 * Represents a method of reading a string argument.
 */
public enum StringType {
    /**
     * A single word.
     * Anything after will count as another argument.
     */
    SINGLE_WORD,
    /**
     * A quoted string, to allow multiple words.
     * A backslash can be used for escaping.
     * When the string is ended, more arguments may follow.
     */
    QUOTED,
    /**
     * Reads the rest of the command.
     */
    GREEDY;
}
