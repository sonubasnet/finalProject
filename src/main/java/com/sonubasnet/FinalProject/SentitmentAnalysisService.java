package com.sonubasnet.FinalProject;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class SentitmentAnalysisService {

    private static String languageKey = System.getenv("LANGUAGE_KEY");
    private static String languageEndpoint = System.getenv("LANGUAGE_ENDPOINT");

    private static TextAnalyticsClient client = null;

    public static Mono<String> runAnalysis(String data) {
        authenticateClient(languageKey, languageEndpoint);
        return sentimentAnalysisWithOpinionMiningExample(client, data);
    }

    // Method to authenticate the client object with your key and endpoint.
    static TextAnalyticsClient authenticateClient(String key, String endpoint) {
        if (client == null) {
            client = new TextAnalyticsClientBuilder()
                    .credential(new AzureKeyCredential(key))
                    .endpoint(endpoint)
                    .buildClient();
        }
        return client;
    }
    // Example method for detecting sentiment and opinions in text.
    static Mono<String> sentimentAnalysisWithOpinionMiningExample(TextAnalyticsClient client, String data) {
        // The document that needs be analyzed.
        String document = data;

        System.out.printf("Document = %s%n", document);

        AnalyzeSentimentOptions options = new AnalyzeSentimentOptions().setIncludeOpinionMining(true);
        final Mono<DocumentSentiment> documentSentimentMono = Mono.fromCallable(() -> client.analyzeSentiment(document, "en", options)).subscribeOn(Schedulers.boundedElastic());
        return documentSentimentMono.map(documentSentiment -> {


            SentimentConfidenceScores scores = documentSentiment.getConfidenceScores();

            String output = "";

            output += "Below is the sentiment analysis of your statement(s):\n";
            output += String.format("Recognized document sentiment: %s, positive score: %f, neutral score: %f, negative score: %f.%n\n",
                    documentSentiment.getSentiment(), scores.getPositive(), scores.getNeutral(), scores.getNegative());


            for (var sentenceSentiment : documentSentiment.getSentences()) {

                SentimentConfidenceScores sentenceScores = sentenceSentiment.getConfidenceScores();
                output += String.format("\tSentence sentiment: %s, positive score: %f, neutral score: %f, negative score: %f.%n\n",
                        sentenceSentiment.getSentiment(), sentenceScores.getPositive(), sentenceScores.getNeutral(), sentenceScores.getNegative());
                for (var opinion : sentenceSentiment.getOpinions()) {
                    TargetSentiment targetSentiment = opinion.getTarget();
                    output += String.format("\t\tTarget sentiment: %s, target text: %s%n\n", targetSentiment.getSentiment(),
                            targetSentiment.getText());
                    for (AssessmentSentiment assessmentSentiment : opinion.getAssessments()) {
                        output += String.format("\t\t\t'%s' assessment sentiment because of \"%s\". Is the assessment negated: %s.%n\n",
                                assessmentSentiment.getSentiment(), assessmentSentiment.getText(), assessmentSentiment.isNegated());
                    }
                }
            }
            return output;
        });
    }
}
