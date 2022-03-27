# UserDataGrazer

## Summary

Login and then click the button to display user data
1. tests are executed from the root folder using gradle, `./gradlew test`
2. any non-empty username and password is accepted as long as it can be authenticated by the Grazer API
3. retrofit and okhttp3 used for requests and responses with pojo/data classes
4. gson is used in order to serialize the attributes into json with correct field names
