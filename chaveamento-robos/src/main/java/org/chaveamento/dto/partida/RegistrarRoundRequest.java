package org.chaveamento.dto.partida;

public record RegistrarRoundRequest(
        String vencedorRound, // "TIME_A" ou "TIME_B" (ou "A", "B")
        Long timeVencedorId,  // Opcional: ID do time vencedor
        Integer vitoriasParaGanhar, // Ex: 2 (para melhor de 3). Padrão se null: 2
        Integer maxRounds,          // Ex: 3 rounds máximos
        Boolean finalizarComDuasSeguidas // Se true, duas vitórias seguidas já encerram
) {
}
