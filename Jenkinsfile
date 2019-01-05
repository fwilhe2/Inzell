// taken from https://github.com/jonico/jenkinsfile-runner-github-actions

node {
    // pull request or feature branch
    if  (env.GITHUB_REF != 'refs/heads/master') {
        checkoutSource()
        mysh "./gradlew check"
        //build()
        //unitTest()
    } else {
        // master branch / production
        checkoutSource()
        mysh "./gradlew check"
        //build()
        //allTests()
        //createRelease("${env.GITHUB_ACTION}-${env.GITHUB_SHA}")
    }
}

def checkoutSource() {
  stage ('checkoutSource') {
    // as the commit that triggered that Jenkins action is already mapped to /github/workspace, we just copy that to the workspace
    copyFilesToWorkSpace()
  }
}

def copyFilesToWorkSpace() {
  mysh "cp -r /github/workspace/* $WORKSPACE"
}

// prevent output of secrets and a globbing patterns by Jenkins
def mysh(cmd) {
    sh('#!/bin/sh -e\n' + cmd)
}
