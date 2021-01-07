package org.loomdev.loom.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ChatToLegacyConverter {

    /**
     * Converts a text component to a legacy string
     * with colors replaced with their legacy codes
     * (§ followed by a number or letter).
     *
     * @return The legacy string.
     * @see https://minecraft.gamepedia.com/Formatting_codes
     */
    public static String toLegacy(Component component) {
        if (component == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        boolean hasFormatting = false;
        for (Component subComponent : getComponents(component)) {
            if (hasFormatting) {
                // If the previous component was formatted, clear all formatting.
                hasFormatting = false;
                result.append(ChatFormatting.RESET);
            }

            Style style = subComponent.getStyle();
            TextColor color = style.getColor();

            if (color != null) {
                // Attempt to convert the colour to legacy
                try {
                    result.append(ChatFormatting.valueOf(color.toString().toUpperCase()));
                    hasFormatting = true;
                } catch (IllegalArgumentException ignored) {
                    // Legacy does not support RGB
                }
            }

            if (style.isBold()) {
                hasFormatting = true;
                result.append(ChatFormatting.BOLD);
            }
            if (style.isItalic()) {
                hasFormatting = true;
                result.append(ChatFormatting.ITALIC);
            }
            if (style.isUnderlined()) {
                hasFormatting = true;
                result.append(ChatFormatting.UNDERLINE);
            }
            if (style.isStrikethrough()) {
                hasFormatting = true;
                result.append(ChatFormatting.STRIKETHROUGH);
            }
            if (style.isObfuscated()) {
                hasFormatting = true;
                result.append(ChatFormatting.OBFUSCATED);
            }

            subComponent.visitSelf((string) -> {
               result.append(string);
               return Optional.empty();
            });
        }

        return result.toString();
    }

    private static List<Component> getComponents(Component component) {
        List<Component> components = new ArrayList<Component>();
        getComponents(component, components);
        return components;
    }

    private static void getComponents(Component component, List<Component> out) {
        out.add(component);
        for (Component sibling : component.getSiblings()) {
            getComponents(sibling, out);
        }
    }

    private ChatToLegacyConverter() {
        throw new UnsupportedOperationException("ChatToLegacyConverter shouldn't be initialized.");
    }
}