# Proyecto

## Instalación y Ejecución

```sh
mvn clean install
mvn spring-boot:run
```

## Endpoints

- **Usuarios**: [GET] `http://localhost:8000/api/users`
- **Salud del sistema**: [GET] `http://localhost:9100/api/health`
- **Prueba**: [GET] `http://localhost:9100/api/test`

## Levantar con Docker

```sh
docker-compose up --build
docker-compose up -d
```

## Acceso a RabbitMQ

- URL: [http://localhost:15672](http://localhost:15672)
- Usuario: `guest`
- Contraseña: `guest`

## API Usuarios

### Crear Usuario

```sh
curl -X POST http://localhost:9100/api/users \
-H "Content-Type: application/json" \
-d '{
  "name": "Juan Perez",
  "email": "jfarfan@tecsup.edu.pe",
  "phone": "123456789"
}'
```

## API Pedidos

### Crear Pedido

```sh
curl -X POST http://localhost:9100/api/orders \
-H "Content-Type: application/json" \
-d '{
  "id": 2,
  "idClient": 2,
  "date": "2025-03-29T00:00:00.000+00:00",
  "ruc": "123456789",
  "address": "Calle Ind. Mz9, Pedregal"
}'
```

### Enviar Orden por Correo

```sh
curl -X POST http://localhost:9100/api/orders/send/{idOrder}
```

