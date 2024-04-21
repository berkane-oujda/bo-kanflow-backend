local:
	@make db &
	@make api

api:
	@./gradlew bootRun;

db: kanflow_db_data
	@env `cat .env | grep -v ^# ` \
		sh -c 'docker run --rm \
		-p$${DB_PORT}:5432 \
		-e POSTGRES_USER=$${POSTGRES_USER} \
		-e POSTGRES_PASSWORD=$${POSTGRES_PASSWORD} \
		-e POSTGRES_DB=$${POSTGRES_DB} \
		--volume=kanflow_db_data:/var/lib/postgresql/data \
			postgres:15.6-alpine3.19'

kanflow_db_data:
	@docker volume create kanflow_db_data

status:
	@curl http://localhost:8080/status

.PHONY: local api db kanflow_db_data status