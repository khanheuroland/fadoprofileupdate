Feature: Change password for logged in account
  Background: Login with customer account
    Given The login page is showed
    When The user attempt to login with his account
    Then The dashboard will be showed

  Scenario: Update the new password with valid new password
    Given The update user information form is showed
    And The change password screen is showed
    When The user attempt to change current password to another valid password
    Then The message "Thay đổi mật khẩu thành công" will be showed

