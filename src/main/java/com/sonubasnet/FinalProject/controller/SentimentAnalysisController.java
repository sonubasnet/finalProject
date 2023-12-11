package com.sonubasnet.FinalProject.controller;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.sonubasnet.FinalProject.SentitmentAnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SentimentAnalysisController {


    @PostMapping("/")
    public Mono<String> sayHello(@RequestBody String data) {
        return SentitmentAnalysisService.runAnalysis(data);
    }
}
