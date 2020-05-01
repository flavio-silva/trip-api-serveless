# AWS Serveless Cloud Native Java RESTFul API

## Requirements
+ Docker
+ Java 8
+ MVN CLI
+ AWS CLI
+ Sam CLI
+ GIT

## Prepare the code
1. Clone repository: `git clone https://github.com/flavio-silva/trip-api-serveless.git`
2. cd trip-api-serveless
3. Install Maven Dependencies: `mvn install`

## How to run the project locally
1. Start DynamoDB in container Docker: `docker run -p 8000:8000 -v $(pwd)/local/dynamodb:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data`
2. Create DynamoDB Table: `aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=id,AttributeType=S AttributeName=date,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=date,KeyType=RANGE --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000`
3. Start SAM locally: 
    + On Mac: `sam local start-api --env-vars src/test/resources/test_environment_mac.json`
    + On Linux: `sam local start-api --env-vars src/test/resources/test_environment_linux.json`
    + On Windows: `sam local start-api --env-vars src/test/resources/test_environment_windows.json`

## How to run the project on AWS
+ Create S3 Bucket: `aws s3api create-bucket --bucket <BUCKET_NAME> --region us-east-1`
+ Package the Code: `sam package --template-file template.yaml --output-template-file packaged.yaml --s3-bucket <BUCKET_NAME> --region=us-east-1`
+ Deploy: `sam deploy --template-file packaged.yaml --stack-name serveless-work --capabilities CAPABILITY_IAM --region us-east-1` 


### Enpoints

Local URL: <http://127.0.0.1:3000>

AWS URL: **dynamically generated**

#### Create a trip
POST /trips

``
{
 "date": "2020/05/01",
  "country": "Brazil",
  "city": "Rio de Janeiro"
}
``
#### Find by Id
GET /trips/{id}

#### Find by period
GET /trips/findByPeriod?start={date(YYYY/MM/DD)}&end={date(YYYY/MM/DD)}


## Developers Team
+ Flavio Alves Ferreira da Silva - RM 333565
+ Pedro Madi Della Coletta - RM 334109
+ Rafael Miranda de Almeida - RM 333829
+ Rodrigo de Almeida Pereira - RM 333241
+ Wellington Moreira Bastos - RM 333878
+ Milton Luiz Ribeiro Junior - RM 333949