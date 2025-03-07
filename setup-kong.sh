#!/bin/bash

echo "Aguardando o Kong iniciar..."
sleep 10  # Aguarda alguns segundos para garantir que o Kong esteja pronto

# Criando um serviço no Kong apontando para a API Spring Boot
curl -i -X POST --url http://localhost:8001/services/ \
  --data 'name=api-trabalho' \
  --data 'url=http://springboot_api:8088'

# Criando uma rota para a API
curl -i -X POST --url http://localhost:8001/services/api-trabalho/routes \
  --data 'paths[]=/api/v1/peca' \
  --data 'name=peca-route'

# Exibe as rotas configuradas
curl -i -X GET --url http://localhost:8001/routes

echo "Configuração do Kong concluída!"
sleep 10
