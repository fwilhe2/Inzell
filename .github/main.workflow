workflow "Build" {
  on = "push"
  resolves = [ "Build and test Incell" ]
}

action "Build and test Incell" {
  uses = "./build-action/"
}
