package org.loomdev.loom.util;

import com.google.common.collect.BoundType;
import com.google.common.collect.Streams;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import org.checkerframework.checker.guieffect.qual.SafeType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChatUtil {

    public static String toString(Component component) {
        if (component == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (Component subComponent : components(component)) {
            Style style = subComponent.getStyle();
            TextColor color = style.getColor();

            if (color != null) {
                try {
                    result.append(ChatFormatting.valueOf(color.toString().toUpperCase()));
                } catch(IllegalArgumentException ignored) {
                    result.append("§x");
                    for (char character : color.toString().substring(1).toCharArray()) {
                        result.append("§" + character);
                    }
                }
            }

            if (style.isBold()) {
                result.append(ChatFormatting.BOLD);
            }
            if (style.isItalic()) {
                result.append(ChatFormatting.ITALIC);
            }
            if (style.isUnderlined()) {
                result.append(ChatFormatting.UNDERLINE);
            }
            if (style.isStrikethrough()) {
                result.append(ChatFormatting.STRIKETHROUGH);
            }
            if (style.isObfuscated()) {
                result.append(ChatFormatting.OBFUSCATED);
            }

            subComponent.visitSelf((string) -> {
               result.append(string);
               return Optional.empty();
            });
        }

        return result.toString();
    }

    public static Stream<Component> stream(Component component) {
        return Streams.concat(Stream.of(component), component.getSiblings().stream().flatMap(ChatUtil::stream));
    }

    public static List<Component> components(Component component) {
        return stream(component).collect(Collectors.toList());
    }
}
