# AWS Serveless Cloud Native Java RESTFul API

## Requirements
+ Docker
+ Java 8
+ MVN CLI
+ AWS CLI
+ Sam CLI

## How to run the project locally
+ docker run -p 8000:8000 -v $(pwd)/local/dynamodb:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data
+ aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=id,AttributeType=S AttributeName=date,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=date,KeyType=RANGE --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000
+ mvn install
+ sam local start-api --env-vars test_environment_<your-operation-system>.json

## How to run the project on AWS
+ aws s3api create-bucket --bucket <BUCKET_NAME> --region us-east-1
+ sam package --template-file template.yaml --output-template-file packaged.yaml --s3-bucket <BUCKET_NAME> --region=us-east-1
+ sam deploy --template-file packaged.yaml --stack-name serveless-work --capabilities CAPABILITY_IAM --region us-east-1 


### Enpoints

Local URL: <http://127.0.0.1:3000>

AWS URL: **dynamically generated**

#### Create a trip
POST /trips

``
{
 "date": "2019-05-01",
  "country": "Brazil",
  "city": "Rio de Janeiro"
}
``
#### Find by Id
GET /trips/{id}

#### Find by period
GET /trips/findByPeriod?start={date}&end={date}