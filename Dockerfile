FROM 200.0.1.100:5000/node 

# set the work directory
WORKDIR /app

# RUN: executes the command(s) while building the image
# install npm
# RUN apt-get update
# RUN apt-get install npm
# RUN apt-get update && apt-get install npm nodejs -y

# copy all the files including node_modules directory
COPY . .

# install npm
RUN npm install

# EXPOSE PORT 4000
EXPOSE 4000

# RUN: executes the command(s) while starting the container
# start the express server
CMD node server.js