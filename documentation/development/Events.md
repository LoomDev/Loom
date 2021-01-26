# Events
Events are things that happen on the server, such as a player joining, or disconnecting. Often, their outcome can be modified, typically allowing you to cancel them.

# Creating and registering a listener class
Listener classes are classes that contain methods to listen to events.

1. Create a new class, in this example called "PluginListeners".
2. Register the class as an event listener inside the "onEnable" method:
```java
@Hook
public void onEnable(PluginEnableHook hook) {
    Loom.getEventManager().register(this, new PluginListeners() /* Replace with listener class name */ );
}
```
3. To listen to events, create a method with the first parameter as the type of event you want to listen to:
```java
public void onPlayerJoin(PlayerEvent.Join event) {

}
```
4. Next, mark the method with the "@Subscribe" annotation:
```java
@Subscribe(
    ignoreCancelled = false,
    order = EventOrder.NORMAL
)
```
All fields are optional.

When "ignoreCancelled" is "true", the method won't be called if the event has been cancelled.

"order" controls when the method is called. Methods with earlier "order"s are called earlier, and methods with later "order"s are called later.

5. Next, add some code to the method.
In this example we will send a friendly message to the player:
```java
event.getPlayer().sendMessage(Component.text("Welcome to our server!").color(ChatColor.GOLD));
```

# Creating an event
Sometimes, it's good to create a custom event, in order to allow other plugins to interact with your plugin.

1. Create a new interface, extending types of events, such as Event itself, PlayerEvent (for an event that involves a player), BlockEvent (for an event that involves a player), Cancelable (for an event that can be cancelled) or anything else.
In this example, the event will be a cancellable player event:
```java
public interface WarpEvent extends PlayerEvent, Cancelable {

}
```
2. Next, add any methods related to the event.
In this example, the warp event contains a modifiable destination:
```java
Location getDestination();

void setDestination(Location dest);
```
3. Create an implementation, making sure to implement all methods:
```java
public class WarpEventImpl implements WarpEvent {

    private boolean canceled;
    private Location dest;

    public boolean isCanceled() {
        return canceled.
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Location getDestination() {
        return dest;
    }

    public void setDestination(Location dest) {
        this.dest = dest;
    }

}
```
4. Add a basic constructor:
```java
public WarpEventImpl(Location dest) {
    this.dest = dest;
}
```
5. Fire the event:
```java
WarpEvent event = Loom.getEventManager().fireEvent(new WarpEventImpl(dest) /* Replace with your class */ );
```
In this example, the warp event is fired when a player is teleported by the plugin:
```java
public void warp(Player player, Location dest) {
    WarpEvent event = Loom.getEventManager().fireEvent(new WarpEventImpl(dest));
    if (event.isCanceled()) return;
    player.teleport(event.getDestination());
}
```
Now, other plugins can listen to event (and, in this case change, the warp destination):
```java
@Subscribe(ignoreCanceled = true)
public void onWarp(WarpEvent event) {
    event.setCanceled(true); /* Prevent teleport */
}
```
