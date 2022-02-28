Feature: User should be able to login

  Background:
    Given the user is on the the login page

  @TRANS-1176 @AC1 @wip
  Scenario Outline: Login with different accounts <userType>
    When the user enters "<username>" and "<password>"
    Then the user should be able to login and "<pageSubtitle>" page should be displayed
    Examples:
      | userType      | username        | password    | pageSubtitle    |
      | Truck driver  | user17          | UserUser123 | Quick Launchpad |
      | Store manager | storemanager65  | UserUser123 | Dashboard       |
      | Sales manager | salesmanager135 | UserUser123 | Dashboard       |

  @TRANS-1185 @AC1 @wip
  Scenario: Validate the Breadcrumb, Page Heading, Page URL, Page Title of 'Dashboard Page' Page
    Given the user logs in as a "Store manager"
    Then Breadcrumb of Dashboard Page should be "Dashboards/ Dashboard" displayed
    And Page Heading of Dashboard Page should be "Dashboard" displayed
    And Page URL of Dashboard Page should be "https://qa.translantik.com/" displayed
    And Page Title of Dashboard Page should be "Dashboard" displayed
    And Modules of Dashboard Page should be displayed as following
      | Dashboards         |
      | Fleet              |
      | Customers          |
      | Sales              |
      | Activities         |
      | Marketing          |
      | Reports & Segments |
      | System             |


  @TRANS-1178 @AC2 @wip
  Scenario: The user should not be able to login without providing credentials
    Given the user logs in as a "Store manager"
    When the user copy the URL after login, then logs out
    And the user try to navigate Dashboard page by providing the URL without providing credentials
    Then the user should not be able to land on Dashboard page

  @TRANS-1194 @AC3 @wip
  Scenario: Application should be able to open when the user closes the browser without logging out
    Given the user logs in as a "Store manager"
    When the user closes the browser without logging out and open browser again
    Then the user should not land on Dashboard page

  @TRANS-1195 @AC3 @wip
  Scenario: Application should be able to open when the user closes the browser without logging out
    Given the user logs in as a "Store manager"
    When the user copy the URL and open a new TAB
    And the user closes the previous TAB and go to copied URL
    Then the user should see the Dashboard Page

  @TRANS-1183 @AC4 @wip
  Scenario: Leading and trailing spaces entered into the username field should be trimmed
    When the user enters a username with leading and trailing spaces "     storemanager65    " and valid password and clicks on login button
    Then the user should be able to login and "Dashboard" page should be displayed

  @TRANS-1184 @AC5 @wip
  Scenario: All the fields in the login page should have proper placeholders
    Then username input box ("Username or Email") and password input box ("Password") should have proper placeholders

  @TRANS-1186 @AC6 @wip
  Scenario Outline: Warning message should be displayed when the user enters invalid credentials
    When the user enters "<username>" and "<password>"
    Then "Invalid user name or password." should be displayed
    Examples:
      | username         | password        |
      | user17           | UserUser        |
      | user1000         | UserUser123     |
      | storemanager65   | User            |
      | storemanger1000  | UserUser123     |
      | salesmanager135  | UserUser1231111 |
      | salesmanager1000 | UserUser123     |

  @TRANS-1187 @AC6 @wip
  Scenario Outline: Warning message should be displayed when any field is empty
    When the user enters "<username>" and "<password>"
    Then Warning message "Please fill out this field." should be displayed
    Examples:
      | username        | password    |
      | user17          |             |
      | storemanager65  |             |
      | salesmanager135 |             |
      |                 | UserUser123 |

  @TRANS-1188 @AC7 @wip
  Scenario: The user should see password in a bullet sign format by default
    When the user enters a password "UserUser123" into the password input box
    Then the user should see password in a bullet sign format

  @TRANS-1196 @AC8 @wip
  Scenario: Validate the Password is not visible in the Page Source
    Then password should not be visible in the page source

  @TRANS-1197 @AC9 @wip
  Scenario: The text entered into the Password field should not be able to copy
    When the user enters a password "UserUser123" into the password input box
    And the user copy the text entered into the password field and paste into the username input box
    Then the text of username input should not match with entered password

  @TRANS-1202 @AC10 @wip
  Scenario Outline: The user should be able to change password by using Forgot Password menu
    When the user clicks on Forgot your password? link
    And the user enters a username "<username>" into the input box and clicks on request button
    Then the user should be able to change password
    Examples:
      | username        |
      | user17          |
      | storemanager65  |
      | salesmanager135 |

  @TRANS-1198 @AC11 @wip
  Scenario: Remember me on this computer link should be clickable
    Then remember me on this computer links should be displayed and clickable

  @TRANS-1200 @AC12 @wip
  Scenario: Verify to login by using the Keyboard keys
    When the user clicks on username input box, enter a valid username "user17" and hit ENTER button
    And the user enter a valid password "UserUser123", hit ENTER button
    Then the user should be able to login and "Quick Launchpad" page should be displayed

  @TRANS-1199 @AC12 @wip
  Scenario: Verify to login by using the Keyboard keys
    When the user clicks on username input box, enter a valid username "user17" and hit TAB button
    And the user enter a valid password "UserUser123", hit ENTER button
    Then the user should be able to login and "Quick Launchpad" page should be displayed

  @TRANS-1201 @AC13 @wip
  Scenario: The background color of Login button should be (hex code #0c84a3)
    Then verify the background color of Login button is (hex code "#0c84a3")




