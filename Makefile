local:
	@make db &
	@make api; docker rm -f kanflow_db;

deps:
	@./gradlew dependencies

api:
	@env `cat .env | grep -v ^# ` ./gradlew bootRun;

db: kanflow_db_data
	@docker rm -f kanflow_db
	@env `cat .env | grep -v ^# ` \
		sh -c 'docker run --name kanflow_db\
		-p$${KANFLOW_DB_PORT}:5432 \
		-e POSTGRES_USER=$${KANFLOW_POSTGRES_USER} \
		-e POSTGRES_PASSWORD=$${KANFLOW_POSTGRES_PASSWORD} \
		-e POSTGRES_DB=$${KANFLOW_POSTGRES_DB} \
		--volume=kanflow_db_data:/var/lib/postgresql/data \
			postgres:15.6-alpine3.19'

kanflow_db_data:
	@docker volume create kanflow_db_data

health:
	@curl http://localhost:8080/health

.PHONY: local api db kanflow_db_data status