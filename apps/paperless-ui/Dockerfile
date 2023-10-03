#
# Build stage
#
FROM node:alpine as build
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
ARG configuration=production
RUN npm run build -- --configuration $configuration

#
# Package stage
#
FROM nginx
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/paperless-ui/en-GB /usr/share/nginx/html
EXPOSE 80
