This project was made for a take home technical test.

Things to note:
- In a real world project, I would not store the password, personally identifiable information or payment details in plain text
- For simplicity, the database used is in memory and will be cleared on program exit, no state is saved between runs.
- This project targets Java 8 as I don't know which version of Java will be installed on the PC this will be run on.

For blocked IINS run:
```
BLOCKED_IINS=123456,987654 ./gradlew clean bootRun
```
Or without blocked IINS run:
```
./gradlew clean bootRun
```

To run tests run:
```
./gradlew clean build
```

Example request
```
curl --request POST \
  --url http://127.0.0.1:8080/register \
  --header 'content-type: application/json' \
  --data '{
"username": "BobFrench",
"password": "Password1",
"dob": "1980-02-21",
"paymentCardNumber": "349293081054422"
}'
```