aws dynamodb create-table \
--table-name person \
--attribute-definitions AttributeName=id,AttributeType=S \
--key-schema AttributeName=personId,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000