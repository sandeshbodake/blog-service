# sample-service
Lambda function which acts as an Service for various frontend app as well as consumes events from kinesis stream to store entities releated to patient.

## Project Structure

It is a multi-module gradle project. Top Level project does not contain any sources. There are two application modules:

1. api : Exposes Api For the Client to Interact with Various patient.
2. event : Listens to kinesis streams and stores data in Postgres database

## Local setup:
Required tools:
1. Postgres server
2. Gradle
3. sam cli

## Ktlint Commands:

Following Commands are related to Ktlint

1. Configure IntelliJ IDEA - `./gradlew ktlintApplyToIdea`
2. Configure Pre-commit Hook for Format - `./gradlew addKtlintFormatGitPreCommitHook`
3. Format Kotlin Code - `./gradlew ktlintFormat`
4. Check Kotlin Lint - `./gradlew ktlintCheck`

### A. Runnling API locally:

Can be run in two modes:

1. As continuously running server:

    a. ` ./gradlew runApi `
    
    b. Hit using following endpoint `http://localhost:8080/api`
    
    c. Running Server with File watcher - `./gradlew :api:run --continuous`
    
2. As lambda (using sam cli):

    a. Generate zip using `./gradlew shadowJar`
    
    b. Run sam-cli command `sam local start-api -t sam.yaml`
    
    c. Hit using following endpoint `http://localhost:3000/api`
    
### B. Triggering event:

Invoke lambda with test json
```
echo '{ "records": [{ "kinesis": { "kinesisSchemaVersion": "1.0", "partitionKey": "1", "sequenceNumber": "49590338271490256608559692538361571095921575989136588898", "data": "eyJ0eXBlIjoidXJuOnBzLmV2ZW50Om1tczpwYXRpZW50OmNyZWF0ZWQiLCAicGF5bG9hZCI6IHsibW1zSWQiOiA2MjU1LCJmaXJzdE5hbWUiOiAiRHJlYW1hIiwibGFzdE5hbWUiOiAiTydSZWlsbHkiLCJkb2IiOiAiMTk4OC0xMS0wNSIsICJwaGFybWFjeU5waSI6ICIxMzUyMTU0MzU4In19", "approximateArrivalTimestamp": "2020-01-01" }, "eventSource": "aws:kinesis", "eventVersion": "1.0", "eventID": "shardId-000000000006:49590338271490256608559692538361571095921575989136588898", "eventName": "get-prescription", "invokeIdentityArn": "arn:aws:iam::123456789012:role/lambda-role", "awsRegion": "us-east-2", "eventSourceARN": "arn:aws:kinesis:us-east-2:123456789012:stream/lambda-stream"}]}' | ./gradlew runEvent
```
