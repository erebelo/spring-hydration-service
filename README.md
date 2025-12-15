# Spring Hydration Service

REST API project developed in Java using Spring Boot 3 that orchestrates and executes data hydration workflows by fetching incremental datasets from Amazon Athena and processing them through a configurable, step-based pipeline.

The service manages hydration jobs and steps with full lifecycle tracking and executes them asynchronously with controlled concurrency, timeout handling, and safe cancellation. Hydration jobs can be triggered via REST endpoints or automatically through a scheduled task protected by distributed locks, ensuring reliable and scalable execution across environments.

## Requirements

- Java 21
- Spring Boot 3.x.x
- Apache Maven 3.8.6

## Libraries

- [spring-common-parent](https://github.com/erebelo/spring-common-parent): Manages the Spring Boot version and provide common configurations for plugins and formatting.

## Configuring Maven for GitHub Dependencies

To pull the `spring-common-parent` dependency, follow these steps:

1. **Generate a Personal Access Token**:

   Go to your GitHub account -> **Settings** -> **Developer settings** -> **Personal access tokens** -> **Tokens (classic)** -> **Generate new token (classic)**:

   - Fill out the **Note** field: `Pull packages`.
   - Set the scope:
     - `read:packages` (to download packages)
   - Click **Generate token**.

2. **Set Up Maven Authentication**:

   In your local Maven `settings.xml`, define the GitHub repository authentication using the following structure:

   ```xml
   <servers>
     <server>
       <id>github-spring-common-parent</id>
       <username>USERNAME</username>
       <password>TOKEN</password>
     </server>
   </servers>
   ```

   **NOTE**: Replace `USERNAME` with your GitHub username and `TOKEN` with the personal access token you just generated.

## Run App

- Complete the required [AWS Setup](#aws-setup) step.
- Set the following environment variables: `AWS_REGION`, `AWS_ACCESS_KEY_ID`, and `AWS_SECRET_ACCESS_KEY`.
- Run the `SpringHydrationServiceApplication` class as Java Application.

## Collection

[Project Collection](https://github.com/erebelo/spring-hydration-service/tree/main/collection)

## AWS Setup

[IAM, Athena and S3 Setup](https://github.com/erebelo/spring-hydration-service/blob/main/docs/aws/aws-setup.md)
