package gamesvrapi.rest.api.enums;

public enum PlatformEnum {
  VR(1, "Virtual Reality"),
  SOCKS(2, "Intelligent Socks"),
  TABLET(3, "Tablet");

  private final String description;

  private final Integer code;

  PlatformEnum(final Integer code, final String description) {
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
