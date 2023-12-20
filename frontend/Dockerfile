FROM nginx:latest
WORKDIR /usr/share/nginx/html
COPY ./js ./js
COPY ./css ./css
COPY ./pages ./pages
COPY ./index.html ./
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]