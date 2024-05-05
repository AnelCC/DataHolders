# Data Holders 
The data holders/streams are used in Kotlin/Android development to manage asynchronous data and UI state updates, but they have different behaviors and use cases.

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


## Flows
"flows" refer to Kotlin coroutine flows, a way to represent and manage asynchronous streams of data. They are essentially a type that can emit multiple values sequentially over time, unlike suspend functions which return only one value.

StateFlow and SharedFlow are Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.

### Flow (regular flow)
- Type: Cold stream
- Purpose: Represents a stream of values asynchronously emitted over time.
- Behavior: Does not emit anything until collected. Every collector gets its own independent stream.
- Use case: One-time operations, e.g., making network calls, database queries, processing data streams.
- Threading: Can be used with suspend functions, supports operators like map, filter.
- No built-in state: Does not hold or cache the last emitted value.

### StateFlow
- Type: Hot stream with state
- Purpose: Holds a single updatable data value and emits updates to collectors.
- Behavior: Always has a current value. When collected, emits the latest value immediately and subsequently any changes.
- Use case: UI state management, where you want to observe a state that can change over time and always want the latest value.
- Threading: Designed to be thread-safe.
- Similar to: LiveData but for Kotlin Coroutines.
- Key feature: Always replays the current state to new subscribers.

### SharedFlow
- Type: Hot stream, more general than StateFlow
- Purpose: Broadcasts emitted values to multiple subscribers.
- Behavior: Does not hold a single value by default but can be configured to replay a specified number of past emissions.
- Use case: Event buses, one-time events like navigation commands, showing Toasts, or any multi-subscriber broadcast.
- Configurable: Can buffer values, replay last n emissions.
- No initial value: Unlike StateFlow, SharedFlow doesn’t require an initial value.

### LiveData
- Type: Lifecycle-aware observable data holder 
- Purpose: Provides data updates to UI components, automatically managing subscriptions based on lifecycle state (e.g., only active when UI is started/resumed). 
- Behavior: Holds a single value and emits it on subscription, also emits updates. 
- Use case: Android UI data binding, reactive UI updates that respect lifecycle to avoid leaks. 
- Threading: Can post values from background threads. 
- Limitations: Works only on Android, tied to lifecycle, less flexible compared to Kotlin Flows. 
- Similar to: StateFlow but lifecycle-aware.

## Summary
| Feature                 | Flow                  | StateFlow                  | SharedFlow                     | LiveData                   |
| ----------------------- | --------------------- | -------------------------- | ------------------------------ | -------------------------- |
| Cold or Hot?            | Cold                  | Hot                        | Hot                            | Hot                        |
| Holds state?            | No                    | Yes (single current value) | Optional (configurable replay) | Yes (single current value) |
| Lifecycle-aware?        | No                    | No                         | No                             | Yes                        |
| Replays last value?     | No                    | Yes                        | Configurable (0 or more)       | Yes                        |
| Requires initial value? | No                    | Yes                        | No                             | Yes                        |
| Suitable for UI state?  | Sometimes             | Yes                        | Sometimes                      | Yes                        |
| Suitable for events?    | Sometimes             | No                         | Yes                            | Limited                    |
| Platform                | Kotlin (any platform) | Kotlin (any platform)      | Kotlin (any platform)          | Android only               |

### When to use what?
* Use **Flow** for async operations and streams where no state caching is needed (no save the state and it will refreshed if we rotate the phone).
* Use **StateFlow** for UI state or any observable state that always has a current value (store state and the flow benefit to update the UI).
* Use **SharedFlow** for events or broadcasts where you want multiple subscribers to get emissions, without holding a single current state (keep the value and be able to access the data after any time on the state).
* Use **LiveData** when you want lifecycle-aware reactive data in Android UI with minimal boilerplate (Live data do not use more is deprecating).

### Preview 🎉

<img src="https://raw.githubusercontent.com/AnelCC/DataHolders/refs/heads/main/image/uiexample.png" width="180" height="400"/>

### References
https://developer.android.com/topic/libraries/architecture/livedata
https://developer.android.com/kotlin/flow/stateflow-and-sharedflow