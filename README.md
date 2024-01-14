# Paperless Java Application

## Overview
![image](https://github.com/grumartin/paperless_monorepo/assets/101011406/0cc305a6-a98e-4c16-9e3a-92fe27fe9161)

This repository contains the Paperless Java Application, designed to provide an efficient, paperless solution as an alternative to the original Paperless Python version [https://github.com/paperless-ngx/paperless-ngx]. The application is built using Java and can be easily set up and run using Docker Compose.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Docker (Docker Desktop on Windows)
- Maven
- Java [18.0.2.1 or later]

## Installation and Running the Application

### Using Docker Compose

To run the Paperless Java Application using Docker Compose, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/grumartin/paperless_monorepo.git
2. Navigate to the root project directory where the docker-compose.yml file is located:
   ```bash
   cd [path to the root folder you created]
3. Start the application using Docker Compose (make sure Docker Desktop is running in the Background if on Windows):
   ```bash
   docker-compose up
4. After building and deploying, the UI will be available at:
   
   http://localhost:4200/en-GB/dashboard
6. To stop the application and remove the containers, use:
   ```bash
   docker-compose down

## Troubleshooting

### 1. Docker Container Fails to Start
- **Issue:** The application container doesn't start or stops immediately after starting.
- **Solution:** Check the Docker container logs using `docker logs [container_id]`. This often provides insights into what went wrong, such as missing environment variables, incorrect configurations, or issues with dependencies.

### 2. Port Conflicts
- **Issue:** Error messages indicating that the Docker container ports are already in use.
- **Solution:** Ensure no other services are running on the same ports. You can change the port in the Docker Compose file or stop the conflicting service.

### 3. Trouble Accessing the Paperless UI
- **Issue:** Can't access Paperless UI
- **Solution:** Check the Browser-Log Error Messages. If they aren't providing any useful error messages, try clearing the browser's chache or accessing the site via a different browser. If the error still persists, try running the following command:  
  ```bash
  docker builder prune
  ```
  This removes any Docker build caches.
