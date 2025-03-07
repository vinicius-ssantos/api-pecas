#!/bin/bash

# Aguarda o Kong inicializar
echo "Aguardando o Kong iniciar..."
sleep 10

# Cria um serviço no Kong apontando para a API
curl -i -X POST --url http://localhost:8001/services/ \
  --data 'name=api-trabalho' \
  --data 'url=http://api:8088'

# Cria uma rota para a API
curl -i -X POST --url http://localhost:8001/services/api-trabalho/routes \
  --data 'paths[]=/api/v1/peca' \
  --data 'name=peca-route'

# Exibe as rotas configuradas
curl -i -X GET --url http://localhost:8001/routes
