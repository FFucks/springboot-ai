package com.ffucks.bookstore.services;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private static final int MAX_REVIEW_LENGTH = 500;

    private final ChatClient chatClient;

    public ReviewService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateReview(String title) {
        try {
            var prompt = """
                    Escreva um resumo objetivo e direto do livro "%s".
                    Responda em português.
                    Não inclua opiniões nem julgamentos sobre a obra.
                    Não repita o título do livro na resposta.
                    Use no máximo 500 caracteres.
                    """.formatted(title);

            var review = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            if (review == null) {
                return null;
            }

            if (review.length() > MAX_REVIEW_LENGTH) {
                return review.substring(0, MAX_REVIEW_LENGTH);
            }

            return review;
        } catch (Exception e) {
            System.out.println("Erro ao gerar o review do livro. Esse erro precisa ser tratado: " + e.getMessage());
            // Aqui caberia tratamento adequado: envio para fila de erro, retentativa ou circuit breaker.
            return null;
        }
    }
}
