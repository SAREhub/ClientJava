#!/bin/bash
# This script initiates the Gradle publishing task when pushes to master occur.
# NOTE: Travis-CI can only publish SNAPSHOT versions.
echo "REPO_SLUG: $TRAVIS_REPO_SLUG"
echo "IS_PULL_REQUEST: $TRAVIS_PULL_REQUEST"
echo "TAG: $TRAVIS_TAG"
if [ "$TRAVIS_REPO_SLUG" == "SAREhub/JavaClient" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && ["$TRAVIS_TAG"]; then
  echo "Checking TAG format"
  if [[ "$TRAVIS_TAG" =~ ".+SNAPSHOT" ]]; then
      echo 'Travis can only publish snapshots'
      return 0
  fi

  echo -e "Starting publish to Sonatype...\n"

  ./gradlew uploadArchives -PnexusUsername="${NEXUS_USERNAME}" -PnexusPassword="${NEXUS_PASSWORD}"
  RETVAL=$?

  if [ $RETVAL -eq 0 ]; then
    echo 'Completed publish!'
  else
    echo 'Publish failed.'
    return 1
  fi
fi

echo "Publish only on master branch and tag with SNAPSHOT"
