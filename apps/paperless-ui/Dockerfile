#
# Build stage
#
FROM node:latest AS build
WORKDIR /dist/src/app
COPY package.json package-lock.json ./
RUN npm cache clean --force
COPY . .
RUN npm install
RUN npm run build --prod

#
# Package stage
#
FROM nginx:latest AS nginx
COPY --from=build /dist/src/app/dist/paperless-ui /usr/share/nginx/html
EXPOSE 80
