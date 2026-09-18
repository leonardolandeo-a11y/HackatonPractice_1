package com.example.hackatonpractice_1.service;

import com.example.hackatonpractice_1.dto.AiClassificationDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GitHubModelsService {

    private static final Set<String> VALID_SIGNAL_TYPES = Set.of(
            "HAMBRE",
            "ABANDONO",
            "MUTACION",
            "FUGA",
            "CONFLICTO",
            "REPRODUCCION_MASIVA",
            "SENAL_CORRUPTA"
    );

    private static final Set<String> VALID_SEVERITIES = Set.of(
            "LEVE",
            "MODERADO",
            "GRAVE",
            "CRITICO"
    );

    private static final Map<String, String> EXPECTED_UNITS = Map.of(
            "HAMBRE", "Laboratorio de Nutricion",
            "ABANDONO", "Unidad de Bienestar",
            "MUTACION", "Division Genetica",
            "FUGA", "Equipo de Contencion",
            "CONFLICTO", "Consejo de Mediacion",
            "REPRODUCCION_MASIVA", "Control Demografico",
            "SENAL_CORRUPTA", "Archivo de Senales"
    );

    private static final String SYSTEM_PROMPT = """
            Eres el sistema de clasificación de señales del TropelCare Signal Engine, desarrollado por Tuckersoft.
            Recibes señales emitidas por criaturas digitales llamadas Tropeles y debes clasificarlas.
            Responde ÚNICAMENTE con este JSON en una sola línea, sin texto adicional, sin markdown, sin bloques de código:
            {"signalType":"<TIPO>","severity":"<GRAVEDAD>","assignedUnit":"<UNIDAD>","recommendedAction":"<acción breve y concreta en español>"}

            Tipos válidos: HAMBRE, ABANDONO, MUTACION, FUGA, CONFLICTO, REPRODUCCION_MASIVA, SENAL_CORRUPTA
            Gravedades válidas: LEVE, MODERADO, GRAVE, CRITICO
            Unidades válidas: Laboratorio de Nutricion, Unidad de Bienestar, Division Genetica, Equipo de Contencion, Consejo de Mediacion, Control Demografico, Archivo de Senales

            Reglas:
            - HAMBRE → Laboratorio de Nutricion: escasez de nutrientes, intento de morder objetos digitales.
            - ABANDONO → Unidad de Bienestar: angustia por falta de interacción, silencio prolongado.
            - MUTACION → Division Genetica: cambios físicos, brillo anómalo, glitch corporal, duplicación.
            - FUGA → Equipo de Contencion: intento de abandonar el sector, zonas prohibidas.
            - CONFLICTO → Consejo de Mediacion: pelea entre Tropeles, invasión de territorio.
            - REPRODUCCION_MASIVA → Control Demografico: reproducción no planificada, clonación accidental.
            - SENAL_CORRUPTA → Archivo de Senales: señal ininteligible, estática, datos corruptos.

            Gravedades:
            - LEVE: sin riesgo inmediato para el Tropel o el sector.
            - MODERADO: requiere atención, pero no es urgente.
            - GRAVE: afecta al Tropel o al sector de forma importante.
            - CRITICO: riesgo de mutación irreversible, fuga masiva o colapso del sector.
            """;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final String modelId;

    public GitHubModelsService(
            RestClient.Builder restClientBuilder,
            ObjectMapper objectMapper,
            @Value("${github.token}") String githubToken,
            @Value("${github.models.url}") String githubModelsUrl,
            @Value("${github.models.model-id}") String modelId
    ) {
        this.restClient = restClientBuilder.baseUrl(githubModelsUrl).defaultHeader("Authorization", "Bearer " + githubToken).build();
        this.objectMapper = objectMapper;
        this.modelId = modelId;
    }

    public AiClassificationDTO classify(String rawContent) {

        try {
            Map<String, Object> requestBody = Map.of("model", modelId, "messages", List.of(
                            Map.of("role", "system", "content", SYSTEM_PROMPT),
                            Map.of("role", "user", "content", rawContent)
                    )
            );

            String response = restClient.post().uri("/chat/completions").contentType(MediaType.APPLICATION_JSON).body(requestBody).retrieve().body(String.class);

            return parseResponse(response);

        } catch (Exception ex) {
            throw new IllegalStateException("Error al clasificar la señal con GitHub Models", ex);
        }
    }

    private AiClassificationDTO parseResponse(String response) throws Exception {

        if (response == null || response.isBlank()) {
            throw new IllegalArgumentException("GitHub Models devolvió una respuesta vacía");
        }

        JsonNode root = objectMapper.readTree(response);

        JsonNode contentNode = root.path("choices").path(0).path("message").path("content");

        if (contentNode.isMissingNode() || contentNode.isNull()) {
            throw new IllegalArgumentException("La respuesta de GitHub Models no contiene choices[0].message.content");
        }

        String content = contentNode.asText();

        String json = extractJson(content);

        JsonNode classification = objectMapper.readTree(json);

        String signalType = getRequiredText(classification, "signalType");
        String severity = getRequiredText(classification, "severity");
        String assignedUnit = getRequiredText(classification, "assignedUnit");
        String recommendedAction = getRequiredText(classification, "recommendedAction");

        validateClassification(
                signalType,
                severity,
                assignedUnit,
                recommendedAction
        );

        return new AiClassificationDTO(
                signalType,
                severity,
                assignedUnit,
                recommendedAction
        );
    }

    private String extractJson(String content) {

        int start = content.indexOf('{');
        int end = content.lastIndexOf('}');

        if (start == -1 || end == -1 || start >= end) {
            throw new IllegalArgumentException("La respuesta de la IA no contiene un JSON válido");
        }

        return content.substring(start, end + 1);
    }

    private String getRequiredText(JsonNode node, String field) {

        JsonNode value = node.get(field);

        if (value == null || value.isNull() || value.asText().isBlank()) {
            throw new IllegalArgumentException("Campo obligatorio faltante en la respuesta de IA: " + field);
        }

        return value.asText();
    }

    private void validateClassification(
            String signalType,
            String severity,
            String assignedUnit,
            String recommendedAction
    ) {

        if (!VALID_SIGNAL_TYPES.contains(signalType)) {
            throw new IllegalArgumentException("signalType inválido: " + signalType);
        }

        if (!VALID_SEVERITIES.contains(severity)) {
            throw new IllegalArgumentException("severity inválido: " + severity);
        }

        String expectedUnit = EXPECTED_UNITS.get(signalType);

        if (!expectedUnit.equals(assignedUnit)) {
            throw new IllegalArgumentException("assignedUnit inválido para " + signalType);
        }

        if (recommendedAction.isBlank()) {
            throw new IllegalArgumentException("recommendedAction no puede estar vacío");
        }
    }
}