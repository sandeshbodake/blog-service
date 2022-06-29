### How to Persist into Postgres

### How to generate Controllers using Apifi

### How to generate Command/Query using Norm

### How to Persist to DynamoDB

### How to consume Kinesis Events

### How to raise Kinesis Events

### How to trigger from S3 events

### How to read file from S3 

### How to write file to S3 

### How to trigger from cloudwatch trigger events (cron)

### How to save Audit Log

### How to authorize request using Okta

### How to deploy to AWS Lambda

### How to deploy continuously using code-pipeline
Builds from a branch can be deployed continuously to dev & test environments using https://github.com/medlypharmacy/codepipeline-webhook-action
> :warning: This works for repositories with `-service` at the end

In the first step, the build artifact(bundle.zip) is uploaded to a common S3 bucket and then a webhook is triggered to start deployment of the uploaded bundle to dev environment.


```
- name: Upload artifact to S3
  uses: medlypharmacy/s3-artifacts-action@master
  with:
    aws_access_key_id: ${{ secrets.S3_BUILD_ARTIFACTS_ACCESS_KEY_ID}}
    aws_secret_access_key: ${{ secrets.S3_BUILD_ARTIFACTS_SECRET_ACCESS_KEY}}
    aws_s3_bucket_name: ${{ secrets.S3_BUILD_ARTIFACTS_BUCKET_NAME }}
    source_path: './bundle.zip'

- name: Deploy to dev
  uses: medlypharmacy/codepipeline-webhook-action@master
  if: github.ref == 'refs/heads/master'
  with:
    deployment_environment: 'dev' # Accepted values: 'dev', 'test'
    codepipeline_webhook_url: ${{ secrets.CODEPIPELINE_WEBHOOK_URL }}
    codepipeline_webhook_secret: ${{ secrets.CODEPIPELINE_WEBHOOK_SECRET }}
```

### How to integrate Secrets using SSM (and their rotation)

### How to build/test on Github Actions
- To enable CI for a repo using Github Actions create a workflow file in `.github/workflows` directory.

Take a look at this repo's [workflow](https://github.com/medlypharmacy/kotlin-micronaut-service-template/blob/master/.github/workflows/main.yml)

### How to run/test locally (using PG, Dynamo, Kinesis etc.)

### How to add log statements in code (and where not to log)

### How to ship logs to cloudwatch logs

### How to capture custom metrics

### How to enable XRay

### How to capture custom traces

### How to authenticate interservice API calls using Okta client auth

### How to auto generate Open API specs by annotating controllers

### How to have multiple changelog files in migrations without conflicts.

### How to automatically lint using ktlint

### How to publish test coverage to sonarcloud

### How to publish API Documentation
OpenAPI documentation is uploaded using https://github.com/medlypharmacy/s3-artifacts-action to S3 bucket
- Check out the snippet at https://github.com/medlypharmacy/s3-artifacts-action#to-generate-swagger-to-html to understand the fields

Example:
```
- name: Upload OpenAPI spec to S3
  uses: medlypharmacy/s3-artifacts-action@master
  if: github.ref == 'refs/heads/master'
  with:
    aws_access_key_id: ${{ secrets.S3_BUILD_ARTIFACTS_ACCESS_KEY_ID }}
    aws_secret_access_key: ${{ secrets.S3_BUILD_ARTIFACTS_SECRET_ACCESS_KEY }}
    aws_s3_bucket_name: ${{ secrets.MEDLY_STATIC_ASSETS_BUCKET }}
    source_path: "./docs/spec"
    destination_path: ${{ secrets.API_SPEC }}
    resource_type: "SWAGGER_TO_HTML"
```
