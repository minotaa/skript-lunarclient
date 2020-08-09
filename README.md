# skript-lunarclient
Skript Addon which interacts with the Lunar Client API 

# Tested with
### **1.7** & **1.8** Skript

# Download
[Downloads](https://github.com/xMinota/skript-lunarclient/releases/)

# Syntaxes
Player is Authenticated with Lunar Client
```vbs
%player% is ([auth[enticated] with]|on) [lunar [client]]
```
Send Player Cooldown
```vbs
send [lunar] cooldown to %player% called %string% with %itemstack% for %integer% [(milliseconds|ms)]
```
Send Player Notification (1.7 Client Only, it doesn't break the server, but it just doesn't show up on 1.8)
```vbs
send [lunar] notification to %player% with message %string% delayed by %integer% [seconds]
```
