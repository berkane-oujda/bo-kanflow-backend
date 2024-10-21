APP_NAME=bo-kanflow-app
DOCKER_FILE_NAME=compose.yaml
ENV_FILE=.env


run:
	@echo "Running app ${APP_NAME}"
	@env `cat .env | grep -v ^# ` ./gradlew bootRun

docker-up:
	@echo "Starting docker containers with environment variables from ${ENV_FILE}"
	@docker compose --env-file ${ENV_FILE} -f ${DOCKER_FILE_NAME} up -d

docker-down:
	@echo "Stopping docker containers"
	@docker compose -f ${DOCKER_FILE_NAME} down

build:
	@echo "Building App!"
	@env `cat .env | grep -v ^# ` ./gradlew clean build

start:
	@echo "Building and starting the app"
	@make build
	@make docker-up
	@make run

.PHONY: run docker-up docker-down build start
