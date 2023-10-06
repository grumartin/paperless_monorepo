#
# Build stage
#
FROM node:latest as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm cache clean --force
COPY . .
RUN npm install
ARG configuration=production
RUN npm run build -- --configuration $configuration

#
# Package stage
#
FROM nginx:latest
COPY --from=build /app/dist/paperless-ui /usr/share/nginx/html
EXPOSE 80
