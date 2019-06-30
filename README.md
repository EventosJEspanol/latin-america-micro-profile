# Java language script


### Run Locally with Docker-compose

#### Start fresh environment

`docker-compose -f docker-compose.yml up -d`

#### Stop and Remove

`docker-compose -f docker-compose.yml down`

####  List services
`docker-compose -f docker-compose.yml ps`


 docker exec -it --user=solr java-languages_sorl_1 bin/solr create_core -c people
