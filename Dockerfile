FROM openjdk:8-jre-alpine

WORKDIR /opt/io/docs-indexer

COPY build/libs/docs-indexer.jar ./
COPY runDocsIndexerDocker.sh ./

RUN chmod 711 ./runDocsIndexerDocker.sh

EXPOSE 8093

CMD ["./runDocsIndexerDocker.sh"]
