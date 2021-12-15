Feature: Dropbox Api Test

  Scenario Outline: File uploading test
    Given I have a file "<name>"
    When I upload file "<name>" to Dropbox
    Then I see file "<name>" successfully uploaded
    Examples:
    |                 name |
    |         homework.txt |
    |      course_work.pdf |
    |    profile_photo.png |

  Scenario Outline: Get file metadata test
    Given I have an uploaded file "<name>"
    When I request metadata of file "<name>" by its id
    Then I receive metadata for file "<name>"
    Examples:
      |                 name |
      |         homework.txt |
      |      course_work.pdf |
      |    profile_photo.png |

  Scenario Outline: List files and folders test
    When I ask for list of files and folders in "<path>"
    Then I receive list of files and folders
    Examples:
    |  path  |
    |        |
    |  /Apps  |

  Scenario Outline: Delete files test
    Given I have an uploaded file "<name>"
    When I ask to delete file "<name>"
    Then I see file "<name>" is successfully deleted
    Examples:
      |                 name |
      |         homework.txt |
      |      course_work.pdf |
      |    profile_photo.png |