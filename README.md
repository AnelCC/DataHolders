# Data Holders 

## Live Data
LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

## The advantages of using LiveData
Using LiveData provides the following advantages:

### Ensures your UI matches your data state
LiveData follows the observer pattern. LiveData notifies Observer objects when underlying data changes. You can consolidate your code to update the UI in these Observer objects. That way, you don't need to update the UI every time the app data changes because the observer does it for you.
### No memory leaks
Observers are bound to Lifecycle objects and clean up after themselves when their associated lifecycle is destroyed.
### No crashes due to stopped activities
If the observer's lifecycle is inactive, such as in the case of an activity in the back stack, then it doesn’t receive any LiveData events.
### No more manual lifecycle handling
UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.
### Always up to date data
If a lifecycle becomes inactive, it receives the latest data upon becoming active again. For example, an activity that was in the background receives the latest data right after it returns to the foreground.
### Proper configuration changes
If an activity or fragment is recreated due to a configuration change, like device rotation, it immediately receives the latest available data.
### Sharing resources
You can extend a LiveData object using the singleton pattern to wrap system services so that they can be shared in your app. The LiveData object connects to the system service once, and then any observer that needs the resource can just watch the LiveData object. For more information, see Extend LiveData.



### References
https://developer.android.com/topic/libraries/architecture/livedata