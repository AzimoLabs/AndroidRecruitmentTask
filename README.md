# Internship task
The goal of this programming task is to check how comfortable you are with Android code. Please keep in mind that this is not only for us to review your experience and skills, but also for you. We firmly believe that the ability to create described application will be useful when looking for a future job as an Android developer.

Code which you will create covers some skills which are useful in mobile software developer:

* Networking, connecting with REST API, combining requests
* Dealing with data: caching it (optional), processing, displaying it in the user interface
* Navigation between screens
* Implementing user friendly UI components
* Code/project structure, design/architectural patterns
* You will get extra points for automate tests checking your project (unit tests or any of user-interface tests).

During the review process, **we won’t pay much attention to UI/UX** (until you ask otherwise). Feel free to use system components without additional styling. Please keep your user interface readable. 

## How to submit your code
1. Fork this project it was already initialized with base DI configuration to help you focus on the task.
2. Work on the code on your repository. You can start by searching TODO sections. 
3. When it’s ready, just send us a link or create Pull Request via Github interface.

If you don’t want to share your code publicly, send us the entire project as a zip file via email.

Feel free to ask any questions at jakub.kwiatek@azimo.com.

# Task

## Github API client

Please create Github client app which connects to public Github REST API available here: https://developer.github.com/v3/. None of the requested methods require authentication. 

Requirements:

1. After filling in the username and clicking on the "->" button the app opens user details screen.
2. User details screen contains some basic information about the user and a list of his public repositories.
3. User details are fetched from {username}/user endpoint.
4. User repositories are fetched from {username}/repos endpoint.
5. Both request are combined together, they happen simultaneously.
6. Application handles api errors.
7. Additional points for unit and UI tests.
