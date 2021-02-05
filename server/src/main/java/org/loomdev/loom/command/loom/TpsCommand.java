package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.ChatColor;
import org.loomdev.loom.server.ServerImpl;

import java.text.DecimalFormat;

public class TpsCommand extends Command {

    private static final DecimalFormat FORMAT = new DecimalFormat("########0.0");
    private final ServerImpl server;

    public TpsCommand(ServerImpl server) {
        super("tps", "mspt");
        setDescription("Displays the server's current TPS and tick times.");
        setUsage("/tps");

        this.server = server;
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var tps = server.getTps();
        var tickTimes = server.getTickTimes();
        var comma = Component.text(", ");

        var ticks = Component.text("TPS from the last 1m, 5m, 15m: ")
                .append(formatTps(tps, Tps.TpsInterval.MINUTES_1))
                .append(comma)
                .append(formatTps(tps, Tps.TpsInterval.MINUTES_5))
                .append(comma)
                .append(formatTps(tps, Tps.TpsInterval.MINUTES_15));

        var ticks1 = Component.text("Tick rates: ")
                .append(formatTickRates(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_5)))
                .append(comma)
                .append(formatTickRates(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_10)))
                .append(comma)
                .append(formatTickRates(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_60)))
                .hoverEvent(HoverEvent.showText(Component.text("Tick rates from last 5s, 10s, 15s")));

        context.getSource().sendMessage(ticks.append(Component.newline()).append(ticks1));
    }

    private Component formatTps(Tps tps, Tps.TpsInterval interval) {
        var value = tps.getTps(interval);
        var color = value > 18.0 ? ChatColor.GREEN : value > 16.0 ? ChatColor.YELLOW : ChatColor.RED;
        return Component.text(Math.min(Math.round(value * 100.0) / 100.0, 20.0))
                .append((value > 21.0) ? Component.text("*") : Component.empty())
                .color(color);
    }

    private Component formatTickRates(long[] times) {
        long min = Integer.MAX_VALUE;
        long max = 0L;
        long total = 0L;

        for (long value : times) {
            if (value > 0L && value < min) min = value;
            if (value > max) max = value;
            total += value;
        }

        double avgD = ((double) total / (double) times.length) * 1.0E-6D;
        double minD = ((double) min) * 1.0E-6D;
        double maxD = ((double) max) * 1.0E-6D;

        var separator = Component.text("/").color(ChatColor.WHITE);
        return Component.text(FORMAT.format(avgD)).color(getColor(avgD))
                .append(separator)
                .append(Component.text(FORMAT.format(minD)).color(getColor(minD)))
                .append(separator)
                .append(Component.text(FORMAT.format(maxD)).color(getColor(maxD)));
    }

    private TextColor getColor(double time) {
        return time >= 50 ? ChatColor.RED : time >= 40 ? ChatColor.YELLOW : ChatColor.GREEN;
    }
}
