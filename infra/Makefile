run-migrations:
	./applymigrations.sh

serverless-install:
	npm install --silent --progress=false -g npm
	npm install --silent --progress=false -g serverless
	npm install --silent --progress=false -g serverless-plugin-aws-alerts

serverless-deploy:
	serverless --host=ci --stage=${STAGE} deploy --alias provisioned

migrate-and-deploy: run-migrations serverless-install serverless-deploy

.DEFAULT_GOAL := migrate-and-deploy
