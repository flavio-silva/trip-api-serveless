# AWS Serveless Cloud Native Java RESTFul API

## How to run the project locally

### Requirements
+ Docker
+ Java 8
+ MVN CLI
+ AWS CLI
+ Sam CLI

### Steps
+ docker run -p 8000:8000 -v $(pwd)/local/dynamodb:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data
+ aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=id,AttributeType=S AttributeName=date,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=date,KeyType=RANGE --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000
+ mvn install
+ sam local start-api --env-vars test_environment_<your-operation-system>.json

### Enpoints

#### Create a trip
POST http://127.0.0.1:3000/trips

``
{
 "date": "2019-05-01",
  "country": "Brazil",
  "city": "Rio de Janeiro"
}
``
#### Find by Id
GET http://127.0.0.1:3000/trips/{id}

#### Find by period
GET http://127.0.0.1:3000/trips/findByPeriod?start={date}&end={date}