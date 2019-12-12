package at.fhv.teama.easyticket.server.security;

import lombok.Data;

@Data
public class LDAPConfig {
  private String[] userDnPatterns;
  private String groupSearchBase;
  private String userSearchBase;
  private String userSearchFilter;
  private String managerDn;
  private String managerPassword;
  private String url;
  private int port = 636;
}
