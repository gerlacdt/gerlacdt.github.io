#!/bin/bash

# export the whole wiki to ./output/index.html
tiddlywiki --build index

# copy generated index.html file to GIT_PROJECT_ROOT
cp output/index.html ../
