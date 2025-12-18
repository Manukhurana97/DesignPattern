The <b>Facade design pattern</b> is a <a href="../../../Readme.md#structural">structural design pattern </a> that provides a unified, significant interface to a complex subsystem  make it easier for the clients to interact with multiple compoments without getting overwhelmed.

Ex: <i>Consider you are at high end hotel. As a guest(client) you dont want to individually call houskeeper for a fresh towel, the restaurant for a dinner booking and valet for you car, instead you call concierge <b>(the facade: interface)</b> and make the requets in once and he will do all the arrangement for you.</i>

*Facade provides a simplified interface to a complex subsystem, hiding internal complexity from the client.


```

                +----------------------+
                |   DeploymentFacade   |
                +----------------------+
                | - vcs: VersionControlSystem |
                | - bs: BuildSystem           |
                | - tf: TestingFramework      |
                | - dt: DeploymentTarget      |
                +----------------------+
                | + deployApplication(branch, server): boolean |
                +----------------------+
                      |      |      |      |
          -------------       |      |       -------------
          |                   |      |                    |
+---------------------+ +-----------------+ +---------------------+ +----------------------+
| VersionControlSystem| |   BuildSystem   | | TestingFramework    | | DeploymentTarget     |
+---------------------+ +-----------------+ +---------------------+ +----------------------+
| + pullLatestChanges | | + compileProject| | + runUnitTest       | | + transferArtifact   |
+---------------------+ +-----------------+ +---------------------+ | + activateNewVersion |
                                                                    +----------------------+

                +----------------------+
                |        Main          |
                +----------------------+
                | + main(args): void   |
                +----------------------+



| Role       | Class                                                                         |
| ---------- | ----------------------------------------------------------------------------- |
| **Facade** | `DeploymentFacade`                                                            |
| Subsystems | `VersionControlSystem`, `BuildSystem`, `TestingFramework`, `DeploymentTarget` |
| Client     | `Main`                                                                        |

```