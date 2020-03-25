#!/bin/bash

ROOT_DIR="../$(pwd)"

# create ./public/ release folder
cd blog
rm -rf public
hugo -D

# move release to correct location
cd ${ROOT_DIR}
cp -r hugo/blog/public/* .
