# QuickThing

Components :

1. Broadcast Receiver : for listening to network changes over time , issued by system .
2. Timer Task : This is for checking network latency , right now it has been set to check within a period of 5 sec .
3. Event bus : Notifies our activity of the events taking place in various components of the application . Local broadcast can be used in this place also.
4. Main Activity : For displaying UI changes by listening to event changes.