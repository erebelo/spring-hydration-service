package com.erebelo.springhydrationservice.config;

import com.erebelo.springhydrationservice.service.impl.AthenaServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.athena.AthenaClient;

import java.time.Duration;

@Configuration
public class AthenaConfiguration {

    @Bean
    public AthenaClient athenaClient(AwsCredentialsProvider credentialsProvider, Region region) {
        return AthenaClient
                .builder().region(region).credentialsProvider(credentialsProvider).httpClientBuilder(ApacheHttpClient
                        .builder().socketTimeout(Duration.ofSeconds(5)).connectionTimeout(Duration.ofSeconds(5)))
                .build();
    }

    @Bean("hydrationAthenaService")
    public AthenaServiceImpl hydrationAthenaService(AthenaClient athenaClient,
            @Value("${athena.hydration.database}") String athenaDatabase,
            @Value("${s3.hydration.output.bucket.url}") String outputBucketUrl,
            @Value("${athena.hydration.workgroup}") String workgroup) {
        return new AthenaServiceImpl(athenaClient, athenaDatabase, outputBucketUrl, workgroup);
    }
}
