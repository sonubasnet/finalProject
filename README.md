# Sentimental Analysis 

## Overview

The Sentiment Analyzer API is a powerful tool designed to analyze the sentiment of textual statements, providing valuable insights into the emotional tone conveyed. Leveraging Azure's Sentiment Analysis API, this project enables users to assess whether a given statement is positive, negative, or neutral, along with a corresponding sentiment score. This tool streamlines the use of the API by eliminating the need for signing up or logging in on the Azure website, enhancing overall efficiency. The API on AZURE can be found here https://language.cognitive.azure.com/tryout/sentiment 

### Key Features:

1. **Sentiment Analysis:** Utilize Azure's Sentiment Analysis API to evaluate the sentiment of user-provided statements.
   
2. **Real-time Feedback:** Receive instant feedback on the emotional tone of the input, aiding in understanding user sentiment. Rapid feedback is essential for timely response to customer sentiments in dynamic environments.

3. **Local Deployment:** The project allows for local deployment, facilitating testing and development on individual machines.

4. **User-Friendly Interface:** Interact with the API effortlessly using Postman or similar tools, making it accessible to both technical and non-technical users.

### Use Cases:

- **Business:** Gain insights into customer sentiment for products, services, or marketing campaigns. For example, understand how a new product launch is perceived.

- **Architects:** Integrate sentiment analysis into larger systems for enhanced user experience and personalized interactions. Improve user satisfaction through adaptive systems.

- **Developers:** Easily incorporate sentiment analysis functionality into applications and services, contributing to a more informed decision-making process.


## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
  - [Example API Request](#example-api-request)
  - [Example API Response](#example-api-response)
- [Testing](#testing)
- [Built With](#built-with)

## Prerequisites

Some prerequisties before using the API: 

- Java 17 installed on the local machine 
- IntelliJ IDE or any other Java IDE
- Postman for testing API endpoints


## Getting Started

** First get your LANGUAGE_KEY and LANGUAGE_ENDPOINT 
Make sure Key and Endpoint are set to the enviroment variables with variable names as LANGUAGE_KEY and LANGUAGE_ENDPOINT

```bash
# Clone the repository
git clone git@github.com:sonubasnet/finalProject.git

# Navigate to the project directory
cd your-repo

# Build the project
./gradlew build

# Run the project
./gradlew run
```

## Usage

Explain how users can interact with your project. Provide examples of API requests using tools like Postman. Include information about endpoints, request payloads, and expected responses.

### Example API Request

```http
POST http://localhost:8080/
Content-Type: application/json

{
  "statement": "This is a positive statement."
}
```

### Example API Response


```json
{
  "Sentence sentiment": "positive",
  "positive score": 0.960000,
  "neutral score": 0.030000,
  "negative score": 0.010000
}
```
<img width="1038" alt="Screen Shot 2023-12-10 at 11 16 58 PM" src="https://github.com/sonubasnet/finalProject/assets/54035984/845d8cfd-259b-454b-a700-ad737dcd18e4">

## Testing

1. Open Postman.
2. Set the request type to POST.
3. Enter the API endpoint: `64.225.30.152:8080/`.
4. In the body tab, select `raw`.
5. Enter your desired statement and press send.
   
Image of what the postman request should look like
<img width="1037" alt="Screen Shot 2023-12-10 at 11 26 38 PM" src="https://github.com/sonubasnet/finalProject/assets/54035984/f8760cc9-f1cf-4950-bd78-cd15d4ab032d">


## Built With

- Java 17
- IntelliJ IDEA
- Digital Ocean
- Azure Sentiment Analysis API
- Postman 

