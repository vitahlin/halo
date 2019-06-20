.PHONY: build up start stop down status mysql

build:
	docker-compose build

start:
	docker-compose start

stop:
	docker-compose stop

down:
	docker-compose down

up:
	docker-compose up -d
	
status:
	docker-compose ps

mysql:
	docker-compose exec mysql /bin/bash
