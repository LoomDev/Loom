package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.server.Server;
import org.loomdev.loom.server.ServerImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpsCommand extends Command {

    private final Server server;

    private static final DecimalFormat FORMAT = new DecimalFormat("########0.0");

    public TpsCommand(ServerImpl server) {
        super("tps", "mspt");
        setDescription("Displays the server's current TPS and tick times.");
        setUsage("/tps");

        this.server = server;
    }

    @Override
    public void execute(@NonNull CommandSource source, String alias, String[] args) {
        Tps tps = server.getTps();
        TickTimes tickTimes = server.getTickTimes();

        List<String> times = new ArrayList<>();
        times.addAll(eval(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_5)));
        times.addAll(eval(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_10)));
        times.addAll(eval(tickTimes.getTimes(TickTimes.TickTimesInterval.SECONDS_60)));

        source.sendMessage(TextComponent.builder()
                .append(TextComponent.of("TPS from the last 1m, 5m, 15m: "))
                .append(TextComponent.of(String.format("%s§f, %s§f, %s",
                        getFormattedTps(tps, Tps.TpsInterval.MINUTES_1),
                        getFormattedTps(tps, Tps.TpsInterval.MINUTES_5),
                        getFormattedTps(tps, Tps.TpsInterval.MINUTES_15))))
                .build()
        );

        source.sendMessage(TextComponent.builder()
                .append(TextComponent.of("Tick rates: "))
                .append(TextComponent.of(String.format("%s§7/%s§7/%s§f, %s§7/%s§7/%s§f, %s§7/%s§7/%s", times.toArray()))
                        .hoverEvent(HoverEvent.showText(TextComponent.of("Tick rates from last 5s, 10s, 15s"))))
                .build()
        );
    }

    private static String getFormattedTps(Tps tps, Tps.TpsInterval interval) {
        double tpsValue = tps.getTps(interval);
        return ((tpsValue > 18.0) ? "§a" : (tpsValue > 16.0) ? "§e" : "§c") + ((tpsValue > 21.0) ? "*" : "")
                + Math.min(Math.round(tpsValue * 100.0) / 100.0, 20.0);
    }

    private static List<String> eval(long[] times) {
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
        return Arrays.asList(getColor(avgD), getColor(minD), getColor(maxD));
    }

    private static String getColor(double avg) {
        return "§" + (avg >= 50 ? "c" : avg >= 40 ? "e" : "a") + FORMAT.format(avg);
    }
}
