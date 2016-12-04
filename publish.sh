#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "jchampemont/jabba-framework" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"

  if [ -n "$TRAVIS_TAG" ]; then
    echo -e "Publishing maven artifacts...\n"
    ./gradlew -PghToken=$GH_TOKEN publish
    cd $HOME/.gitRepos/jchampemont/maven-repository
    git add .
    git commit -m "Publish artifacts of $1 $TRAVIS_TAG on sucessful travis build $TRAVIS_BUILD_NUMBER"
    cd -
  fi

  echo -e "Publishing javadoc...\n"

  cp -R $1/build/docs/javadoc $HOME/$1-javadoc-latest

  cd $HOME
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/jchampemont/javadoc gh-pages > /dev/null

  mkdir -p gh-pages/$1
  cd gh-pages/$1
  cp -Rf $HOME/$1-javadoc-latest .
  if [ -z "$TRAVIS_TAG" ]; then
    git rm -rf $TRAVIS_BRANCH
    mv $1-javadoc-latest $TRAVIS_BRANCH
  else
    git rm -rf $TRAVIS_TAG
    mv $1-javadoc-latest $TRAVIS_TAG
  fi
  git add -f .
  git commit -m "Latest javadoc for $1 on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi