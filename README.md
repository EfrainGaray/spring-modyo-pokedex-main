# Challenger Pokedex spring boot external api:
[![N|Solid](https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png)](https://pokeapi.co/)

![Build Status](https://github.com/EfrainGaray/spring-modyo-pokedex-main/actions/workflows/main.yaml/badge.svg)


## Features

- Integrar una API externa.
- Exponer un API REST.
- Consumir API desde front-end (opcional)
- Desplegar en la nube.
- Manejar errores.
- Mantener un código ordenado y de calidad.


## Demo

Demo api in spring and front end in svelte:

- [Spring Boot](https://spring.io/) - https://api.modyo.pokedex.hegga.tech/pokemon
- [Svelte](https://svelte.dev/) - https://challenge-pokedex.surge.sh/


## Docker

spring-modyo-pokedex-main is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.

```sh
git clone https://github.com/EfrainGaray/spring-modyo-pokedex-main.git
cd spring-modyo-pokedex-main
docker build -t spring-modyo-pokedex-main .
```

Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart=always --name=pokedex-challenge spring-modyo-pokedex-main
```

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
http://localhost:8000/pokemon
```

## Deploy

Deploy in aws with githubactions ecr, ecs.

workflow
```yaml
name: Main - Testing

on:
  push:
    branches:
      - 'main'
env:
  AWS_REGION: ${{ secrets.AWS_REGION }}
  ECR_REPOSITORY: ${{ secrets.ECR_REPOSITORY }}
  ECS_SERVICE: ${{ secrets.ECS_SERVICE }}
  ECS_CLUSTER: ${{ secrets.ECS_CLUSTER }}
  CONTAINER_NAME: ${{ secrets.CONTAINER_NAME }}
  
jobs:

  artifact:

    name: Test master branch - GitHub Packages
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v1
      - name: Set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11.0.4
      - name: Maven Package
        run: mvn -B clean package -DskipTests
      - name: Maven Verify
        run: mvn -B clean verify
  deploy:
    name: Begin deploy
    needs: [ artifact ]
    environment: production
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v2

      - name: Configure AWS credentials
        uses: aws-actions/configure-aws-credentials@13d241b293754004c80624b5567555c4a39ffbe3
        with:
          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          aws-region: ${{ env.AWS_REGION }}

      - name: Login to Amazon ECR
        id: login-ecr
        uses: aws-actions/amazon-ecr-login@aaf69d68aa3fb14c1d5a6be9ac61fe15b48453a2

      - name: Build, tag, and push image to Amazon ECR
        id: build-image
        env:
          ECR_REGISTRY: ${{ steps.login-ecr.outputs.registry }}
          IMAGE_TAG: ${{ github.sha }}
        run: |
          docker build -t $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG .
          docker push $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG
          echo "::set-output name=image::$ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG"
      - name: Download task definition
        run: |
          aws ecs describe-task-definition --task-definition spring-modyo-pokedex-challenge-task --query taskDefinition > task-definition.json
      - name: Fill in the new image ID in the Amazon ECS task definition
        id: task-def
        uses: aws-actions/amazon-ecs-render-task-definition@97587c9d45a4930bf0e3da8dd2feb2a463cf4a3a
        with:
          task-definition: task-definition-mock.json
          container-name: ${{ secrets.CONTAINER_NAME }}
          image: ${{ steps.build-image.outputs.image }}

      - name: Deploy Amazon ECS task definition
        uses: aws-actions/amazon-ecs-deploy-task-definition@de0132cf8cdedb79975c6d42b77eb7ea193cf28e
        with:
          task-definition: ${{ steps.task-def.outputs.task-definition }}
          service: ${{ secrets.ECS_SERVICE }}
          cluster: ${{ secrets.ECS_CLUSTER }}
          wait-for-service-stability: true
```

## License

MIT
