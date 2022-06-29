#!/usr/bin/env bash

PASECRETS=$(aws secretsmanager get-secret-value --secret-id postgres/sample-service | python3 -c "import sys, json; print(json.load(sys.stdin)['SecretString'])")
DATABASE_HOST=$(echo $PASECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['host'])")
DATABASE_PORT=$(echo $PASECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['port'])")
DATABASE_NAME=$(echo $PASECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['db'])")
DATABASE_USERNAME=$(echo $PASECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['username'])")
DATABASE_PASSWORD=$(echo $PASECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['password'])")

DATABASE_URL="jdbc:postgresql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}"

./sample-service-db-migrate/bin/sample-service-db-migrate --driver=org.postgresql.Driver --url=${DATABASE_URL} --username=${DATABASE_USERNAME} --password=${DATABASE_PASSWORD} --changeLogFile=changelog.xml update
