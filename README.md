# reactive-sqs-client
[![Build Status](https://travis-ci.org/gregwhitaker/reactive-sqs-client.svg?branch=master)](https://travis-ci.org/gregwhitaker/reactive-sqs-client)  [ ![Download](https://api.bintray.com/packages/gregwhitaker/maven/reactive-sqs-client/images/download.svg) ](https://bintray.com/gregwhitaker/maven/reactive-sqs-client/_latestVersion)

An observable client for AWS [Simple Queue Service](https://aws.amazon.com/sqs/) using RxJava.

## Running the Example
The example can be run using the following gradle command:

```
$ ./gradlew run -DqueueName={name of sqs queue} -Dregion={aws region}
```

**NOTE:** The example is using the `DefaultAWSCredentialsProvider` so you can supply your AWS credentials using the `.aws/credentials` file, as system properties, or as environment variables.

## License
Copyright 2016 - 2020 Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
