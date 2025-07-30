#!/bin/sh

HOST="$1"
PORT="$2"
shift 2

echo "Esperando a que $HOST:$PORT esté disponible..."

while ! nc -z "$HOST" "$PORT"; do
  echo "Todavía no está disponible $HOST:$PORT, esperando..."
  sleep 2
done

echo "$HOST:$PORT está disponible, iniciando aplicación..."
exec "$@"
