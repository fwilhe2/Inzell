FROM openjdk:11

LABEL "com.github.actions.name"="Build Incell"
LABEL "com.github.actions.description"="Build and test using Gradle"
LABEL "com.github.actions.icon"="mic"
LABEL "com.github.actions.color"="purple"

LABEL "repository"="https://github.com/fwilhe/Incell"
LABEL "maintainer"="fwilhe"

ADD entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]