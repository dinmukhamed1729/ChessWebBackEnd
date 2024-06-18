package ChessWeb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RegistrationDto(
         String name,
         String email,	
         String password) {}
