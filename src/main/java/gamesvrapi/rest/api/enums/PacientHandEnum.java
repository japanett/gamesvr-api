package gamesvrapi.rest.api.enums;

public enum PacientHandEnum {
  LEFT(1, "Mão esquerda"), RIGHT(2, "Mão direita"), ALTERNATED(3, "Mão alternada");

  private final String description;

  private final Integer code;

  PacientHandEnum(final Integer code, final String description) {
    this.description = description;
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public int getCode() {
    return code;
  }
}
