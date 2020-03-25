#!/bin/bash

ROOT_DIR=$(pwd)

RELEASE_FOLDER="blog"

# create ./public/ release folder
cd hugo/blog
rm -rf public
hugo -D

# move release to correct location
cd ${ROOT_DIR}
rm -rf ${RELEASE_FOLDER}
mv hugo/blog/public ${RELEASE_FOLDER}
