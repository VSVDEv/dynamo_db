# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


### Spring boot with dynamo db example

1. run docker dynamo ( you have alreasdy installed aws cli and configured keys)
2. create table

`aws dynamodb create-table \
--table-name person \
--attribute-definitions AttributeName=id,AttributeType=S \
--key-schema AttributeName=personId,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000`


### AWS Dynamo DB

#### Install AWS CLI

https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html

check install

`aws --version`

`aws configure`

`AWS Access Key ID: "fakeMyKeyId"`

`AWS Secret Access Key: "fakeSecretAccessKey"`

`region: "us"`

#### Run docker

`docker run -p 8000:8000 amazon/dynamodb-local`

#### Run docker compose

#### Check dynamo

` aws dynamodb list-tables --endpoint-url http://localhost:8000`

###### create table

`aws dynamodb create-table \
--table-name users \
--attribute-definitions AttributeName=nickname,AttributeType=S \
--key-schema AttributeName=nickname,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000`

###### describe table

`aws dynamodb describe-table --table-name users --endpoint-url http://localhost:8000`

###### delete table

`aws dynamodb delete-table --table-name users --endpoint-url http://localhost:8000`

###### put item

`aws dynamodb put-item --table-name users --endpoint-url http://localhost:8000 --item '{"nickname": {"S": "taro"}}'
~/D/s/sam-app`

`aws dynamodb scan --table-name users --endpoint-url http://localhost:8000`

###### Composite primary key

`aws dynamodb create-table \
--table-name users \
--attribute-definitions AttributeName=nickname,AttributeType=S AttributeName=age,AttributeType=N \
--key-schema AttributeName=nickname,KeyType=HASH AttributeName=age,KeyType=RANGE \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000`

###### Filter when scanning

`aws dynamodb scan --table-name users --endpoint-url http://localhost:8000 --filter-expression 'nickname = :n' --expression-attribute-values '{":n": {"S": "taro"}}'`

`aws dynamodb scan --table-name users --endpoint-url http://localhost:8000 --filter-expression 'age > :a' --expression-attribute-values '{":a": {"N": "11"}}'`