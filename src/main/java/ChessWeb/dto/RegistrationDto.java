package ChessWeb.dto;

import lombok.Builder;

@Builder
public record RegistrationDto(
        String name,
        String email,
        String password) {
}
