#!/bin/bash

echo "Aguardando o Kong iniciar..."
sleep 10  # Aguarda alguns segundos para garantir que o Kong esteja pronto

# Verificar se o serviço já existe
if curl -s http://localhost:8001/services/api-trabalho | grep -q '"id"'; then
  echo "Serviço já existe, pulando criação..."
else
  echo "Criando serviço no Kong..."
  curl -i -X POST --url http://localhost:8001/services/ \
    --data 'name=api-trabalho' \
    --data 'url=http://springboot_api:8088'
fi

# Verificar se a rota já existe
if curl -s http://localhost:8001/routes | grep -q '"name":"peca-route"'; then
  echo "Rota já existe, pulando criação..."
else
  echo "Criando rota no Kong..."
  curl -i -X POST --url http://localhost:8001/services/api-trabalho/routes \
    --data 'paths[]=/api/v1/peca' \
    --data 'name=peca-route'
fi

# Exibir as rotas configuradas
curl -i -X GET --url http://localhost:8001/routes

echo "Configuração do Kong concluída!"
sleep 10
