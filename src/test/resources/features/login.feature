Feature: User login on Fado.vn

  Scenario: Login with valid account
    Given The login page is showed
    When The user attempt to login with his account
    Then The dashboard will be showed

