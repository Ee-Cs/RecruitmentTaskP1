# Implementation Notes
> [!IMPORTANT]
>
> This repository contains an implementation produced for the technical recruitment test. \
> The implementation focuses on the domain logic and was developed using \
> test-driven development (TDD) to validate and guide the design and behavior of the components.

### References
- 1️⃣ The [flowchart diagram](https://github.com/Ee-Cs/RecruitmentTaskP/blob/main/docs/flowchartDiagram.md)
- 2️⃣ The [Capture Reservation](https://github.com/Ee-Cs/RecruitmentTaskP/blob/main/docs/captureReservationSequenceDiagram.md) sequence diagram
- 3️⃣ The [Release Reservation](https://github.com/Ee-Cs/RecruitmentTaskP/blob/main/docs/releaseReservationSequenceDiagram.md) sequence diagram
- 4️⃣ The [Test Run in IDEA IDE](Screenshot_TestsRunInIdea.jpg) screenshot

### Some considerations
- This project provides the core logic and components that would enable a containerized application \
  to expose a `BackendController` as an API service, as described in the task brief.
- In accordance with the exercise scope, no application entry point \
  (Java `main` method) or framework bootstrap configuration (e.g., Spring Boot or Quarkus) has been included.
- The project intentionally omits deployment and run-time configuration \
  so the delivered artifacts remain an abstraction suitable for review and extension.

▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄
> [!CAUTION]
>
> Below is the original README.md content.

# Technical Test

## Instructions

The container would work as a starting point for any application to handle the lifecycle of the objects.

This hypothetical application would expose the BackendController as an API service.

There is no need for you to build such application, this is an abstraction test.

- Complete TODOs in the code as you see fit
- You may change all files in the project: design, method signature, packages, classes, etc. 
- Commit what you do in order to leave an audit trail
    - This is a local repository, there is no need to push anywhere. Just zip the project when you are done (with .git folder).
- There is no time limitation, but we encourage not spending more than two hours

Once the test is delivered, we will assess your work, and we will call you in for a <u>follow-up interview</u>.

## Follow-Up Interview

During the next interview we will take the chance to discuss your work and the design decisions that you have made.

The interview usually takes around 1.5 hours.

We believe there is no right nor wrong without analysing trade-offs first.

We typically discuss IoC, DI, TDD among other topics, and have a pair programming exercise at the end.

## Relevant Links

- https://chris.beams.io/posts/git-commit/
- http://en.wikipedia.org/wiki/Dependency_injection
- https://en.wikipedia.org/wiki/SOLID
- https://en.wikipedia.org/wiki/Extreme_programming_practices
- http://en.wikipedia.org/wiki/Test-driven_development
- https://www.kevinlondon.com/2015/05/05/code-review-best-practices.html