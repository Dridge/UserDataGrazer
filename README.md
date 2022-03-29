# UserDataGrazer

## Summary

Login and then click the button to display user data
1. Tests are not testing anything atm but are
executed from the root folder using gradle, `./gradlew test`
2. Any non-empty username and password is accepted as long
as it can be authenticated by the Grazer API
3. Retrofit and Okhttp used for requests and responses with
pojo/data classes
   - https://square.github.io/okhttp/
   - https://square.github.io/retrofit/
4. GsonConverterFactory is used in order to serialize the
attributes into json with correct field names
5. Shared module is a remnant from the Multi-platform project
it contains classes that do authentication and data retrieval
6. Observer pattern is used in order to notify activities when
data is available or authentication is complete
7. Two main singletons drive the app, Authenticator and
UserDataRetriever. Both store data once the asynchronous call
responds. Observers then retrieve that data.
8. Static code analysis is performed with the diktat tool
   - diktat generally: https://analysis-dev.github.io/diktat/
   - running as jar from cli: https://github.com/analysis-dev/diktat#run-as-cli-application
   - My first time using this tool, but I got it running from my checkout dir like so:
     `../tools/ktlint -R ../tools/diktat-1.0.3.jar --disabled_rules=standard temp/UserData/shared/src/**/*.kt temp/UserData/androidApp/src/**/*.kt > output.txt`

## Design

The final design was based on keeping the ui reactive and allows
the application to avoid managing threads or coroutines. Whilst
we could override settings to run everything on the main ui thread
that is not good practice.

Through using toast messages the user remains informed but does not
need to wait for the requests to return.


## Improvements

- There are no meaningful automated tests, I'm not happy about this,
but I was eager to get the functionality of an MVP first. Along
the way some tests did help me troubleshoot but in redesign they were
removed.
- Convert date of birth from epoch seconds to locale based date
- A loading spinner to keep the user more informed
- Coroutines might provide an alternative solution for more dynamic user activities
- Add a RecycleView instead of just using the UserDataAdapter to allow a refresh
of the userdata
- I am very new to Kotlin and retrofit, so there are probably some
improvements with its usage
- This was a multi-platform project, I had hoped to get something with 
swift and ios but have not yet got round to it.

## Proud parts

The observer pattern was a design decision after reading up on coroutines and
attempting to authenticate on different threads whilst not blocking user
activities. I think it works well.

Aesthetics, there was some thought that went into keeping the app clean and 
simple in its appearance.

A large amount of my time was spent learning Kotlin which I decided
to use as a personal challenge but mainly because it best suited
the role.
