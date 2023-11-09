package fr.igorbanque.updateservice.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TransactionDeserializer extends JsonDeserializer<Transaction> {

    @Override
    public Transaction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String clientFirstName = node.get("clientFirstName").asText();
        String clientLastName = node.get("clientLastName").asText();
        int amountOfTransaction = node.get("amountOfTransaction").asInt();
        String clientCreditCartNumber = node.get("clientCreditCartNumber").asText();
        String clientCreditCartDateExpiration = node.get("clientCreditCartDateExpiration").asText();
        String clientCVV = node.get("clientCVV").asText();

        return new Transaction(
                clientFirstName,
                clientLastName,
                amountOfTransaction,
                clientCreditCartNumber,
                clientCreditCartDateExpiration,
                clientCVV
        );
    }
}
